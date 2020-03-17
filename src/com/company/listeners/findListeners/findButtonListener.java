package com.company.listeners.findListeners;

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

public class findButtonListener implements ActionListener {

    final EditorWindow window;
    final FindDialog dialog;

    final JTextField searchField;

    final JTextArea textArea;

    final Highlighter highlighter;
    final Highlighter.HighlightPainter painter;

    boolean direction, isCaseSensitive;
    int pos;

    public findButtonListener(EditorWindow window, FindDialog dialog) {

        this.window = window;
        this.dialog = dialog;

        searchField = dialog.getSearchBox();

        textArea = window.getTextArea();

        pos = 0;

        highlighter = window.getTextArea().getHighlighter();
        painter = new DefaultHighlighter.DefaultHighlightPainter(ColorUIResource.yellow);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        direction = dialog.getDirection();
        isCaseSensitive = dialog.isCaseSensitive();

        boolean found = false;
        boolean direction = dialog.getDirection(); //True for up, false for down

        //remove current highlighting
        highlighter.removeAllHighlights();

        //https://stackoverflow.com/questions/13437865/java-scroll-to-specific-text-inside-jtextarea
        //This code is a slightly modified version of the above

        String searchItem = searchField.getText();
        String currentSearch;
        int searchItemLength = searchItem.length();

        if (!isCaseSensitive) {

            searchItem = searchItem.toLowerCase();
        }

        textArea.requestFocusInWindow();

        if (searchItemLength > 0) {

            Document document = textArea.getDocument();

            while (isWithinDoc(searchItemLength, document)) {


                try {

                    currentSearch = document.getText(pos, searchItemLength);

                    if (!isCaseSensitive) {
                        currentSearch = currentSearch.toLowerCase();
                    }

                    if (currentSearch.equals(searchItem)) {
                        found = true;
                        break;
                    }
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }

                incrementOrDecrement();

            }

            if (found) {

                System.out.println("Found!");

                Rectangle viewRect = null;
                try {
                    viewRect = textArea.modelToView(pos);
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }

                textArea.scrollRectToVisible(viewRect);

//                setCaratPos(searchItemLength);
//                textArea.moveCaretPosition(pos);

                try {
                    highlighter.addHighlight(pos, pos + searchItemLength, painter);
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }

                if (!direction) {
                    pos += searchItemLength;
                } else {
                    pos -= searchItemLength;

                }

            } else {

                JOptionPane.showMessageDialog(window, "The end of the document was reached");

                //Check if end of doc is reached, if so reset to beginning
                if (isEndOfDoc(searchItemLength, document) && !direction) {
                    pos = 0;

                } else if (isEndOfDoc(searchItemLength, document) && direction) {
                    pos = document.getLength() - searchItemLength;

                }

            }

            window.toFront();
//            window.requestFocus();

        }
    }

    private boolean isWithinDoc(int searchItemLength, Document document) {

        if (!direction) {
            return pos + searchItemLength <= document.getLength();

        } else {

            return pos - searchItemLength >= 0;
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

    private boolean isEndOfDoc(int itemLength, Document document) {

        if (!direction) {
            return (pos + itemLength >= document.getLength());

        } else {
            return (pos - itemLength < 0);

        }
    }

}
