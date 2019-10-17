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
public class ProductDaoException extends Exception {

    public ProductDaoException(String msg) {
        super(msg);
    }

    public ProductDaoException(String msg, Throwable inner) {
        super(msg, inner);
    }
}
