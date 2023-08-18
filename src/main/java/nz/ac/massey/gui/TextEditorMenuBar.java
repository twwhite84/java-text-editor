package nz.ac.massey.gui;

import nz.ac.massey.SimpleKeybindAction;

import javax.swing.*;
import java.awt.*;
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
        menuItemNew.setAction(new SimpleKeybindAction(gui, "New", KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK)));
        menuFile.add(menuItemNew);

        // file > open
        JMenuItem menuItemOpen = new JMenuItem("Open", KeyEvent.VK_O);
        menuItemOpen.setAction(new SimpleKeybindAction(gui, "Open", KeyStroke.getKeyStroke(KeyEvent.VK_O, Event.CTRL_MASK)));
        menuFile.add(menuItemOpen);

        // file > save
        JMenuItem menuItemSave = new JMenuItem("Save", KeyEvent.VK_S);
        menuFile.add(menuItemSave);
        menuItemSave.addActionListener(e -> JOptionPane.showMessageDialog(menuItemSave, "File > Save not implemented yet", "Todo", 0));

        // file > settings
        JMenuItem menuItemSettings = new JMenuItem("Settings", KeyEvent.VK_T);
        menuFile.add(menuItemSettings);
        menuItemSettings.addActionListener(e -> JOptionPane.showMessageDialog(menuItemSettings, "File > Settings not implemented yet", "Todo", 0));

        // file > print
        JMenuItem menuItemPrint = new JMenuItem("Print", KeyEvent.VK_P);
        menuFile.add(menuItemPrint);
        menuItemPrint.addActionListener(e -> JOptionPane.showMessageDialog(menuItemSettings, "File > Print not implemented yet", "Todo", 0));

        // file > exit
        JMenuItem menuItemExit = new JMenuItem("Exit", KeyEvent.VK_X);
        menuFile.add(menuItemExit);
        menuItemExit.addActionListener(e -> JOptionPane.showMessageDialog(menuItemSettings, "File > Exit not implemented yet", "Todo", 0));

        // search menu
        JMenu menuSearch = new JMenu("Search");
        menuSearch.setMnemonic(KeyEvent.VK_S);
        add(menuSearch);

        // search > search
        JMenuItem menuItemSearch = new JMenuItem("Search", KeyEvent.VK_S);
        menuSearch.add(menuItemSearch);
        menuItemSearch.addActionListener(e -> JOptionPane.showMessageDialog(menuItemSearch, "Search not implemented yet", "Todo", 0));

        // view menu
        JMenu menuView = new JMenu("View");
        menuView.setMnemonic(KeyEvent.VK_V);
        add(menuView);

        // view > word wrap
        JCheckBoxMenuItem menuItemWrap = new JCheckBoxMenuItem("Word Wrap");
        menuItemWrap.setMnemonic(KeyEvent.VK_W);
        menuView.add(menuItemWrap);

        menuItemWrap.addActionListener(e -> {
            // not actually implemented yet, this just toggles the indicator
            gui.getGuiContentPane().toggleWrapIndicator(menuItemWrap.isSelected());
        });

        // view > status bar
        JCheckBoxMenuItem menuItemStatusbar = new JCheckBoxMenuItem("Status Bar");
        menuItemStatusbar.setMnemonic(KeyEvent.VK_S);
        menuView.add(menuItemStatusbar);
        menuItemStatusbar.addActionListener(e -> {
            String message;
            if (menuItemStatusbar.isSelected())
                message = "Status bar on";
            else
                message = "Status bar off";
            JOptionPane.showMessageDialog(menuItemWrap, message + "\nStatus bar not implemented yet", "Todo", 0);
        });

        // help menu
        JMenu menuHelp = new JMenu("Help");
        menuHelp.setMnemonic(KeyEvent.VK_H);
        add(menuHelp);

        // help > about
        JMenuItem menuItemAbout = new JMenuItem("About", KeyEvent.VK_A);
        menuHelp.add(menuItemAbout);
        menuItemAbout.addActionListener(e -> JOptionPane.showMessageDialog(menuItemAbout, "Help > About not implemented yet", "Todo", 0));
    }
}