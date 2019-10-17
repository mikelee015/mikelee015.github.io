/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdcollection.controller;

import com.mycompany.dvdcollection.dao.DVDDao;
import com.mycompany.dvdcollection.dao.DVDDaoException;
import com.mycompany.dvdcollection.dto.DVD;
import com.mycompany.dvdcollection.ui.DVDView;
import java.util.List;

/**
 *
 * @author mac
 */
public class DVDController {

    DVDView view;
    DVDDao dao;

    public DVDController(DVDDao dao, DVDView view) {
        this.dao = dao;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listDVDs();
                        break;
                    case 2:
                        createDVD();
                        break;
                    case 3:
                        viewDVDById();
                        break;
                    case 4:
                        removeDVD();
                        break;
                    case 5:
                        editDVD();
                        break;
                    case 6:
                        viewDVDByTitle();
                        break;
                    case 7:
                        viewDvdsByDirector();
                        break;
                    case 8:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            } //end while loop

            exitMessage();

        } catch (DVDDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void listDVDs() throws DVDDaoException {
        view.displayDisplayAllBanner();
        List<DVD> DVDList = dao.getAll();
        view.displayDVDList(DVDList);
    }

    private void createDVD() throws DVDDaoException {
        view.displayCreateDVDBanner();
        DVD newDVD = view.getNewDVDInfo();
        dao.addDVD(newDVD);
        view.displayCreateSuccessBanner();
    }

    private void viewDVDById() throws DVDDaoException {
        view.displayDisplayDVDBanner();
        int DVDID = view.getDVDIDChoice();
        DVD DVD = dao.getByID(DVDID);
        view.displayDVD(DVD);
    }

    private void viewDVDByTitle() throws DVDDaoException {
        view.displayDisplayDVDBanner();
        String title = view.getDVDTitleChoice();
        DVD DVD = dao.getByTitle(title);
        view.displayDVD(DVD);
    }

    private void removeDVD() throws DVDDaoException {
        view.displayRemoveDVDbanner();
        int DVDID = view.getDVDIDChoice();
        dao.removeByID(DVDID);
        view.displayRemoveSuccessBanner();
    }

    private void editDVD() throws DVDDaoException {
        view.displayEditDVDbanner();
        int DVDID = view.getDVDIDChoice();
        DVD DVD = view.editDVD();
        dao.editDVD(DVDID, DVD);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitbanner();
    }

    private void viewDvdsByDirector() throws DVDDaoException {
        //get director name from user - from the view.
        String name = view.getDirectorName();
        view.displayDVDByDirectorBanner(name);
         
         //get all DVDs that match or have that director name - comes from dao.
         List<DVD> matchingDVDs = dao.getByDirectorName(name); //input is the String name referenced above. Get DVDs by director name.
         
         //send those DVDs out to the view to be printed. 
         view.displayDVDList(matchingDVDs); //use view to print matchingDVDs out to the list.
         
    }

}
