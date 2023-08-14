package nz.ac.massey;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class GUIMenuBar {
  private GUI gui;

  public GUIMenuBar(GUI gui) {
    this.gui = gui;
  }

  public JMenuBar getMenuBar() {
    JMenuBar menuBar = new JMenuBar();

    // file menu
    JMenu menuFile = new JMenu("File");
    menuFile.setMnemonic(KeyEvent.VK_F);
    menuBar.add(menuFile);

    // file > new
    JMenuItem menuItemNew = new JMenuItem("New", KeyEvent.VK_N);
    menuFile.add(menuItemNew);

    menuItemNew.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(menuItemNew, "File > New not implemented yet", "Todo", 0);
      }

    });

    // file > open
    JMenuItem menuItemOpen = new JMenuItem("Open", KeyEvent.VK_O);
    menuFile.add(menuItemOpen);
    menuItemOpen.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(menuItemOpen, "File > Open not implemented yet", "Todo", 0);
      }

    });

    // file > save
    JMenuItem menuItemSave = new JMenuItem("Save", KeyEvent.VK_S);
    menuFile.add(menuItemSave);
    menuItemSave.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(menuItemSave, "File > Save not implemented yet", "Todo", 0);
      }

    });

    // file > settings
    JMenuItem menuItemSettings = new JMenuItem("Settings", KeyEvent.VK_T);
    menuFile.add(menuItemSettings);
    menuItemSettings.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(menuItemSettings, "File > Settings not implemented yet", "Todo", 0);
      }

    });

    // file > print
    JMenuItem menuItemPrint = new JMenuItem("Print", KeyEvent.VK_P);
    menuFile.add(menuItemPrint);
    menuItemPrint.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(menuItemSettings, "File > Print not implemented yet", "Todo", 0);
      }

    });

    // file > exit
    JMenuItem menuItemExit = new JMenuItem("Exit", KeyEvent.VK_X);
    menuFile.add(menuItemExit);
    menuItemExit.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(menuItemSettings, "File > Exit not implemented yet", "Todo", 0);
      }

    });

    // search menu
    JMenu menuSearch = new JMenu("Search");
    menuSearch.setMnemonic(KeyEvent.VK_S);
    menuBar.add(menuSearch);

    // search > search
    JMenuItem menuItemSearch = new JMenuItem("Search", KeyEvent.VK_S);
    menuSearch.add(menuItemSearch);
    menuItemSearch.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(menuItemSearch, "Search not implemented yet", "Todo", 0);
      }

    });

    // view menu
    JMenu menuView = new JMenu("View");
    menuView.setMnemonic(KeyEvent.VK_V);
    menuBar.add(menuView);

    // view > word wrap
    JCheckBoxMenuItem menuItemWrap = new JCheckBoxMenuItem("Word Wrap");
    menuItemWrap.setMnemonic(KeyEvent.VK_W);
    menuView.add(menuItemWrap);

    menuItemWrap.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        // not actually implemented yet, this just toggles the indicator
        gui.guiContentPane.toggleWrapIndicator(menuItemWrap.isSelected());
      }

    });

    // view > status bar
    JCheckBoxMenuItem menuItemStatusbar = new JCheckBoxMenuItem("Status Bar");
    menuItemStatusbar.setMnemonic(KeyEvent.VK_S);
    menuView.add(menuItemStatusbar);
    menuItemStatusbar.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        String message;
        if (menuItemStatusbar.isSelected())
          message = "Status bar on";
        else
          message = "Status bar off";
        JOptionPane.showMessageDialog(menuItemWrap, message + "\nStatus bar not implemented yet", "Todo", 0);
      }

    });

    // help menu
    JMenu menuHelp = new JMenu("Help");
    menuHelp.setMnemonic(KeyEvent.VK_H);
    menuBar.add(menuHelp);

    // help > about
    JMenuItem menuItemAbout = new JMenuItem("About", KeyEvent.VK_A);
    menuHelp.add(menuItemAbout);
    menuItemAbout.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(menuItemAbout, "Help > About not implemented yet", "Todo", 0);
      }

    });

    return menuBar;
  }
}