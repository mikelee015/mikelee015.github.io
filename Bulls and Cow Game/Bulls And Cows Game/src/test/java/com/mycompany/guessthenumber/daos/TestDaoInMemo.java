/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guessthenumber.daos;

import com.mycompany.guessthenumber.models.Game;
import com.mycompany.guessthenumber.models.Round;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mac
 */
@Profile("Service Test")
@Repository
public class TestDaoInMemo implements GuessTheNumberDao {

    List<Game> allGames = new ArrayList();
    List<Round> allRounds = new ArrayList();

    public TestDaoInMemo() {

        Game game1 = new Game();
        game1.setGameId(1);
        game1.setIsDone(true);
        game1.setTargetNum(1234);
        allGames.add(game1);

        Game game2 = new Game();
        game2.setGameId(2);
        game2.setIsDone(false);
        game2.setTargetNum(5678);
        allGames.add(game2);

        Round round1 = new Round();
        round1.setRoundId(1);
        round1.setGameId(1);
        round1.setGuessedNum(9281);
        round1.setPartialMatchCount(1);
        round1.setExactMatchCount(1);
        round1.setGuessTime(LocalDateTime.of(2019, 10, 1, 12, 01, 25));
        allRounds.add(round1);

        Round round2 = new Round();
        round2.setRoundId(2);
        round2.setGameId(1);
        round2.setGuessedNum(1234);
        round2.setPartialMatchCount(0);
        round2.setExactMatchCount(4);
        round2.setGuessTime(LocalDateTime.of(2019, 10, 1, 12, 03, 45));
        allRounds.add(round2);

        Round round3 = new Round();
        round3.setRoundId(1);
        round3.setGameId(2);
        round3.setGuessedNum(5679);
        round3.setPartialMatchCount(0);
        round3.setExactMatchCount(3);
        round3.setGuessTime(LocalDateTime.of(2019, 10, 10, 15, 01, 00));
        allRounds.add(round3);

        Round round4 = new Round();
        round4.setRoundId(2);
        round4.setGameId(2);
        round4.setGuessedNum(5671);
        round4.setPartialMatchCount(0);
        round4.setExactMatchCount(3);
        round4.setGuessTime(LocalDateTime.of(2019, 10, 10, 15, 04, 02));
        allRounds.add(round4);
    }

    @Override
    public Game newGame(int targetNum) throws InvalidTargetNumberException {
        Game toReturn = new Game();
        //maptoInt takes the game and converts it to it's gameid. Then you can get max, etc.
        toReturn.setGameId( allGames.stream().mapToInt( g -> g.getGameId() ).max().orElse(0) + 1);
        toReturn.setIsDone(false);
        toReturn.setTargetNum(targetNum);
        allGames.add(toReturn);
        return toReturn;
    }

    @Override
    public Game getGameById(int gameId) throws InvalidGameIdException {
        Game toReturn = allGames
                .stream()
                .filter(i -> i.getGameId() == (gameId))
                .findFirst().orElse(null);

//        if (toReturn == null) {
//            throw new InvalidGameIdException("Could not find game with Id: " + gameId);
//        }
        return toReturn;
    }

    @Override
    public Round newRound(Round newRound) throws RoundDaoException {
        newRound.setRoundId( allRounds.stream().mapToInt( r -> r.getRoundId() ).max().orElse(0) + 1);
        allRounds.add(newRound);

        if (newRound == null) {
            throw new RoundDaoException("Cannot add an empty Round.");
        }
        return newRound;
    }

    @Override
    public List<Game> getAllGames() {
        return allGames;
    }

    @Override
    public List<Round> getAllRounds(int gameId) throws InvalidGameIdException {
        if (gameId == 0 || gameId <= 0) {
            throw new InvalidGameIdException("Game with ID: " + gameId + " does not exist.");
        }

        List<Round> toReturn
                = allRounds
                        .stream()
                        .filter(i -> i.getGameId() == (gameId))
                        .collect(Collectors.toList());
        return toReturn;
    }

    @Override
    public void editGame(int gameId) throws InvalidGameIdException {
        if (gameId == 0 || gameId <= 0) {
            throw new InvalidGameIdException("Game with ID: " + gameId + " does not exist.");
        }

        Game toEdit = getGameById(gameId);
        toEdit.setIsDone(true);
    }

    @Override
    public void deleteAllGames() {
        allRounds.clear();
        allGames.clear();
    }

}
