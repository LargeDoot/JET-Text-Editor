package com.company.listeners.findReplaceListeners;

import com.company.EditorWindow;
import com.company.FindDialog;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*******************************************************************************
 * Copyright (c) Ethan Wilson 2020.
 *
 * @author Ethan Wilson
 * @since 13-02-2020
 ******************************************************************************/
public class findButtonListener implements ActionListener {

    final EditorWindow window;
    final FindDialog dialog;

    final JTextField searchField;

    final JTextArea textArea;

    final Highlighter highlighter;
    final Highlighter.HighlightPainter painter;

    boolean direction, isCaseSensitive;
    int pos;

    /**
     * Constructor to create the listener.
     *
     * @param window the parent Editor Window
     * @param dialog the dialog that the listener belongs to
     */
    public findButtonListener(EditorWindow window, FindDialog dialog) {

        //Set variables parsed to constructor
        this.window = window;
        this.dialog = dialog;

        //Get search box and text area contents as Strings
        searchField = dialog.getSearchBox();
        textArea = window.getTextArea();

        //Set position to 0 so the search function will always have a start point
        pos = 0;

        //Set up highlighter stuff
        highlighter = window.getTextArea().getHighlighter();
        painter = new DefaultHighlighter.DefaultHighlightPainter(ColorUIResource.yellow);


    }

    /**
     * Search feature that will locate a string in a Document object. It takes into account some preferences in the find
     * dialog, such as case sensitivity and direction of search. If the term being searched for is found, then it is
     * highlighted yellow. Each new search will remove the previous highlighting.
     * <p>
     * If the term is not found, or the search reaches the end of the document, then a dialog is shown stating just
     * that!
     *
     * @param e event
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        //Get the search preferences from the dialog
        direction = dialog.getDirection();
        isCaseSensitive = dialog.isCaseSensitive();
        boolean direction = dialog.getDirection(); //True for up, false for down

        //Create found boolean
        boolean found = false;

        //Remove current highlighting
        highlighter.removeAllHighlights();

        //https://stackoverflow.com/questions/13437865/java-scroll-to-specific-text-inside-jtextarea
        //This code is a slightly modified version of the above

        String currentSearch;

        //Get the string being searched for
        String searchItem = searchField.getText();
        int searchItemLength = searchItem.length();

        //Check if the case sensitive box sis ticked, if it isn't then change the search term to lower case
        if (!isCaseSensitive) {
            searchItem = searchItem.toLowerCase();

        }

        //Check that there is some text in the search box
        if (searchItemLength > 0) {

            //Get the document object from the text area
            Document document = textArea.getDocument();

            //Loop while the document is still in scope
            while (!isEndOfDoc(searchItemLength, document)) {

                //Get the current selection
                try {
                    currentSearch = document.getText(pos, searchItemLength);

                    //Set lowercase if case sensitive is ticked in the dialog
                    if (!isCaseSensitive) {
                        currentSearch = currentSearch.toLowerCase();
                    }

                    //Check if it is what we a re looking for, if so then exit the loop
                    if (currentSearch.equals(searchItem)) {
                        found = true;
                        break;
                    }

                } catch (BadLocationException ex) {
                    System.err.println("Invalid location for search!");
                }

                //Change the position ready for the next iteration
                incrementOrDecrement();
            }

            if (found) {

                //Initialise a Rectangle variable
                Rectangle viewRect = null;

                //Try to set the rectangle shape to the current position in the text area
                try {
                    viewRect = textArea.modelToView(pos);
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }

                //Scroll the text area to show the found word
                textArea.scrollRectToVisible(viewRect);

                //Try to highlight the found string
                try {
                    highlighter.addHighlight(pos, pos + searchItemLength, painter);
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }

                //Set the start and end position of the found string for use in replacing
                int[] foundPosition = {pos, pos + searchItemLength};
                dialog.setCurrentFindSelection(foundPosition);

                //Set the position to the end of the word (or start, depending upon the direction being searched in)
                if (!direction) {
                    pos += searchItemLength;
                } else {
                    pos -= searchItemLength;

                }

            } else {

                //If the term being searched for is no found, show a dialog informing the user that the whole
                // document was searched
                JOptionPane.showMessageDialog(window, "The end of the document was reached");

                //Check if end of doc is reached, if so reset to beginning
                if (isEndOfDoc(searchItemLength, document) && !direction) {
                    pos = 0;

                } else if (isEndOfDoc(searchItemLength, document) && direction) {
                    pos = document.getLength() - searchItemLength;

                }

            }

        }

    }


    /**
     * Increments or decrements the pos variable depending on the search direction
     */
    private void incrementOrDecrement() {

        if (!direction) {
            pos++;
        } else {
            pos--;
        }

    }

    /**
     * Determines if the end of the document object has been reached by taking the current search position and adding
     * the search term's length, then comparing the result to the document's length.
     *
     * @param searchItemLength length of the search term
     * @param document         the current TextArea's document object
     * @return true if end of document is reached, otherwise false
     */
    private boolean isEndOfDoc(int searchItemLength, Document document) {

        if (!direction) {
            return (pos + searchItemLength >= document.getLength());

        } else {
            return (pos < 0);

        }
    }

}
