package nz.ac.massey;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class GUIMenuBar {
  public JMenuBar createMenuBar() {
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

    JMenuItem menuItemSearch = new JMenuItem("Search", KeyEvent.VK_S);
    menuSearch.add(menuItemSearch);
    menuItemSearch.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(menuItemSearch, "Search not implemented yet", "Todo", 0);
      }

    });

    return menuBar;
  }
}