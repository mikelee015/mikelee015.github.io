/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guessthenumber.services;

import com.mycompany.guessthenumber.daos.GuessTheNumberDao;
import com.mycompany.guessthenumber.daos.InvalidGameIdException;
import com.mycompany.guessthenumber.daos.InvalidTargetNumberException;
import com.mycompany.guessthenumber.daos.RoundDaoException;
import com.mycompany.guessthenumber.models.Game;
import com.mycompany.guessthenumber.models.Round;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author mac
 */
@Component
public class GuessTheNumberServiceImpl implements GuessTheNumberService {

    GuessTheNumberDao dao;

    @Autowired
    public GuessTheNumberServiceImpl(GuessTheNumberDao dao) {
        this.dao = dao;
    }

    Random rng = new Random();

    @Override
    public int newGame() throws InvalidNumberException {

        Game newGame = new Game();
        int targetNum = generateTargetNum();

        try {
            newGame = dao.newGame(targetNum);
        } catch (InvalidTargetNumberException ex) {
            throw new InvalidNumberException("Invalid target number.");
        }
        return newGame.getGameId();
    }

    @Override
    public Round guess(Round newRound) throws InvalidNumberException, InvalidIdException, InvalidRoundException {
        try {
            boolean duplicates = hasDuplicates(newRound.getGuessedNum());

            Game game = dao.getGameById(newRound.getGameId());

            //TODO: check if game is null, if so throw an exception
            if (game == null) {
                throw new InvalidIdException("Game does not exist.");
            }

            if (game.getIsDone() == true) {
                throw new InvalidIdException("Game is no longer active.");
            }

            if (duplicates) {
                throw new InvalidNumberException("Your guess cannot contain duplicate numbers: " + newRound.getGuessedNum());
            }

            int targetNum = game.getTargetNum();
            int exactMatchCount = exactMatches(targetNum, newRound.getGuessedNum());
            int partialMatchCount = partialMatches(targetNum, newRound.getGuessedNum());

            LocalDateTime guessedTime = LocalDateTime.now();

            newRound.setExactMatchCount(exactMatchCount);
            newRound.setPartialMatchCount(partialMatchCount);
            newRound.setGuessedNum(newRound.getGuessedNum());
            newRound.setGameId(newRound.getGameId());
            newRound.setGuessTime(guessedTime);

            Round updatedRound = dao.newRound(newRound);//drop in a whole 'round' object.

            if (updatedRound.getExactMatchCount() == 4) {
                //T = active. F = inactive.
                game.setIsDone(true);
                dao.editGame(newRound.getGameId());
            }

            return newRound;
        } catch (RoundDaoException | InvalidGameIdException ex) {
            throw new InvalidRoundException("Invalid Round.");
        }
    }

    @Override
    public Game getGameById(Integer gameId) throws InvalidIdException {

        if (gameId == 0 || gameId <= 0) {
            throw new InvalidIdException("Game with ID " + gameId + " does not exist.");
        }

        Game getGame = null;

        try {
            getGame = dao.getGameById(gameId);
            if (getGame == null) {
                throw new InvalidIdException("Game with ID " + gameId + " does not exist.");
            }
            
            Boolean gameOver = getGame.getIsDone();
            //Must check game status only AFTER you've gotten the game by ID.

            if (!gameOver) {
                getGame.setTargetNum(0);
            }

            //Catches DAO exception.
        } catch (InvalidGameIdException ex) {
            throw new InvalidIdException("Game with ID " + gameId + " does not exist.");
        }

        return getGame;
    }

    @Override
    public List<Game> getAllGames() {
        
        List<Game> allGames = dao.getAllGames();
        for(Game toCheck : allGames){
            if(toCheck.getIsDone() == false){
                toCheck.setTargetNum(0);
            }
        }
        return allGames;
    }

    @Override
    public List<Round> getAllRounds(Integer gameId) throws InvalidIdException, InvalidRoundException {
        try {
            if (dao.getGameById(gameId) == null) {
                throw new InvalidIdException("Game with ID " + gameId + " does not exist.");
            }
            return dao.getAllRounds(gameId);
        } catch (InvalidGameIdException ex) {
            throw new InvalidRoundException("Round info does not exist.");
        }
    }

    private int generateTargetNum() {
        List<Integer> avail = new ArrayList(
                Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        int toReturn = 0;
        for (int i = 0; i < 4; i++) {
            toReturn *= 10;
            int digitIndex = rng.nextInt(avail.size());
            int digit = avail.get(digitIndex);
            toReturn += digit;
            avail.remove(digitIndex);
        }
        return toReturn;
    }

    private int exactMatches(int target, int guess) {
        int toReturn = 0;
        for (int i = 0; i < 4; i++) {
            int targetOnes = target % 10;
            int guessOnes = guess % 10;
            if (targetOnes == guessOnes) {
                toReturn++;
            }
            target /= 10;
            guess /= 10;
        }
        return toReturn;
    }

    private int partialMatches(int target, int guess) {
        int toReturn = 0;
        String t = target + "";
        String g = guess + "";
        if (target < 1000) {
            t = "0" + t;
        }
        if (guess < 1000) {
            g = "0" + g;
        }
        for (int i = 0; i < 4; i++) {
            char gc = g.charAt(i);
            for (int j = 0; j < 4; j++) {
                if (i != j) {
                    char gt = t.charAt(j);
                    if (gc == gt) {
                        toReturn++;
                    }
                }
            }
        }
        return toReturn;
    }

    private boolean hasDuplicates(int guessNumber) {
        boolean toReturn = false;
        boolean[] seenDigit = new boolean[]{false, false, false, false, false, false, false, false, false, false};
        for (int i = 0; i < 4; i++) {
            int onesPlace = guessNumber % 10;
            guessNumber /= 10;
            boolean seenBefore = seenDigit[onesPlace];
            if (seenBefore) {
                toReturn = true;
                break;
            } else {
                seenDigit[onesPlace] = true;
            }
        }
        return toReturn;
    }

}
