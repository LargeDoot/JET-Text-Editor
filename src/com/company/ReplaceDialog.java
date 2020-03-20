package com.company;

import com.company.listeners.findReplaceListeners.replaceButtonListener;

import javax.swing.*;

/*******************************************************************************
 * Copyright (c) Ethan Wilson 2020.
 *
 * An extension of the find dialog, with an extra text field where a user can type a replace string.
 *
 * @author Ethan Wilson
 * @since 13-02-2020
 ******************************************************************************/
public class ReplaceDialog extends FindDialog {

    final JPanel replaceBoxContainer;

    final JTextField replaceBox;
    final JButton replaceButton;

    /**
     * Constructor for creating the dialog.
     *
     * @param window parent editor window
     */
    public ReplaceDialog(EditorWindow window) {
        super(window);

        findReplace.setTitle("Find and Replace");

        //Create a container to house the replace box and associated label. This will be added to the searchGrid
        // container to display below the find box.
        replaceBoxContainer = new JPanel();

        //Create the replace field
        replaceBox = new JTextField();

        //Create the new replace button
        replaceButton = new JButton("Replace");
        replaceButton.addActionListener(new replaceButtonListener(window, this));

        //Create and set a box layout for the new container
        BoxLayout replaceLayout = new BoxLayout(replaceBoxContainer, BoxLayout.LINE_AXIS);
        replaceBoxContainer.setLayout(replaceLayout);

        //Add components to the container
        replaceBoxContainer.add(new JLabel("   Replace:"));
        replaceBoxContainer.add(replaceBox);

        //Remove the glue and insert the new container into its place
        searchGrid.remove(1);
        searchGrid.add(replaceBoxContainer);

        //Remove the cancel button, the glue, insert the replace button, and re-insert the cancel button
        rightGridContainer.remove(1);
        rightGridContainer.remove(1);
        rightGridContainer.add(replaceButton);
        rightGridContainer.add(cancelButton);
        rightGridContainer.add(Box.createVerticalGlue());

    }

    public String getReplaceText() {

        return replaceBox.getText();

    }
}
