package com.company;

import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.UndoManager;


/*******************************************************************************
 * Copyright (c) Ethan Wilson 2020.
 *
 * The undoRedoManager class handles all of the undo/redo change tracking and functionality.
 *  *
 * //***See main class for references***
 *
 * @author Ethan Wilson
 * @since 13-02-2020
 ******************************************************************************/
public class undoRedoManager {

    final JTextArea textArea;

    final JMenuItem undoButton, redoButton;

    final UndoManager undoManager = new UndoManager();

    /**
     * Code for handling undo and redo operations.
     *
     * @param window     parent editor window
     * @param undoButton the undo button that the manager will use
     * @param redoButton the read button that the editor will use
     */
    public undoRedoManager(EditorWindow window, JMenuItem undoButton, JMenuItem redoButton) {

        this.textArea = window.getTextArea();
        this.undoButton = undoButton;
        this.redoButton = redoButton;

        //Add a listener to the text area's document object
        textArea.getDocument().addUndoableEditListener(e -> {
            undoManager.addEdit(e.getEdit());
            updateButtons();
        });

        //Add button listeners
        undoButton.addActionListener(e -> {
            try {
                undoManager.undo();
            } catch (CannotRedoException cre) {
                cre.printStackTrace();
            }
            updateButtons();
        });

        redoButton.addActionListener(e -> {
            try {
                undoManager.redo();
            } catch (CannotRedoException cre) {
                cre.printStackTrace();
            }
            updateButtons();
        });


    }


    public void updateButtons() {
        undoButton.setText(undoManager.getUndoPresentationName());
        redoButton.setText(undoManager.getRedoPresentationName());
        undoButton.setEnabled(undoManager.canUndo());
        redoButton.setEnabled(undoManager.canRedo());
    }


}
