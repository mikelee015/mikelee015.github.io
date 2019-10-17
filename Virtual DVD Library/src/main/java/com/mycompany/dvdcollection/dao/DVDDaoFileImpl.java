/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdcollection.dao;

import com.mycompany.dvdcollection.dto.DVD;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author mac
 */
public class DVDDaoFileImpl implements DVDDao {

    String path;

    public static final String DVD_FILE = "DVD.txt";
    public static final String DELIMITER = ",";

    public DVDDaoFileImpl(String path) {
        this.path = path;
    }

    @Override
    public List<DVD> getAll() throws DVDDaoException {

        List<DVD> toReturn = new ArrayList<>();

        FileReader reader = null;
        try {
            reader = new FileReader(path);
            Scanner scn = new Scanner(reader);

            while (scn.hasNextLine()) {
                String line = scn.nextLine();
                if (line.length() > 0) {
                    String[] cells = line.split(",");

                    DVD toAdd = new DVD(); //allows you to set the fields of DVD toAdd.
                    toAdd.setId(Integer.parseInt(cells[0]));
                    toAdd.setTitle(cells[1]);
                    toAdd.setReleaseDate(LocalDate.parse(cells[2]));
                    toAdd.setMpaaRating(cells[3]);
                    toAdd.setDirectorName(cells[4]);
                    toAdd.setStudio(cells[5]);
                    toAdd.setUserRating(cells[6]);

                    toReturn.add(toAdd);
                }
            }

        } catch (FileNotFoundException ex) {

        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                throw new DVDDaoException("Could not close reader for " + path, ex);
            }
        }
        return toReturn; //returns updated list from file.
    }

    @Override
    public DVD getByID(int id) throws DVDDaoException { //add intake parameter of int id to getByID method.
        DVD toReturn = null; //setting variable to check ID on. 

        List<DVD> allDVDs = getAll();

        for (DVD toCheck : allDVDs) {
            if (toCheck.getId() == id) {
                toReturn = toCheck;
                break;
            }
        }
        return toReturn;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DVD getByTitle(String title) throws DVDDaoException {
        DVD toReturn = null;

        List<DVD> allDVDs = getAll();

        for (DVD toCheck : allDVDs) {
            if (toCheck.getTitle() != title) {
                toReturn = toCheck;
                break;
            }
        }
        return toReturn;
    }

    @Override
    public void removeByID(int id) throws DVDDaoException {

        List<DVD> allDVDs = getAll(); //calls getAll() on line 34, which updates the list of DVDs.

        int matchIndex = -1;

        for (int i = 0; i < allDVDs.size(); i++) {
            DVD toCheck = allDVDs.get(i);

            if (toCheck.getId() == id) {
                matchIndex = i;
                break;
            }
        }

        if (matchIndex == -1) { //didn't find an id that matches
            throw new DVDDaoException("ERROR: could not remove DVD with id " + id);
        }

        allDVDs.remove(matchIndex);

        writeFile(allDVDs);
    }

    @Override
    public DVD addDVD(DVD toAdd) throws DVDDaoException {//declares a DVD variable called toAdd so you can set your setters.

        List<DVD> allDVDs = getAll();

        int newID = 0;

        for (DVD toCheck : allDVDs) { //right now you can add duplicate IDs but it just increases it by 1 due to line 129. Need to update if else statement to throw an exception. 
            if (toCheck.getId() > newID) {//assumes new ID would go in ascending order.
                newID = toCheck.getId();
            }
        }

        newID++;
        toAdd.setId(newID);

        allDVDs.add(toAdd);

        writeFile(allDVDs);

        return toAdd;
    }

    @Override
    public void editDVD(int oldID, DVD toEdit) throws DVDDaoException {

        List<DVD> allDVDs = getAll();

        int matchIndex = -1;
        boolean dupeId = false;
        for (int i = 0; i < allDVDs.size(); i++) {
            if (allDVDs.get(i).getId() == oldID) {
                matchIndex = i;
            } else if (allDVDs.get(i).getId() == toEdit.getId()) {
                dupeId = true;
            }
        }

        if (matchIndex == -1) { //if you don't get a match.
            throw new DVDDaoException("ERROR: could not edit DVD with id " + oldID);
        } else if (dupeId) { //if you try to use an existing ID.
            throw new DVDDaoException("ERROR; cannot edit dvd to have an existing ID " + toEdit.getId());
        }

        allDVDs.remove(matchIndex);
        allDVDs.add(toEdit);
        writeFile(allDVDs);
    }

    private void writeFile(List<DVD> allDVDs) throws DVDDaoException {

        FileWriter writer = null;
        try {
            writer = new FileWriter(path);
            PrintWriter pw = new PrintWriter(writer);

            for (DVD toWrite : allDVDs) {
                String line = convertToLine(toWrite);
                pw.println(line);
            }
        } catch (IOException ex) {
            throw new DVDDaoException("Error: count not write to " + path, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                throw new DVDDaoException("Error: could not close writer for " + path, ex);
            }
        }
    }

    private String convertToLine(DVD toWrite) {
        String line
                = toWrite.getId() + ","
                + toWrite.getTitle() + ","
                + toWrite.getReleaseDate() + ","
                + toWrite.getMpaaRating() + ","
                + toWrite.getDirectorName() + ","
                + toWrite.getStudio() + ","
                + toWrite.getUserRating();

        return line;
    }

//    @Override
//    public DVD addDVD(String DVDId, DVD toAdd) throws DVDDaoException { 
//
//        List<DVD> allDVDs = getAll();
//
//        int newID = 0;
//
//        for (DVD toCheck : allDVDs) {
//            if (toCheck.getId() > newID) {
//                newID = toCheck.getId();
//            }
//        }
//
//        newID++;
//        toAdd.setId(newID);
//
//        allDVDs.add(toAdd);
//
//        writeFile(allDVDs);
//
//        return toAdd;
//    }
    
    
    
    
    
//    @Override
//    public DVD getByDirectorName(String directorName) throws DVDDaoException {
//        DVD toReturn = null;
//        
//        List<DVD> allDVDs = getAll();
//        
//        for (DVD toCheck : allDVDs) {
//            if (toCheck.getDirectorName() != directorName) {
//                toReturn = toCheck;
//                break;
//            } else {throw new DVDDaoException("ERROR; cannot find director name " + directorName );
//            }
//        }
//        return toReturn;
//    }

    @Override
    public List<DVD> getByDirectorName(String directorName) throws DVDDaoException {
        //make a variable that will hold your answer.
        List<DVD> toReturn = new ArrayList<>(); //creating a new list to store a list of dvds by director name.
        
        List<DVD> allDVDs = getAll(); //use the getAll method to get access to all DVDs. 
        
        for(DVD toCheck : allDVDs) {
            //do we want to add it to toReturn or now?
            if (toCheck.getDirectorName().equalsIgnoreCase(directorName)) { //can't use == with strings. equalsIgnoreCase is used to ignor the upper/lower case input by user.
                toReturn.add(toCheck);
            }
        }
        return toReturn;
    }
    
}


