package nz.ac.massey.gui;

import nz.ac.massey.SimpleKeybindAction;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Represents the menu bar at the top of the application.
 * Allows accessing of menus such as "File", "Search" etc.
 */
public class TextEditorMenuBar extends JMenuBar {

    /**
     * Injected instance of main GUI
     */
    private final TextEditorGUI gui;

    public TextEditorMenuBar(TextEditorGUI gui) {
        this.gui = gui;

        init();
    }

    /**
     * Get instance of the menu bar
     */
    public void init() {
        // file menu
        JMenu menuFile = new JMenu("File");
        menuFile.setMnemonic(KeyEvent.VK_F);
        add(menuFile);

        // file > new
        JMenuItem menuItemNew = new JMenuItem("New", KeyEvent.VK_N);
        menuItemNew
                .setAction(new SimpleKeybindAction(gui, "New",
                        KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK)));
        menuFile.add(menuItemNew);

        // file > open
        JMenuItem menuItemOpen = new JMenuItem("Open", KeyEvent.VK_O);
        menuItemOpen.setAction(
                new SimpleKeybindAction(gui, "Open",
                        KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK)));
        menuFile.add(menuItemOpen);

        // file > save
        JMenuItem menuItemSave = new JMenuItem("Save", KeyEvent.VK_S);
        menuItemSave.setAction(new SimpleKeybindAction(gui, "Save",
                KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK)));
        menuFile.add(menuItemSave);

        // file > save as
        JMenuItem menuItemSaveAs = new JMenuItem("Save As", KeyEvent.VK_A);
        menuItemSaveAs.setAction(new SimpleKeybindAction(gui, "Save As",
                KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.SHIFT_DOWN_MASK | InputEvent.CTRL_DOWN_MASK)));
        menuFile.add(menuItemSaveAs);

        // file > print
        JMenuItem menuItemPrint = new JMenuItem("Print", KeyEvent.VK_P);
        menuItemPrint.setAction(new SimpleKeybindAction(gui, "Print",
                KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK)));
        menuFile.add(menuItemPrint);

        // file > exit
        JMenuItem menuItemExit = new JMenuItem("Exit", KeyEvent.VK_X);
        menuItemExit.setAction(new SimpleKeybindAction(gui, "Exit",
                KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK)));
        menuFile.add(menuItemExit);

        // search menu
        JMenu menuSearch = new JMenu("Search");
        menuSearch.setMnemonic(KeyEvent.VK_S);
        add(menuSearch);

        // search > search
        JMenuItem menuItemSearch = new JMenuItem("Search", KeyEvent.VK_S);
        menuSearch.add(menuItemSearch);
        menuItemSearch.setAction(
                new SimpleKeybindAction(gui, "Search",
                        KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK)));

        // view menu
        JMenu menuView = new JMenu("View");
        menuView.setMnemonic(KeyEvent.VK_V);
        add(menuView);

        // view > word wrap
        JCheckBoxMenuItem menuItemWrap = new JCheckBoxMenuItem("Word Wrap");
        menuItemWrap.setMnemonic(KeyEvent.VK_W);
        menuItemWrap.setAction(
                new SimpleKeybindAction(gui, "Word Wrap",
                        KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_DOWN_MASK)));
        menuView.add(menuItemWrap);

        // view > status bar
        JCheckBoxMenuItem menuItemStatusbar = new JCheckBoxMenuItem("Status Bar");
        menuItemStatusbar.setMnemonic(KeyEvent.VK_S);
        menuItemStatusbar.setAction(new SimpleKeybindAction(gui, "Status Bar",
                KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_DOWN_MASK)));
        menuItemStatusbar.setSelected(true);
        menuView.add(menuItemStatusbar);

        // time and date menu
        JMenu timeAndDate = new JMenu("Time & Date");
        timeAndDate.setMnemonic(KeyEvent.VK_T);
        add(timeAndDate);

        // time and date > time and date
        JMenuItem menuItemTimeDate = new JMenuItem("Time & Date", KeyEvent.VK_T);
        menuItemTimeDate.setAction(new SimpleKeybindAction(gui, "Time and Date",
                KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0)));
        timeAndDate.add(menuItemTimeDate);

        // help menu
        JMenu menuHelp = new JMenu("Help");
        menuHelp.setMnemonic(KeyEvent.VK_H);
        add(menuHelp);

        // help > about
        JMenuItem menuItemAbout = new JMenuItem("About", KeyEvent.VK_A);
        menuHelp.add(menuItemAbout);
        menuItemAbout.setAction(new SimpleKeybindAction(gui, "About", null));
    }
}