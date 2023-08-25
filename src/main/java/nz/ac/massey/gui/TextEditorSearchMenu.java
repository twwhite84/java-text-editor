package nz.ac.massey.gui;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

/**
 * Toggleable menu to search contents of editor
 */
@Getter
public class TextEditorSearchMenu extends JPanel {

    /**
     * Text field to enter string to match
     */
    @Getter
    private JTextField searchField;

    /**
     * Label showing how many matches
     */
    @Getter
    private JLabel labelMatches;

    /**
     * Buttons to navigate search
     */
    @Getter
    private JButton buttonSearchNext, buttonSearchPrev;

    public TextEditorSearchMenu() {
        init();
    }

    private void init() {
        // Set layout
        setLayout(new GridBagLayout());
        GridBagConstraints searchPanelConstraints = new GridBagConstraints();

        searchPanelConstraints.gridx = 0;
        searchPanelConstraints.gridy = 0;
        searchPanelConstraints.anchor = GridBagConstraints.WEST;
        labelMatches = new JLabel("Search Matches: 0");
        add(labelMatches, searchPanelConstraints);

        searchPanelConstraints = new GridBagConstraints();
        searchPanelConstraints.gridx = 0;
        searchPanelConstraints.gridy = 1;
        searchField = new JTextField(20);
        add(searchField, searchPanelConstraints);

        searchPanelConstraints = new GridBagConstraints();
        searchPanelConstraints.gridx = 1;
        searchPanelConstraints.gridy = 0;
        buttonSearchNext = new JButton("Next");
        add(buttonSearchNext, searchPanelConstraints);

        searchPanelConstraints = new GridBagConstraints();
        searchPanelConstraints.gridx = 1;
        searchPanelConstraints.gridy = 1;
        buttonSearchPrev = new JButton("Prev");
        add(buttonSearchPrev, searchPanelConstraints);
    }

}
