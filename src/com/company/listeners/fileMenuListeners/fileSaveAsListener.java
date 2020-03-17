package com.company.listeners.fileMenuListeners;

import com.company.EditorWindow;
import com.company.FileBrowser;
import com.company.JETFile;
import com.company.ReaderWriter;

import java.awt.event.ActionListener;

public class fileSaveAsListener extends fileSaveListener implements ActionListener {

    public fileSaveAsListener(EditorWindow window) {
        super(window);
    }

    @Override
    void saveFile(JETFile file) {

        String saveAddress = "";
        String originalAddress = file.getFileLocation();

        //Open a new file browser to get a save URI
        saveAddress = new FileBrowser().getURI();

        //Set the file's location to the chosen URI as long as the user didn't cancel.
        if (saveAddress != null) {
            file.setFileLocation(saveAddress);
        }

        //If the file now has a new save address, write the data (i.e if user didnt cancel file browser).
        if (originalAddress.equals(saveAddress)) {

            //Set the text contents of the file to that inside the main textarea.
            file.setTextContents(window.getText());

            //Write the data
            new ReaderWriter(file);

        }

    }

}