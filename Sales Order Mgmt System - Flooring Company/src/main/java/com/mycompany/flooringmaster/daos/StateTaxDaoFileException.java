/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmaster.daos;

/**
 *
 * @author mac
 */
public class StateTaxDaoFileException extends Exception {

    public StateTaxDaoFileException(String msg) {
        super(msg);
    }

    public StateTaxDaoFileException(String msg, Throwable inner) {
        super(msg, inner);
    }
}
