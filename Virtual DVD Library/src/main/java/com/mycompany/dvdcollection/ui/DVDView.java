/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdcollection.ui;

import com.mycompany.dvdcollection.dto.DVD;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author mac
 */
public class DVDView {

    private DVDUserIO io;

    public DVDView(DVDUserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List DVDs");
        io.print("2. Add New DVD");
        io.print("3. View a DVD by ID");
        io.print("4. Remove a DVD");
        io.print("5. Edit a DVD");
        io.print("6. View a DVD by Title");
        io.print("7. View DVDs by Director");
        io.print("8. Exit");

        return io.readInt("Please select from the above choices.", 1, 8);
    }

    public DVD getNewDVDInfo() { //method for "2. Add new DVD".
        //Integer DVDId = io.readInt("Please enter DVD ID"); This isn't needed bc the app sets it for them. 
        String DVDTitle = io.readString("Please enter DVD Title");
        LocalDate releaseDate = io.readDate("Please enter Release Date"); //create a method to read your date.
        String mpaa = io.readString("Please enter MPAA Rating");
        String directorName = io.readString("Please enter Director's Name");
        String studio = io.readString("Please enter Studio Name");
        String userRating = io.readString("Please enter User Rating");
        DVD currentDVD = new DVD();
        currentDVD.setTitle(DVDTitle);
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setMpaaRating(mpaa);
        currentDVD.setDirectorName(directorName);
        currentDVD.setStudio(studio);
        currentDVD.setUserRating(userRating);
        return currentDVD;
    }

    public void displayCreateDVDBanner() {
        io.print("=== Create DVD ===");
    }

    public void displayCreateSuccessBanner() {
        io.readString(
                "DVD successfully created.  Please hit enter to continue");
    }

    public void displayDVDList(List<DVD> DVDList) { //method for "1. Get DVD List".
        for (DVD currentDVD : DVDList) {
            io.print(currentDVD.getId() + ": "
                    + currentDVD.getTitle() + " "
                    + currentDVD.getReleaseDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) + " "
                    + currentDVD.getMpaaRating() + " "
                    + currentDVD.getDirectorName() + " "
                    + currentDVD.getStudio() + " "
                    + currentDVD.getUserRating());
        }
        io.readString("Please hit enter to continue");
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All DVDs ===");
    }

    public void displayDisplayDVDBanner() {
        io.print("=== Display DVD ===");
    }

    public int getDVDIDChoice() {
        return io.readInt("Please enter the DVD ID.");
    }

    public String getDVDTitleChoice() {
        return io.readString("Please enter the DVD Title.");
    }

    public void displayDVD(DVD dvd) { //method for "3. View a DVD by ID".
        if (dvd != null) {
            io.print(dvd.getId() + "");
            io.print(dvd.getTitle() + "");
            io.print(dvd.getReleaseDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
            io.print(dvd.getMpaaRating() + "");
            io.print(dvd.getDirectorName() + "");
            io.print(dvd.getStudio() + "");
            io.print(dvd.getUserRating());
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }

    public DVD editDVD() {
        Integer DVDId = io.readInt("Please enter new DVD ID");
        String DVDTitle = io.readString("Please enter new DVD Title");
        LocalDate releaseDate = io.readDate("Please enter new Release Date");
        String mpaa = io.readString("Please enter new MPAA Rating");
        String directorName = io.readString("Please enter new Director's Name");
        String studio = io.readString("Please enter new Studio Name");
        String userRating = io.readString("Please enter new User Rating");
        DVD editedDVD = new DVD();
        editedDVD.setId(DVDId);
        editedDVD.setTitle(DVDTitle);
        editedDVD.setReleaseDate(releaseDate);
        editedDVD.setMpaaRating(mpaa);
        editedDVD.setDirectorName(directorName);
        editedDVD.setStudio(studio);
        editedDVD.setUserRating(userRating);
        return editedDVD;
    }

    public void displayEditDVDbanner() {
        io.print("=== Edit DVD ===");
    }

    public void displayEditDVDbannerSuccess() {
        io.print("=== DVD Edit Success ===");
    }

    public void displayRemoveDVDbanner() {
        io.print("=== Remove DVD ===");
    }

    public void displayRemoveSuccessBanner() {
        io.readString("DVD successfully removed. Please hit enter to continue.");
    }

    public void displayExitbanner() {
        io.print("Good bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public void displayDVDByDirectorBanner(String directorName) {
        io.print("All movies by " + directorName);
    }

    public String getDirectorName() {
        String directorName = io.readString("Enter Director Name");
        return directorName;
    }

}
