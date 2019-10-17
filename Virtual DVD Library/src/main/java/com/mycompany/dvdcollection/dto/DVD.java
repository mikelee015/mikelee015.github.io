/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdcollection.dto;

import java.time.LocalDate;

/**
 *
 * @author mac
 */
public class DVD {

    private int Id;
    private String Title;
    private LocalDate ReleaseDate;
    private String MpaaRating;
    private String DirectorName;
    private String Studio;
    private String UserRating;

    /**
     * @return the Id
     */
    public int getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(int Id) {
        this.Id = Id;
    }

    /**
     * @return the Title
     */
    public String getTitle() {
        return Title;
    }

    /**
     * @param Title the Title to set
     */
    public void setTitle(String Title) {
        this.Title = Title;
    }

    /**
     * @return the ReleaseDate
     */
    public LocalDate getReleaseDate() {
        return ReleaseDate;
    }

    /**
     * @param ReleaseDate the ReleaseDate to set
     */
    public void setReleaseDate(LocalDate ReleaseDate) {
        this.ReleaseDate = ReleaseDate;
    }

    /**
     * @return the MpaaRating
     */
    public String getMpaaRating() {
        return MpaaRating;
    }

    /**
     * @param MpaaRating the MpaaRating to set
     */
    public void setMpaaRating(String MpaaRating) {
        this.MpaaRating = MpaaRating;
    }

    /**
     * @return the DirectorName
     */
    public String getDirectorName() {
        return DirectorName;
    }

    /**
     * @param DirectorName the DirectorName to set
     */
    public void setDirectorName(String DirectorName) {
        this.DirectorName = DirectorName;
    }

    /**
     * @return the Studio
     */
    public String getStudio() {
        return Studio;
    }

    /**
     * @param Studio the Studio to set
     */
    public void setStudio(String Studio) {
        this.Studio = Studio;
    }

    /**
     * @return the UserRating
     */
    public String getUserRating() {
        return UserRating;
    }

    /**
     * @param UserRating the UserRating to set
     */
    public void setUserRating(String UserRating) {
        this.UserRating = UserRating;
    }
    
}
