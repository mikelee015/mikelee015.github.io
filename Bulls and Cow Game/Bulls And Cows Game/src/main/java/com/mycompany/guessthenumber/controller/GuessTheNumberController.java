/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guessthenumber.controller;

import com.mycompany.guessthenumber.models.Game;
import com.mycompany.guessthenumber.models.Round;
import com.mycompany.guessthenumber.services.GuessTheNumberService;
import com.mycompany.guessthenumber.services.InvalidIdException;
import com.mycompany.guessthenumber.services.InvalidNumberException;
import com.mycompany.guessthenumber.services.InvalidRoundException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author mac
 */
@RestController
@RequestMapping("/api")
public class GuessTheNumberController {

    @Autowired
    GuessTheNumberService service;

    //your gets and posts go here with a @GetMapping or a @PostMapping.
    //Should start a game, generates an answer, and sets the correct status.
    @PostMapping("/begin")
    public int startNewGame() throws InvalidNumberException{
        int toReturn = service.newGame() ;
        return toReturn;
    }

    @PostMapping("/guess")
    public Round guess(@RequestBody Round newRound) throws InvalidNumberException, InvalidIdException, InvalidRoundException {
        Round toReturn = service.guess(newRound);
        return toReturn;
    }

    @GetMapping("/game/{Id}")
    public Game getGameById(@PathVariable Integer Id) throws InvalidIdException {
        Game toReturn = null;
        toReturn = service.getGameById(Id);
        return toReturn;
        
    }

    @GetMapping("/games")
    public List<Game> getAllGames() {
        List<Game> allGames = new ArrayList();
        allGames = service.getAllGames();
        return allGames; 
    }

    @GetMapping("/rounds/{Id}")
    public List<Round> getAllRoundsByGameId(@PathVariable Integer Id) throws InvalidIdException, InvalidRoundException {
        List<Round> allRounds = new ArrayList();
        allRounds = service.getAllRounds(Id);
        return allRounds;
    }

}
