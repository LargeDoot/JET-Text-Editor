package com.company;

import com.company.listeners.findReplaceListeners.findButtonListener;
import com.company.listeners.findReplaceListeners.findReplaceCloseListener;
import com.company.listeners.windowListeners.windowCloseCancelListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FindDialog {

    final JDialog findReplace;

    final JLabel findLabel;
    final JTextField findTextField;
    final JButton findNextButton;
    final JButton cancelButton;

    final JCheckBox wrapAround, matchCase;
    final JRadioButton upRadio, downRadio;
    final ButtonGroup directionGroup;

    final JPanel container, searchContainer, buttonContainer, checkBoxContainer, leftGridContainer,
            rightGridContainer, searchGrid;

    int[] currentFindSelection;

    /**
     * Code for the find and replace dialog, containing:
     * - a text field for the search query
     * - a tick box for wrapping text
     * - a tick box for case sensitivity
     * - radio buttons for up/down search direction
     * - 'search' and 'cancel' buttons
     *
     * @param window a reference to the parent window (EditorWindow)
     */
    public FindDialog(EditorWindow window) {

        //Initialise the dialog
        findReplace = new JDialog(window, false);
        findReplace.setTitle("Find");

        //Add a windowListener to remove highlighting when closed
        findReplace.addWindowListener(new findReplaceCloseListener(window));

        //Initialise all the containers used for positioning in the dialog
        container = new JPanel();
        searchGrid = new JPanel();
        searchContainer = new JPanel();
        buttonContainer = new JPanel();
        checkBoxContainer = new JPanel();
        leftGridContainer = new JPanel();
        rightGridContainer = new JPanel();

        //Set the layout to grid layout for all containers
        BoxLayout layout = new BoxLayout(container, BoxLayout.LINE_AXIS);
        container.setLayout(layout);

        GridLayout buttonLayout = new GridLayout(1, 0);
        buttonContainer.setLayout(buttonLayout);

        GridLayout checkboxLayout = new GridLayout(3, 0);
        checkBoxContainer.setLayout(checkboxLayout);

        GridLayout leftGrid = new GridLayout(0, 1);
        leftGrid.setVgap(15);
        leftGridContainer.setLayout(leftGrid);

        GridLayout rightGrid = new GridLayout(0, 1);
        rightGridContainer.setLayout(rightGrid);

        GridLayout searchGridLayout = new GridLayout(0, 1);
        searchGridLayout.setVgap(10);
        searchGrid.setLayout(searchGridLayout);

        BoxLayout searchLayout = new BoxLayout(searchContainer, BoxLayout.LINE_AXIS);
        searchContainer.setLayout(searchLayout);


        //Set the close operation
        findReplace.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        //Add a border around the window so that components never touch the edge
        container.setBorder(new EmptyBorder(10, 10, 10, 10));

        //Make the dialog open in the center of the screen (not the top left)
        findReplace.setLocationRelativeTo(null);

        //Create buttons
        findNextButton = new JButton("Find Next");
        cancelButton = new JButton("Cancel");


        //Create text Field
        findTextField = new JTextField();

        //Add action listeners
        findNextButton.addActionListener(new findButtonListener(window, this));
        cancelButton.addActionListener(new windowCloseCancelListener(findReplace));

        //Add buttons
        buttonContainer.add(findNextButton);
        buttonContainer.add(cancelButton);

        //Create label
        findLabel = new JLabel("Find what:");

        //Create checkBoxes and radioButtons
        wrapAround = new JCheckBox("Wrap around");
        matchCase = new JCheckBox("Match case");

        upRadio = new JRadioButton("Up", false);
        upRadio.setMnemonic(1);
        downRadio = new JRadioButton("Down", true);
        downRadio.setMnemonic(0);

        //Create a button group and add the radio buttons
        directionGroup = new ButtonGroup();
        directionGroup.add(upRadio);
        directionGroup.add(downRadio);

        //Add components to containers
        rightGridContainer.add(findNextButton);
        rightGridContainer.add(cancelButton);
        rightGridContainer.add(Box.createVerticalGlue());

        searchContainer.add(findLabel);
        searchContainer.add(findTextField);

        searchGrid.add(searchContainer);
        searchGrid.add(Box.createVerticalGlue());

        checkBoxContainer.add(Box.createVerticalGlue());
        checkBoxContainer.add(wrapAround);
        checkBoxContainer.add(matchCase);

        buttonContainer.add(checkBoxContainer);
        buttonContainer.add(Box.createHorizontalStrut(50));
        buttonContainer.add(upRadio);
        buttonContainer.add(downRadio);

        leftGridContainer.add(searchGrid);
        leftGridContainer.add(buttonContainer);

        container.add(leftGridContainer);
        container.add(Box.createRigidArea(new Dimension(20, 0)));
        container.add(rightGridContainer);

        //Add the container to the dialog and make it visible
        findReplace.add(container);
        findReplace.pack();
        findReplace.setVisible(true);


    }

    /**
     * Getter
     *
     * @return the search box containing the text to be searched for
     */
    public JTextField getSearchBox() {

        return (findTextField);

    }

    /**
     * Returns the direction of the search, specified by the user via two radio buttons on the dialog.
     * directionGroup.getSelection().getMnemonic() == 1
     *
     * @return true if up, false if down
     */
    public boolean getDirection() {

        return upRadio.isSelected();
    }

    public boolean isCaseSensitive() {

        return matchCase.isSelected();
    }

    public int[] getCurrentFindSelection() throws NullPointerException {

        if (currentFindSelection == null) {

            throw new NullPointerException("The current selection has not yet been set.");

        } else {
            return currentFindSelection;
        }

    }

    public void setCurrentFindSelection(int[] currentFindSelection) {

        if (currentFindSelection.length == 2) {
            this.currentFindSelection = currentFindSelection;

        }

    }
}
