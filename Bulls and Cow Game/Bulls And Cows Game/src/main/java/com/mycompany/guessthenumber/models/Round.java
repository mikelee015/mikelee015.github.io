/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guessthenumber.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;



/**
 *
 * @author mac
 */
public class Round {
    private int roundId;
    private int gameId; //to track which game the round is associated with.
    private int guessedNum;
    private int partialMatchCount;
    private int exactMatchCount;
    private LocalDateTime guessTime;

    /**
     * @return the roundId
     */
    public int getRoundId() {
        return roundId;
    }

    /**
     * @param roundId the roundId to set
     */
    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    /**
     * @return the gameId
     */
    public int getGameId() {
        return gameId;
    }

    /**
     * @param gameId the gameId to set
     */
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    /**
     * @return the guessedNum
     */
    public int getGuessedNum() {
        return guessedNum;
    }

    /**
     * @param guessedNum the guessedNum to set
     */
    public void setGuessedNum(int guessedNum) {
        this.guessedNum = guessedNum;
    }

    /**
     * @return the partialMatchCount
     */
    public int getPartialMatchCount() {
        return partialMatchCount;
    }

    /**
     * @param partialMatchCount the partialMatchCount to set
     */
    public void setPartialMatchCount(int partialMatchCount) {
        this.partialMatchCount = partialMatchCount;
    }

    /**
     * @return the exactMatchCount
     */
    public int getExactMatchCount() {
        return exactMatchCount;
    }

    /**
     * @param exactMatchCount the exactMatchCount to set
     */
    public void setExactMatchCount(int exactMatchCount) {
        this.exactMatchCount = exactMatchCount;
    }

    /**
     * @return the guessTime
     */
    public LocalDateTime getGuessTime() {
        return guessTime;
    }

    /**
     * @param guessTime the guessTime to set
     */
    public void setGuessTime(LocalDateTime guessTime) {
        this.guessTime = guessTime;
    }




    
    
    
}
