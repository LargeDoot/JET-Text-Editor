package com.company.listeners.findReplaceListeners;

import com.company.EditorWindow;
import com.company.ReplaceDialog;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class replaceButtonListener implements ActionListener {

    final EditorWindow window;
    final ReplaceDialog dialog;

    Highlighter highlighter;
    Highlighter.HighlightPainter painter;

    JTextArea textArea;

    /**
     * Constructor to create the listener.
     *
     * @param window the parent Editor Window
     * @param dialog the dialog that the listener belongs to
     */
    public replaceButtonListener(EditorWindow window, ReplaceDialog dialog) {

        this.window = window;
        this.dialog = dialog;

    }

    /**
     * Replaces text that has been located using the find function. Once replaced, text will be re-highlighted to
     * account for differences in length between origin and new strings
     *
     * @param e event
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        try {

            //Get an array of the start and end of the currently selected string
            int[] replacePos = dialog.getCurrentFindSelection();

            //Get the text area and do the replace
            textArea = window.getTextArea();
            textArea.replaceRange(dialog.getReplaceText(), replacePos[0], replacePos[1]);

            replacePos[1] = replacePos[0] + dialog.getReplaceText().length();
            dialog.setCurrentFindSelection(replacePos);

            //Create a highlighter to highlight the replaced text for continuity
            highlighter = window.getTextArea().getHighlighter();

            //Highlight the text
            try {
                painter = new DefaultHighlighter.DefaultHighlightPainter(ColorUIResource.yellow);
                highlighter.addHighlight(replacePos[0], replacePos[1], painter);

            } catch (BadLocationException ex) {
                ex.printStackTrace();
            }

            //catch a nullPointer if the find button hasn't been used yet
        } catch (NullPointerException exception) {
            JOptionPane.showMessageDialog(null, "Please use the find feature to find some text before replacing!");

        }

    }

}
