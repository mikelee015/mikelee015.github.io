/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guessthenumber.daos;

/**
 *
 * @author mac
 */
public class InvalidTargetNumberException extends Exception{
        public InvalidTargetNumberException(String msg) {
        super(msg);
    }

    public InvalidTargetNumberException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
