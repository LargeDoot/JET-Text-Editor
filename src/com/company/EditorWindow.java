package com.company;

import com.company.listeners.editMenuListeners.*;
import com.company.listeners.fileMenuListeners.*;
import com.company.listeners.formatMenuListeners.*;
import com.company.listeners.helpListener;
import com.company.listeners.viewMenuListeners.zoomMenuListeners.*;
import com.company.listeners.windowListeners.textKeyListener;
import com.company.listeners.windowListeners.textMouseListener;
import com.company.listeners.windowListeners.windowCloseListener;
import com.company.listeners.viewMenuListeners.viewToolbarToggleListener;

import com.company.preferences.TextPrefs;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

@SuppressWarnings("DuplicatedCode")
public class EditorWindow extends JFrame {

    final JPanel statusBar;

    private JScrollPane scrollPane;
    private JTextArea textArea;

    private JToolBar toolbar;

    private JMenuBar menuBar;

    private JMenuItem editUndo, editRedo;

    private final JLabel lineNum;
    private final JLabel lineColumn;
    private final JLabel zoomLevelLabel;

    private JETFile currentFile;

    private final TextPrefs windowPreferences;

    private Font windowFont;

    private int zoomLevel, fontSize;

    JCheckBoxMenuItem formatWordWrap, viewToolbarToggle;

    public EditorWindow() {

        super(" Jet Editor | 'Untitled.txt'");

        addWindowListener(new windowCloseListener(this));

        //Add the system's look and feel to make the window look normal in the current OS
        //Set the font size to the system's default "TextField" font size (otherwise it is very small on some screens)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            windowFont = UIManager.getFont("TextField.font");
            UIManager.getDefaults().put("TextArea.font", windowFont);
        } catch (Exception ignored) {
        }

        //Create objects for the file, prefs and style
        currentFile = new JETFile();
        windowPreferences = new TextPrefs();

        //Set the icon for the editor window
        ImageIcon jetIcon = new ImageIcon("src/com/company/resources/JETLogo.png");
        setIconImage(jetIcon.getImage());

        //Set the layout to border layout
        BorderLayout layout = new BorderLayout(50, 0);
        setLayout(layout);

        //Initialize the menu bar
        initMenuBar();

        //Add and set the menubar
        add(menuBar);
        setJMenuBar(menuBar);

        //Initialize the tool bar
        initToolbar();

        //Add the toolBar
        add(toolbar, BorderLayout.NORTH);

        //Add status bar to bottom of the window
        statusBar = new JPanel();
        statusBar.setLayout(new BoxLayout(statusBar, BoxLayout.LINE_AXIS));

        Font statusFont = new Font("Segoe UI", Font.PLAIN, 12);

        lineNum = new JLabel(" Ln 0, \t ");
        lineNum.setFont(statusFont);
        lineNum.setBorder(new EmptyBorder(4, 0, 4, 0));

        lineColumn = new JLabel("Col 0 ");
        lineColumn.setFont(statusFont);

        zoomLevelLabel = new JLabel(" 100% ");
        zoomLevelLabel.setFont(statusFont);

        JLabel charSet = new JLabel(" UTF-8 \t\t");
        charSet.setFont(statusFont);


        //Create separators for use in the bar
        JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
        JSeparator separator2 = new JSeparator(SwingConstants.VERTICAL);
        JSeparator separator3 = new JSeparator(SwingConstants.VERTICAL);
        separator.setMaximumSize(new Dimension(0, 20));
        separator2.setMaximumSize(new Dimension(0, 20));
        separator3.setMaximumSize(new Dimension(0, 20));

        statusBar.add(Box.createHorizontalGlue());
        statusBar.add(separator3);
        statusBar.add(lineNum);
        statusBar.add(lineColumn);
        statusBar.add(Box.createRigidArea(new Dimension(30, 0)));
        statusBar.add(separator);
        statusBar.add(zoomLevelLabel);
        statusBar.add(Box.createRigidArea(new Dimension(30, 0)));
        statusBar.add(separator2);
        statusBar.add(charSet);

        add(statusBar, BorderLayout.SOUTH);

        //Initialise the text area
        initTextArea();

        //Initialise the scroll area
        initScrollPane();

        //Add an UndoManager
        createUndoManager();

        //Add the scroll pane
        add(scrollPane, BorderLayout.CENTER);

        //Set the close operation to dispose of the window when closed, allowing for multiple windows
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        // Align the formatting to the preferences
        setPrefFormatting();
        setMiscPrefs();

        //Pack the window
        pack();

