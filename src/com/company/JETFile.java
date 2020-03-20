package com.company;

/*******************************************************************************
 * Copyright (c) Ethan Wilson 2020.
 *
 * The JETFile class acts as a file, and stores the files URI and text contents.
 *
 * @author Ethan Wilson
 * @since 13-02-2020
 ******************************************************************************/
public class JETFile {

    private String textContents, fileLocation;

    /**
     * Constructor for a new, empty and unsaved file
     */
    public JETFile() {

        textContents = "";
        fileLocation = "Untitled";

    }

    /**
     * Constructor for a new file with pre-defined text and location
     *
     * @param text     the text to be written to the initial file
     * @param location the location of the initial file
     */
    public JETFile(String text, String location) {

        this.textContents = text;
        this.fileLocation = location;

    }


    public String getTextContents() {
        return textContents;
    }

    public void setTextContents(String textContents) {
        this.textContents = textContents;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }
}
