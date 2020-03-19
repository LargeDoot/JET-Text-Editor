package com.company.listeners.fileMenuListeners;

import com.company.EditorWindow;
import com.company.FileBrowser;
import com.company.JETFile;
import com.company.ReaderWriter;

import java.awt.event.ActionListener;

public class fileSaveAsListener extends fileSaveListener implements ActionListener {

    /**
     * Constructor to create the listener. Calls the superclass as this class extends the fileSaveListener class.
     *
     * @param window the parent Editor Window
     */
    public fileSaveAsListener(EditorWindow window) {
        super(window);
    }

    /**
     * Obtains a file location via a FileBrowser object, and puts the chosen location into the current JETFile as long
     * as the user did not cancel the FileBrowser. If the user did pick a location, the text from the Editor Window's is
     * saved to the KJETFile object, the file is written, and the window title is updated to reflect the file location
     * change.
     *
     * @param file
     */
    @Override
    void saveFile(JETFile file) {

        String saveAddress;
        String originalAddress = file.getFileLocation();

        //Open a new file browser to get a save URI
        saveAddress = new FileBrowser("Save As").getURI();


        //Set the file's location to the chosen URI as long as the user didn't cancel.
        if (saveAddress != null) {
            file.setFileLocation(saveAddress);
        }


        //If the file now has a new save address, write the data (i.e if user didnt cancel file browser).
        if (!originalAddress.equals(saveAddress) && saveAddress != null) {

            //Set the text contents of the file to that inside the main textarea.
            file.setTextContents(window.getText());

            //Write the data
            new ReaderWriter(file);

            //Set window title
            window.setTitle(String.format(" JET \t | \t %s", file.getFileLocation()));

        }

    }

}