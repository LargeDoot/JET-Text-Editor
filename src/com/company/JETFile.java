package com.company;

public class JETFile {

    private String textContents, fileLocation;

    //Constructor for a new, empty and unsaved file
    public JETFile() {

        textContents = "";
        fileLocation = "Untitled";

    }

// --Commented out by Inspection START (12/03/2020 10:04):
//    //Unused
//    public JETFile(String text) {
//
//        this.textContents = text;
//
//    }
// --Commented out by Inspection STOP (12/03/2020 10:04)

    //Constructor for a new file with pre-defined text and location
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
