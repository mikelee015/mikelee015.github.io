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
public class OrderDaoException extends Exception {
       public OrderDaoException(String msg) {
        super(msg);
    }

    public OrderDaoException(String msg, Throwable inner) {
        super(msg, inner);
    } 
}
