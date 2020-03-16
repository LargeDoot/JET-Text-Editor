package com.company.preferences;

import com.company.EditorWindow;
import com.company.listeners.prefsListeners.prefsFontListener;
import com.company.listeners.prefsListeners.prefsFontSizeListener;
import com.company.listeners.prefsListeners.prefsFontStyleListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PreferencesDialog {

    final JDialog prefs;

    final JLabel fontLabel;
    final JLabel fontStyleLabel;
    final JLabel fontSize;

    final JSpinner fontSizeSelector;
    final SpinnerModel fontSizeModel;

    final JComboBox<String> fontList;
    final JComboBox<String> fontStyleList;

    private final DefaultComboBoxModel<String> styleModel;

    public PreferencesDialog(EditorWindow window) {

        prefs = new JDialog(window, true);
        prefs.setTitle("Font Preferences");

        JPanel container = new JPanel();

        //Set the layout to grid layout
        GridLayout layout = new GridLayout(0, 2);
        layout.setHgap(10);
        layout.setVgap(10);
        container.setLayout(layout);

        //Set the close operation
        prefs.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        //Add a border around the window so that components never touch the edge
        container.setBorder( new EmptyBorder(10, 10, 10, 10) );

        //Add font size spinner components
        fontSize = new JLabel("Font size");
        container.add(fontSize);

        int currentFontSize = window.getPrefs().getFontSize();
        fontSizeModel = new SpinnerNumberModel(currentFontSize, 11, 72, 1);
        fontSizeModel.addChangeListener(new prefsFontSizeListener(window));

        fontSizeSelector = new JSpinner(fontSizeModel);
        container.add(fontSizeSelector);


        //Add font selector components
        fontLabel = new JLabel("Font Selector");
        container.add(fontLabel);

        fontList = new JComboBox<>(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
        fontList.addItemListener(new prefsFontListener(this));
        container.add(fontList);

        //Add font style selector components
        fontStyleLabel = new JLabel("Font Style Selector");
        container.add(fontStyleLabel);

        //Create a combo box model to allow changes to the contents while the program runs
        styleModel = new DefaultComboBoxModel<>();

        //Create the combo box with the new model
        fontStyleList = new JComboBox<>(styleModel);
        fontStyleList.addItemListener(new prefsFontStyleListener(window));
        container.add(fontStyleList);


        //Set the default dialog size
        prefs.setSize(400, 200);

        //Make the dialog open in the center of the screen (not the top left)
        prefs.setLocationRelativeTo(null);

        //Align the font components with the actual preferences
        String initialFontFamily = window.getPrefs().getFontName();
        String initialFont = window.getPrefs().getFontName();

        initialFontFamily = initialFontFamily.replace(" Bold", "");
        initialFontFamily = initialFontFamily.replace(" Italic", "");

        fontList.setSelectedItem(initialFontFamily);
        fontStyleList.setSelectedItem(initialFont);

        //Align the font size with the actual preferences
//        fontSizeSelector.setModel();

        //Add the container to the dialog and make it visible
        prefs.add(container);
        prefs.setVisible(true);


    }

    public DefaultComboBoxModel<String> getDefaultComboBoxModel() {

        return styleModel;
    }

// --Commented out by Inspection START (12/03/2020 10:05):
//    public JComboBox<String> getStyleComboBox() {
//
//        return fontStyleList;
//    }
// --Commented out by Inspection STOP (12/03/2020 10:05)

}
