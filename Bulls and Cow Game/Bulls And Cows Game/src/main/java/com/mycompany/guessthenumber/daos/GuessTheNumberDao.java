/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guessthenumber.daos;

import com.mycompany.guessthenumber.models.Game;
import com.mycompany.guessthenumber.models.Round;
import java.util.List;

/**
 *
 * @author mac
 */
public interface GuessTheNumberDao {

    public Game newGame(int targetNum) throws InvalidTargetNumberException;

    public Game getGameById(int gameId) throws InvalidGameIdException;

    public Round newRound(Round newRound) throws RoundDaoException, InvalidGameIdException;

    public List<Game> getAllGames();

    public List<Round> getAllRounds(int gameId) throws InvalidGameIdException;

    public void editGame(int gameId) throws InvalidGameIdException;

    public void deleteAllGames();
}
