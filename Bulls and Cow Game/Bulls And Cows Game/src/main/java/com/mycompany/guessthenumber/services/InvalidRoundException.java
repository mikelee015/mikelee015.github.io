/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guessthenumber.services;

/**
 *
 * @author mac
 */
public class InvalidRoundException extends Exception {
        public InvalidRoundException(String msg) {
        super(msg);
    }

    public InvalidRoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
