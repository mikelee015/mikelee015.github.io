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
public class InvalidNumberException extends Exception {

    public InvalidNumberException(String msg) {
        super(msg);
    }

    public InvalidNumberException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
