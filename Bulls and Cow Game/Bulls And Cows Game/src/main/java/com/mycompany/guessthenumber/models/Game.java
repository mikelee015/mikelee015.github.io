/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guessthenumber.models;

/**
 *
 * @author mac
 */
public class Game {
    private int gameId;
    private boolean isDone;
    private int targetNum;

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
     * @return the targetNum
     */
    public int getTargetNum() {
        return targetNum;
    }

    /**
     * @param targetNum the targetNum to set
     */
    public void setTargetNum(int targetNum) {
        this.targetNum = targetNum;
    }

    /**
     * @return the gameStatus
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * @param gamestatus the gameStatus to set
     */
    public void setIsDone(boolean gamestatus) {
        this.isDone = gamestatus;
    }
    
}
