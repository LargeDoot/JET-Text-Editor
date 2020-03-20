package com.company;

import com.company.listeners.fileMenuListeners.fileExitListener;
import com.company.listeners.fileMenuListeners.fileSaveListener;
import com.company.listeners.windowListeners.windowCloseCancelListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/*******************************************************************************
 * Copyright (c) Ethan Wilson 2020.
 *
 * The save dialog class contains the code to create a dialog that prompts the user to save any unsaved changes
 * before exiting JET.
 *
 * @author Ethan Wilson
 * @since 13-02-2020
 ******************************************************************************/
public class SaveDialog {

    final JDialog checkSave;

    final JLabel saveCheckLabel;
    final JButton saveButton;
    final JButton dontSaveButton;
    final JButton cancelButton;

    /**
     * Creates a save dialog, used when the user tries to close an editor window and there are unsaved changes.
     *
     * @param window parent editor window
     */
    public SaveDialog(EditorWindow window) {

        checkSave = new JDialog(window, true);
        checkSave.setTitle("Java Editor for Text");

        JPanel buttonContainer = new JPanel();
        JPanel container = new JPanel();

        //Set the layout to grid layout for both containers
        GridLayout layout = new GridLayout(0, 1);
        layout.setHgap(10);
        layout.setVgap(10);

        container.setLayout(layout);

        GridLayout buttonLayout = new GridLayout(1, 0);
        buttonLayout.setHgap(10);


        buttonContainer.setLayout(buttonLayout);

        //Set the close operation
        checkSave.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        //Add a border around the window so that components never touch the edge
        container.setBorder(new EmptyBorder(10, 10, 10, 10));

        //Set the default dialog size
        checkSave.setSize(600, 200);

        //Make the dialog open in the center of the screen (not the top left)
        checkSave.setLocationRelativeTo(null);

        //Create buttons
        saveButton = new JButton("Save");
        dontSaveButton = new JButton("Don't Save");
        cancelButton = new JButton("Cancel");

        //Add action listeners
        saveButton.addActionListener(new fileSaveListener(window, checkSave));
        dontSaveButton.addActionListener(new fileExitListener(window, false));
        cancelButton.addActionListener(new windowCloseCancelListener(checkSave));

        //Add buttons
        buttonContainer.add(saveButton);
        buttonContainer.add(dontSaveButton);
        buttonContainer.add(cancelButton);

        //Create label
        String fileLocation = window.getCurrentFile().getFileLocation();
        String fileName = fileLocation.replaceAll(".+\\\\", "");
        String saveMessage = String.format("Do you want to save changes to '%s'", fileName);
        saveCheckLabel = new JLabel(saveMessage);
        saveCheckLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24));

        //Add components to container
        container.add(saveCheckLabel);
        container.add(buttonContainer);

        //Add the container to the dialog and make it visible
        checkSave.add(container);
        checkSave.setVisible(true);


    }

}
