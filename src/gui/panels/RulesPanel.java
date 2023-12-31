package gui.panels;

import gui.AppJButton;
import static gui.SudokuGame.color1;
import static gui.SudokuGame.color3;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Stores the VIEW for a Rules Panel
 *
 * @author masahiro
 */
public class RulesPanel extends JPanel {

    // Rules Panel Attributes
    private final String rules;
    private final AppJButton backBtn;

    /**
     * Constructs a Rules Panel.
     */
    public RulesPanel() {

        this.rules = "<html>The rules are simple, there are 81 squares on a grid.<br>"
                + "A grid is divided into 9 subgrids (blocks), which each contain 9 cells.<br><br>"
                + "In the begining, a cell could contain either a digit or be empty.<br>"
                + "Using these \"hints\", it is up to you to correctly fill every cell "
                + "on the grid with its own digit (1-9).<br><br>"
                + "What's the catch?<br>"
                + " > Each digit must only appear once in each row.<br>"
                + " > Each digit must only appear once in each column.<br>"
                + " > Each digit must only appear once in each subgrid.<br><br>"
                + "Sounds easy, right?<br>"
                + "How about changing the difficulty level of the game? "
                + "There are actually over 10 billion unique grids.<br><br>"
                + "Happy Solving!!</html>";

        this.setLayout(new GridLayout(1, 0));
        this.setBackground(color1);

        // Main Content of Panel
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        content.setBackground(color1);

            JLabel titleLabel = new JLabel("Game Rules");
            titleLabel.setFont(new Font("Avenir", Font.PLAIN, 24));
            titleLabel.setForeground(Color.white);
            titleLabel.setHorizontalAlignment(JLabel.CENTER);
            content.add(titleLabel, BorderLayout.NORTH);

            JLabel text = new JLabel(this.rules);
            text.setFont(new Font("Avenir", Font.PLAIN, 14));
            text.setForeground(Color.white);
            text.setBackground(color1);
            text.setBorder(new EmptyBorder(10, 10, 10, 10));
            content.add(text, BorderLayout.CENTER);

            backBtn = new AppJButton("Take me Back", 24, color3, color1);
            content.add(backBtn, BorderLayout.SOUTH);

        this.add(new ManualImage(500, 550));
        this.add(content);

    }

    /**
     * @return the Back button
     */
    public AppJButton getBackBtn() {
        return backBtn;
    }
}