        //Make the window open in the center of the screen (not the top left)
        setLocationRelativeTo(null);
    }

    /**
     * creates a new resetUndoManager object
     */
    private void createUndoManager() {
        //Create an undoRedoManager object to add functionality to the buttons
        new undoRedoManager(this, editUndo, editRedo);
    }


    /**
     * Method to set the preferences when the application starts
     */
    private void setPrefFormatting() {

        String fontName = windowPreferences.getFontName();
        int fontSize = windowPreferences.getFontSize();

        setWindowFont(new Font(fontName, Font.PLAIN, fontSize));

    }

    /**
     * Method to set up the toolbar
     */
    private void initToolbar() {

        JButton toolbarOpenButton, toolbarSaveButton, toolbarExitButton;
        JButton toolbarZoomInButton, toolbarZoomOutButton;

        //Create icons for toolbar
        Icon openIcon, saveIcon, exitIcon, zoomInIcon, zoomOutIcon;
        openIcon = new ImageIcon("src/com/company/resources/OpenIcon20x20.png");
        saveIcon = new ImageIcon("src/com/company/resources/SaveIcon20x20.png");
        exitIcon = new ImageIcon("src/com/company/resources/ExitIcon20x20.png");
        zoomInIcon = new ImageIcon("src/com/company/resources/ZoomInIcon20x20.png");
        zoomOutIcon = new ImageIcon("src/com/company/resources/ZoomOutIcon20x20.png");

        //Setup toolbar and buttons for toolbar
        toolbarOpenButton = new JButton(openIcon);
        toolbarSaveButton = new JButton(saveIcon);
        toolbarExitButton = new JButton(exitIcon);
        toolbarZoomInButton = new JButton(zoomInIcon);
        toolbarZoomOutButton = new JButton(zoomOutIcon);

        //Add action listeners
        toolbarOpenButton.addActionListener(new fileOpenListener(this));
        toolbarSaveButton.addActionListener(new fileSaveListener(this));
        toolbarExitButton.addActionListener(new fileExitListener(this, true));
        toolbarZoomInButton.addActionListener(new zoomInListener(this));
        toolbarZoomOutButton.addActionListener(new zoomOutListener(this));

        //Create the toolbar
        toolbar = new JToolBar();

        toolbar.setMaximumSize(new Dimension(1000, 20));

        //Add things to the toolbar
        toolbar.add(toolbarOpenButton);
        toolbar.add(toolbarSaveButton);
        toolbar.add(toolbarZoomInButton);
        toolbar.add(toolbarZoomOutButton);
        toolbar.add(Box.createHorizontalGlue());
        toolbar.add(toolbarExitButton);
    }

    /**
     * Method to set up the menuBar
     */
    private void initMenuBar() {

        JMenu menuFile, menuEdit, menuFormat, menuView, menuHelp;
        JMenuItem fileNew, fileSave, fileSaveAs, fileOpen, fileExit;
        JMenuItem editCopy, editPaste, editFind, editReplace;
        JMenuItem formatFont;
        JMenu viewZoom;
        JMenuItem zoomIn, zoomOut, zoomReset;
        JMenuItem helpHelp;

        JSeparator fileSeparator1, editSeparator1;

        //Setup menus and menu buttons for a menubar
        menuBar = new JMenuBar();

        menuFile = new JMenu("File  ");
        menuEdit = new JMenu("Edit  ");
        menuFormat = new JMenu("Format  ");
        menuView = new JMenu("View  ");
        menuHelp = new JMenu("Help  ");

        //Set menu mnemonics
        menuFile.setMnemonic('f');
        menuEdit.setMnemonic('e');
        menuFormat.setMnemonic('o');
        menuView.setMnemonic('v');
        menuHelp.setMnemonic('h');

        //Create file menu items
        fileNew = new JMenuItem("New");
        fileSave = new JMenuItem("Save");
        fileSaveAs = new JMenuItem("Save As");
        fileOpen = new JMenuItem("Open");
        fileExit = new JMenuItem("Exit");

        //Add mnemonics to the menu items
        fileNew.setMnemonic('n');
        fileSave.setMnemonic('s');
        fileSaveAs.setMnemonic('a');
        fileOpen.setMnemonic('o');
        fileExit.setMnemonic('e');

        //Set shortcuts
        fileNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        fileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        fileSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask() | InputEvent.SHIFT_DOWN_MASK));
        fileOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        fileExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

        //Add action listeners
        fileNew.addActionListener(new fileNewListener());
        fileOpen.addActionListener(new fileOpenListener(this));
        fileSave.addActionListener(new fileSaveListener(this));
        fileSaveAs.addActionListener(new fileSaveAsListener(this));
        fileExit.addActionListener(new fileExitListener(this, true));

        //Create edit menu items
        editUndo = new JMenuItem("Undo");
        editRedo = new JMenuItem("Redo");
        editCopy = new JMenuItem("Copy");
        editPaste = new JMenuItem("Paste");
        editFind = new JMenuItem("Find");
        editReplace = new JMenuItem("Replace");

        //Add mnemonics to the menu items
        editUndo.setMnemonic('u');
        editRedo.setMnemonic('e');
        editCopy.setMnemonic('c');
        editPaste.setMnemonic('o');
        editFind.setMnemonic('f');
        editReplace.setMnemonic('r');

        //Set shortcuts
        editUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        editRedo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        editCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        editPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        editFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        editReplace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

        //Add action listeners
        editCopy.addActionListener(new editCopyListener(this));
        editPaste.addActionListener(new editPasteListener(this));
        editFind.addActionListener(new editFindListener(this));
        editReplace.addActionListener(new editReplaceListener(this));

        //Create format menu items
        formatWordWrap = new JCheckBoxMenuItem("Word Wrap", true);
        formatFont = new JMenuItem("Font...");

        //Add mnemonics to the menu items
        formatWordWrap.setMnemonic('w');
        formatFont.setMnemonic('f');

        //Add action listeners to the zoom and format buttons
        formatWordWrap.addItemListener(new formatWrapListener(this));
        formatFont.addActionListener(new formatFontListener(this));

        //Create view menu items
        viewZoom = new JMenu("Zoom");
        zoomIn = new JMenuItem("Zoom In");
        zoomOut = new JMenuItem("Zoom Out");
        zoomReset = new JMenuItem("Reset to default");

        viewToolbarToggle = new JCheckBoxMenuItem("Show toolbar", true);

        //Add mnemonics to the menu items
        viewZoom.setMnemonic('z');
        zoomIn.setMnemonic('i');
        zoomOut.setMnemonic('o');
        zoomReset.setMnemonic('r');
        viewToolbarToggle.setMnemonic('t');


        //Add action listeners
        zoomIn.addActionListener(new zoomInListener(this));
        zoomOut.addActionListener(new zoomOutListener(this));
        zoomReset.addActionListener(new zoomResetListener(this));

        viewToolbarToggle.addItemListener(new viewToolbarToggleListener(this));

        //Create help menu item
        helpHelp = new JMenuItem("Help");

        //Add mnemonic to the menu item
        helpHelp.setMnemonic('h');

        //Add action listener
        helpHelp.addActionListener(new helpListener());

        //Create some separators
        fileSeparator1 = new JSeparator();
        editSeparator1 = new JSeparator();

        //Add everything to the menu
        menuFile.add(fileNew);
        menuFile.add(fileSave);
        menuFile.add(fileSaveAs);
        menuFile.add(fileOpen);
        menuFile.add(fileSeparator1);
        menuFile.add(fileExit);

        menuEdit.add(editUndo);
        menuEdit.add(editRedo);
        menuEdit.add(editCopy);
        menuEdit.add(editPaste);
        menuEdit.add(editSeparator1);
        menuEdit.add(editFind);
        menuEdit.add(editReplace);

        menuFormat.add(formatWordWrap);
        menuFormat.add(formatFont);

        viewZoom.add(zoomIn);
        viewZoom.add(zoomOut);
        viewZoom.add(zoomReset);

        menuView.add(viewZoom);
        menuView.add(viewToolbarToggle);

        menuHelp.add(helpHelp);

        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuFormat);
        menuBar.add(menuView);
        menuBar.add(menuHelp);

    }

    /**
     * Method to set up the scroll pane
     */
    private void initScrollPane() {

        scrollPane = new JScrollPane(textArea);

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        scrollPane.setPreferredSize(new Dimension(1000, 550));

    }

    /**
     * Method to set up the text area
     */
    private void initTextArea() {
        textArea = new JTextArea(50, 50);
        textArea.setLineWrap(true);
        textArea.addKeyListener(new textKeyListener(this));
        textArea.addMouseListener(new textMouseListener(this));
    }

    /**
     * Method to set all the misc preferences such as zoom when the application starts
     */
    private void setMiscPrefs() {

        //Set zoom level
        setZoomLevel(windowPreferences.getZoomAmount());

        //Set the toolbar visibility
        boolean toolBarVisible = windowPreferences.isShowToolbar();
        toolbar.setVisible(toolBarVisible);
        viewToolbarToggle.setSelected(toolBarVisible);

        //Set the text wrapping
        boolean textWrapped = windowPreferences.isWrapText();
        setTextWrap(textWrapped);
        formatWordWrap.setSelected(textWrapped);

    }

    //Setters and getters
    public String getText() {
        return textArea.getText();
    }

    public JETFile getCurrentFile() {
        return currentFile;
    }

    public TextPrefs getPrefs() {
        return windowPreferences;
    }

    public Font getWindowFont() {
        return windowFont;
    }

    public void setWindowFont(Font windowFont) {

        //Set the window font, set the text area font to the new one, and update the fontSize
        this.windowFont = windowFont;
        this.textArea.setFont(windowFont);
        this.fontSize = windowFont.getSize() - zoomLevel;
    }

    public JLabel getLineColumn() {
        return lineColumn;
    }

    public JLabel getLineNum() {
        return lineNum;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public JToolBar getToolbar() {
        return toolbar;
    }

    public int getZoomLevel() {
        return zoomLevel;
    }

    public void setZoomLevel(int zoomLevel) {

        //Set the size of the font based upon the zoom level
        this.zoomLevel = zoomLevel;
        setWindowFont(new Font(windowFont.getFontName(), Font.PLAIN, (fontSize + this.zoomLevel)));

        //Set label to the correct zoom level
        String zoomLevelText = String.format(" %s%% ", (100 + zoomLevel));
        zoomLevelLabel.setText(zoomLevelText);

    }

    public void setFile(JETFile file) {
        currentFile = file;
        textArea.setText(file.getTextContents());
    }

    public void setTextWrap(Boolean wrap) {

        this.textArea.setLineWrap(wrap);

    }

}
