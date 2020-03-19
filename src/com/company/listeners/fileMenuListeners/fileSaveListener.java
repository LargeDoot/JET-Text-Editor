package com.company.listeners.fileMenuListeners;

import com.company.EditorWindow;
import com.company.FileBrowser;
import com.company.JETFile;
import com.company.ReaderWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class fileSaveListener implements ActionListener {

    final EditorWindow window;
    JDialog dialog;

    /**
     * Constructor to create a listener from a window
     *
     * @param window the parent window
     */
    public fileSaveListener(EditorWindow window) {

        this.window = window;
    }

    /**
     * A constructor to create a listener from a dialog (i.e save dialog)
     *
     * @param window the parent window of the dialog
     * @param dialog the parent dialog
     */
    public fileSaveListener(EditorWindow window, JDialog dialog) {

        this.dialog = dialog;
        this.window = window;

    }

    /**
     * Get the current file and store it, then set the contents of the file ONLY if the file has an address. This
     * ensures reliability when checking for changes on window close. The file can then be closed as long as an address
     * is provided or retrieved from a file browser.
     *
     * @param actionEvent the event
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        saveFile(window.getCurrentFile());

    }

    /**
     * A method to save files, where if no file location is provided, it will open a new file browser where the user can
     * choose a new location. If the user cancels the file browser operation, the file will not be saved.
     */
    void saveFile(JETFile file) {

        String saveAddress = file.getFileLocation();

        //Check that the save location is not the default "Untitled" which is set when creating an empty JETFile
        // without an address.
        if (file.getFileLocation().equals("Untitled")) {

            //Open a new file browser to get a save URI
            saveAddress = new FileBrowser("Save").getURI();

            //Set the file's location to the chosen URI as long as the user didn't cancel.
            if (saveAddress != null) {
                file.setFileLocation(saveAddress);
            }

        }

        //If the file now has a save address, write the data (i.e if user didnt cancel file browser).
        if (!"Untitled".equals(saveAddress) && saveAddress != null) {

            //Set the text contents of the file to that inside the main textarea.
            file.setTextContents(window.getText());

            //Write the data
            new ReaderWriter(file);

        }


        //If a dialog was parsed in the constructor, then dispose of it now.
        if (dialog != null && saveAddress != null) {

            window.dispose();
        }
    }

}
