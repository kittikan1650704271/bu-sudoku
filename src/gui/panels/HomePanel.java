package gui.panels;

import gui.AppJButton;
import static gui.SudokuGame.color1;
import static gui.SudokuGame.color2;
import static gui.SudokuGame.color3;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.TableRowSorter;

/**
 * Home Panel View of Sudoku Game
 * 
 * @author masahiro
 * @version 1.0
 */
public class HomePanel extends JPanel {
    
    // Home Panel Attributes
    private final JButton signoutBtn;
    private final JButton newGameBtn;
    private final JButton viewRulesBtn;
//    private final SoundWavePlayer;
    private JTable highscores;
    private DefaultTableModel tableModel;
    private DefaultComboBoxModel levelSelectionModel = new DefaultComboBoxModel();
    private final JComboBox levelSelector;
    private final JComboBox musicSelector;
    private JLabel nameLabel;
    private JPanel topRight;
    
    
    
    /**
     * Constructs a Home Panel View.
     */
    public HomePanel() {
        this.setLayout(new BorderLayout());
        
        
        
        
        // Banner
        JPanel banner = new JPanel();
        banner.setLayout(new BoxLayout(banner, BoxLayout.LINE_AXIS));
        banner.setPreferredSize(new Dimension(1000, 115));
        banner.setBackground(color1);
        banner.setAlignmentX(CENTER_ALIGNMENT);
        
        
            
            // Logo
            LogoImage jP1 = new LogoImage(115, 115);
            jP1.setBackground(color1);
            jP1.setPreferredSize(new Dimension(115, 115));
            jP1.setMaximumSize(new Dimension(115, 115));
            jP1.setAlignmentY(CENTER_ALIGNMENT);
            
            // Spacing
            banner.add(Box.createRigidArea(new Dimension(5,0)));
            banner.add(jP1);
            banner.add(Box.createRigidArea(new Dimension(15,0)));

            // Dynamic Banner Content
            JPanel jP2 = new JPanel();
            jP2.setBackground(color1);
            jP2.setPreferredSize(new Dimension(200, 100));
            jP2.setLayout(new GridLayout(2,0));
                JLabel welcomeLabel = new JLabel("WELCOME");
                welcomeLabel.setFont(new Font("Avenir", Font.PLAIN, 28));
                welcomeLabel.setForeground(Color.WHITE);
                welcomeLabel.setVerticalAlignment(JLabel.BOTTOM);
                jP2.add(welcomeLabel);

                nameLabel = new JLabel("Player");
                nameLabel.setFont(new Font("Avenir", Font.PLAIN, 18));
                nameLabel.setForeground(Color.WHITE);
                nameLabel.setVerticalAlignment(JLabel.TOP);
                jP2.add(nameLabel);
            banner.add(jP2);
            
            topRight = new JPanel();
            topRight.setLayout(null);
            topRight.setBounds(0, 0, 100, 100);
            topRight.setBackground(color1);
            
            // Music Select
            musicSelector = new JComboBox();
            List<String> albumBgm = new ArrayList<>();
            for(int i = 1; i< 10; i++){
            albumBgm.add("BGM "+i);
            }
            for(String Bgm : albumBgm){
                musicSelector.addItem(Bgm);   
            }
            musicSelector.setSelectedItem(null);
            musicSelector.setBounds(100,20,150,50);
            
            topRight.add(musicSelector);
            banner.add(topRight);
            this.add(banner, BorderLayout.NORTH);

        // Main Content
        JPanel main = new JPanel();
        main.setLayout(new GridLayout(0,2));

            // Left Options
            JPanel left = new JPanel();
            left.setBackground(color1);
            left.setLayout(null);
                
                JPanel game = new JPanel();
                game.setLayout(new GridLayout(2,0));
                game.setLocation(100, 50);
                game.setSize(300, 120);
                game.setBackground(color2);
                    // New Game Button
                    newGameBtn = new AppJButton("START A NEW GAME", 20, color2, color3);
                    game.add(newGameBtn);
                    
                    // Difficulty Level
                    levelSelector = new JComboBox();
                    levelSelector.setModel(levelSelectionModel);
                    game.add(levelSelector);
                    
                left.add(game);
                
                game = new JPanel();
                game.setLayout(new GridLayout());
                game.setLocation(100, 200);
                game.setSize(300, 120);
                    // Previous Game Button
                    viewRulesBtn = new AppJButton("SHOW ME THE RULES", 20, color2, color3);
                    game.add(viewRulesBtn);
                left.add(game);
                

                JPanel actions = new JPanel();
                actions.setLayout(new GridLayout());
                actions.setLocation(0, 370);
                actions.setSize(115, 30);
                    // Sign out Button
                    signoutBtn = new AppJButton("SIGN OUT", 14, color2, color3);
                    actions.add(signoutBtn);
                left.add(actions);
            main.add(left);

            // Right Options
            JPanel right = new JPanel();
            right.setBackground(color1);
            right.setLayout(null);
                    
                // High Score Table               
                highscores = new JTable() {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                
                tableModel = (DefaultTableModel) highscores.getModel(); 
                tableModel.setColumnIdentifiers(new String[] {"Score", "Player"});
                highscores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                
                highscores.getTableHeader().setFont(new Font(this.getFont().getFontName(), Font.PLAIN, this.getFont().getSize()));
                highscores.getTableHeader().setBackground(color2);
                highscores.getTableHeader().setForeground(color3);
                highscores.getTableHeader().setReorderingAllowed(false);
                highscores.setAutoCreateRowSorter(true);
                highscores.setFont(new Font("Halvetica Neue", Font.PLAIN, 14));
                highscores.setBackground(color2);
                highscores.setForeground(color3);
                highscores.setShowVerticalLines(false);
                highscores.setShowHorizontalLines(false);
//                highscores.getRowSorter().toggleSortOrder(0);

                // Create a TableRowSorter and set the sort order to descending for the first column (column 0)
                TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
                sorter.setComparator(0, (o1, o2) -> {
                    Integer score1 = Integer.parseInt(o1.toString());
                    Integer score2 = Integer.parseInt(o2.toString());
                    return score2.compareTo(score1);
                });
                sorter.setSortKeys(List.of(new RowSorter.SortKey(0, SortOrder.DESCENDING)));
                highscores.setRowSorter(sorter);
        
                JScrollPane scores = new JScrollPane(highscores);
                scores.setLocation(100, 50);
                scores.setSize(300, 270);
                scores.setBorder(new LineBorder(Color.BLACK,0));
                scores.getViewport().setBackground(color2);
                
                right.add(scores);
                
            main.add(right);

        this.add(main);
    }

    /**
     * @return the signoutBtn
     */
    public JButton getSignoutBtn() {
        return signoutBtn;
    }

    /**
     * @return the newGameBtn
     */
    public JButton getNewGameBtn() {
        return newGameBtn;
    }

    /**
     * @return the levelSelector
     */
    public JComboBox getLevelSelector() {
        return levelSelector;
    }

    /**
     * @return the showRulesBtn
     */
    public JButton getViewRulesBtn() {
        return viewRulesBtn;
    }

    /**
     * @return the nameLabel
     */
    public JLabel getNameLabel() {
        return nameLabel;
    }

    /**
     * @return the highscores
     */
    public JTable getHighscores() {
        return highscores;
    }

    /**
     * @param highscores the highscores to set
     */
    public void setHighscores(JTable highscores) {
        this.highscores = highscores;
    }

    /**
     * @return the tableModel
     */
    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    /**
     * @param tableModel the tableModel to set
     */
    public void setTableModel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }

    /**
     * @return the levelSelectionModel
     */
    public DefaultComboBoxModel getLevelSelectionModel() {
        return levelSelectionModel;
    }
    
    public JComboBox getMusicSelector(){
        return musicSelector;
    }
    
}