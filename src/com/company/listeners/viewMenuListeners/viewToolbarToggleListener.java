package com.company.listeners.viewMenuListeners;

import com.company.EditorWindow;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/*******************************************************************************
 * Copyright (c) Ethan Wilson 2020.
 *
 * @author Ethan Wilson
 * @since 13-02-2020
 ******************************************************************************/
public class viewToolbarToggleListener implements ItemListener {

    final EditorWindow window;

    /**
     * Constructor to create the listener.
     *
     * @param window the parent Editor Window
     */
    public viewToolbarToggleListener(EditorWindow window) {

        this.window = window;

    }

    /**
     * Check for a change of state, and set the toolbar visibility to true or false, depending on if the menu item is
     * selected or deselected (selected means toolbar is shown).
     *
     * @param e state change event
     */
    @Override
    public void itemStateChanged(ItemEvent e) {

        boolean result;

        //Determine if menu item is selected or not
        result = e.getStateChange() == ItemEvent.SELECTED;

        //Set prefs and window variable
        window.getToolbar().setVisible(result);
        window.getPrefs().setShowToolbar(result);


    }
}
