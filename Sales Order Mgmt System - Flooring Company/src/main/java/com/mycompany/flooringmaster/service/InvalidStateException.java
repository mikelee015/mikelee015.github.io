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
public class InvalidStateException extends Exception{
    
      public InvalidStateException(String msg) {
        super(msg);
    }

    public InvalidStateException(String msg, Throwable inner) {
        super(msg, inner);
    }  
}
