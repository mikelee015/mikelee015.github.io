/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdcollection.dao;

import com.mycompany.dvdcollection.dto.DVD;
import java.util.List;

/**
 *
 * @author mac
 */
public interface DVDDao {

    //Create
    DVD addDVD(DVD toAdd) throws DVDDaoException;
    
    //Read
    public DVD getByID(int id) throws DVDDaoException;
    public List<DVD> getAll() throws DVDDaoException;
    DVD getByTitle(String title) throws DVDDaoException;

    List<DVD> getByDirectorName(String directorName) throws DVDDaoException; //return type is a list of DVDs.
    
    //Update
    void editDVD(int oldID, DVD toEdit) throws DVDDaoException;

    //Delete
    void removeByID(int id) throws DVDDaoException;
    

}
