package com.company;

import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.UndoManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//***See main class for references***

public class undoRedoManager {

    final JTextArea textArea;

    final JMenuItem undoButton, redoButton;

    final UndoManager undoManager = new UndoManager();

    public undoRedoManager(EditorWindow window, JMenuItem undoButton, JMenuItem redoButton) {
        this.textArea = window.getTextArea();

        this.undoButton = undoButton;
        this.redoButton = redoButton;


        textArea.getDocument().addUndoableEditListener(e -> {
            undoManager.addEdit(e.getEdit());
            updateButtons();
        });

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
