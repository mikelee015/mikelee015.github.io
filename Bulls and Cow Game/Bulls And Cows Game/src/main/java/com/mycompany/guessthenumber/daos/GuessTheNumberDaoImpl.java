/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guessthenumber.daos;

import com.mycompany.guessthenumber.models.Game;
import com.mycompany.guessthenumber.models.Round;
import com.mycompany.guessthenumber.services.GuessTheNumberService;
import com.mycompany.guessthenumber.services.GuessTheNumberServiceImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mac
 */
@Repository
@Profile({"production", "test"})
public class GuessTheNumberDaoImpl implements GuessTheNumberDao {

//    @Autowired
//    GuessTheNumberServiceImpl service;
    @Autowired
    private JdbcTemplate template;

    @Override
    @Transactional
    //add a new game to the databse.
    public Game newGame(int targetNum) throws InvalidTargetNumberException{
        
        if(targetNum == 0){
            throw new InvalidTargetNumberException("Target number is invalid.");
        }
        Game newGame = new Game();

        //set the targetNum for the game.
        newGame.setTargetNum(targetNum);

        //set status for the new game. T = game completed. F = game in-play.
        boolean isDone = false;
        newGame.setIsDone(isDone);

        //Add status and targetNum to the games table.
        String insert = "insert into games (isDone, targetnumber) values (?,?)";

        //use generated keyholder to get game ID.
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement toReturn = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
                toReturn.setBoolean(1, newGame.getIsDone());
                toReturn.setInt(2, newGame.getTargetNum());
                return toReturn;
            }
        };
        template.update(psc, holder);
        int generatedId = holder.getKey().intValue();
        newGame.setGameId(generatedId);

        return newGame;
    }

    @Override
    public Game getGameById(int gameId) throws InvalidGameIdException{
        
        if (gameId == 0) {
            throw new InvalidGameIdException("Game with ID: " + gameId + " does not exist.");
        }
        String query = "select * from games where gameid = ?";
        Game toReturn = null;
                try{
              toReturn =  template.queryForObject(query, new gameMapper(), gameId);
                }
                catch( EmptyResultDataAccessException ex ){}
        return toReturn;
    }

    @Override
    @Transactional
    public Round newRound(Round newRound) throws RoundDaoException, InvalidGameIdException {

            if (newRound == null) {
                throw new RoundDaoException("Cannot add an empty Round.");
            }
            
            int gameId = newRound.getGameId();
            
            Game getGame = getGameById(gameId);
            if(getGame == null){
                throw new InvalidGameIdException("Game does not exist");
            }
            
            int guessedNum = newRound.getGuessedNum();
            int pMatchCount = newRound.getPartialMatchCount();
            int eMatchCount = newRound.getExactMatchCount();
            LocalDateTime guessedTime = newRound.getGuessTime();
            
            if(guessedTime == null){
                throw new RoundDaoException("Timestamp cannot be null.");
            }
            //to do: check guessedTime for a null. And write test for a null date. Create new exception.
            
            String insert = "insert into rounds (gameid, guessednumber, partialmatchcount, exactmatchcount, guessedtime) values (?,?,?,?,?)";
            
            //use generated keyholder and psc to get auto-generated roundID from database.
            GeneratedKeyHolder holder = new GeneratedKeyHolder();
            PreparedStatementCreator psc = new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement toReturn = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
                    toReturn.setInt(1, gameId);
                    toReturn.setInt(2, guessedNum);
                    toReturn.setInt(3, pMatchCount);
                    toReturn.setInt(4, eMatchCount);
                    toReturn.setString(5, guessedTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    return toReturn;
                }
            };
            template.update(psc, holder);
            int generatedId = holder.getKey().intValue();
            newRound.setRoundId(generatedId);
            int roundId = newRound.getRoundId();
            
            return newRound;

    }

    @Override
    public List<Game> getAllGames() {

        String query = "select * from games";
        List<Game> toReturn = template.query(query, new gameMapper());
        return toReturn;
    }

    @Override
    public List<Round> getAllRounds(int gameId) throws InvalidGameIdException {
        
        if (getGameById(gameId) == null) {
            throw new InvalidGameIdException("Game with ID: " + gameId + " does not exist.");
        }
        //Returns a list of all rounds for a specific game id.
        String query = "select * from rounds where gameid = ?";
        List<Round> allRounds = template.query(query, new roundMapper(), gameId);
        return allRounds;
    }

    @Override
    public void editGame(int gameId) throws InvalidGameIdException {
        //HOW DO YOU CHECK FOR A NULL GAMEID???
        if (gameId == 0) {
            throw new InvalidGameIdException("Game with ID: " + gameId + " does not exist.");
        }
        Game getGame = getGameById(gameId);
        int toEdit = getGame.getGameId();

        String update = "update games set isDone = '1' where gameid = ?";
        template.update(update, toEdit);
    }

    @Override
    public void deleteAllGames() {
        String deleteRelationships = "delete from rounds";
        template.update(deleteRelationships);
        String deleteGames = "delete from games";
        template.update(deleteGames);
    }

    private static class gameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int i) throws SQLException {
            Game toAdd = new Game();
            toAdd.setGameId(rs.getInt("gameid"));
            toAdd.setIsDone(rs.getBoolean("isDone"));
            toAdd.setTargetNum(rs.getInt("targetnumber"));
            return toAdd;
        }
    }

    private static class roundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int i) throws SQLException {
            Round toAdd = new Round();
            toAdd.setRoundId(rs.getInt("roundid"));
            toAdd.setGameId((rs.getInt("gameid")));
            toAdd.setGuessedNum(rs.getInt("guessednumber"));
            toAdd.setPartialMatchCount(rs.getInt("partialmatchcount"));
            toAdd.setExactMatchCount(rs.getInt("exactmatchcount"));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            toAdd.setGuessTime(LocalDateTime.parse(rs.getString("guessedtime"), formatter));

            return toAdd;
        }
    }

}
