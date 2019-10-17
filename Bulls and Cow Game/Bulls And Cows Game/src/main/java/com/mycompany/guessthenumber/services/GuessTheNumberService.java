/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guessthenumber.services;

import com.mycompany.guessthenumber.daos.InvalidTargetNumberException;
import com.mycompany.guessthenumber.models.Game;
import com.mycompany.guessthenumber.models.Round;
import java.util.List;

/**
 *
 * @author mac
 */
public interface GuessTheNumberService {

    public int newGame() throws InvalidNumberException;

    public Round guess(Round newRound) throws InvalidNumberException, InvalidIdException, InvalidRoundException;

    public List<Game> getAllGames();
    
    public Game getGameById(Integer gameId) throws InvalidIdException;

    public List<Round> getAllRounds(Integer gameId) throws InvalidIdException, InvalidRoundException;
    
}
