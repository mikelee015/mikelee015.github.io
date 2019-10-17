/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmaster.service;

/**
 *
 * @author mac
 */
public class InvalidDateException extends Exception {

    public InvalidDateException(String msg) {
        super(msg);
    }

    public InvalidDateException(String msg, Throwable inner) {
        super(msg, inner);
    }
}
