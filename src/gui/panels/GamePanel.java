package gui.panels;

import gui.AppJButton;
import gui.SudokuGame;
import static gui.SudokuGame.color1;
import static gui.SudokuGame.color2;
import static gui.SudokuGame.color3;
import static java.awt.Component.CENTER_ALIGNMENT;
import gui.model.Cell;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

/**
 * View for a Sudoku Game Panel
 * 
 * @author masahiro
 * @version 1.0
 */
public class GamePanel extends JPanel  {
    
    // Game Panel Attributes
    private List<Cell> viewCellList;
    private final JButton endGameBtn;
    private final JButton viewRulesBtn;
    private final JButton hintBtn;
    private final JPanel grid;
    private Boolean resetHeart = false;
    private int failed_count = 0;
    public final JPanel[] heart = new JPanel[3];
    private final HeartImage[] jP3 = new HeartImage[3];
    public final JPanel[] Empty_heart = new JPanel[3];
    private final EmptyHeartImage[] jP4 = new EmptyHeartImage[3];
    private final JPanel jP5 = new JPanel();
    private JLabel levelTitle;
    private JLabel timeLabel;
    private JLabel Heart;
    private JLabel[] numbers = new JLabel[9];
    
    
    
    /**
     * Constructs a Game Panel.
     */
    public GamePanel() {
        this.setLayout(new BorderLayout());
        // Banner
        JPanel banner = new JPanel();
        banner.setLayout(new BoxLayout(banner, BoxLayout.LINE_AXIS));
        banner.setBounds(0,0, 115, 115);
        banner.setPreferredSize(new Dimension(115, 115));
        banner.setBackground(color1);
        banner.setAlignmentX(CENTER_ALIGNMENT);
        
        
            // Sudoku Logo
            LogoImage jP1 = new LogoImage(115, 115);
            jP1.setBackground(color1);
            jP1.setPreferredSize(new Dimension(115, 115));
            jP1.setMaximumSize(new Dimension(115, 115));
            jP1.setAlignmentY(CENTER_ALIGNMENT);
            
            // Spacing
            banner.add(Box.createRigidArea(new Dimension(5,0)));
            banner.add(jP1, BorderLayout.NORTH);
            
            // Dynamic Banner Content
            JPanel jP2 = new JPanel();
            jP2.setBackground(color1);
            jP2.setPreferredSize(new Dimension(200, 100));
            jP2.setBounds(800,25,200,115);
            jP2.setLayout(new GridLayout(2,0));
            //jP2.setLocation(800, 50);
            
                timeLabel = new JLabel();
                timeLabel.setFont(new Font("Avenir", Font.PLAIN, 36));
                timeLabel.setForeground(Color.WHITE);
                timeLabel.setVerticalAlignment(JLabel.BOTTOM);
                timeLabel.setHorizontalAlignment(JLabel.CENTER);
                jP2.add(timeLabel);

                levelTitle = new JLabel();
                levelTitle.setFont(new Font("Avenir", Font.PLAIN, 24));
                levelTitle.setForeground(Color.WHITE);
                levelTitle.setVerticalAlignment(JLabel.TOP);
                levelTitle.setHorizontalAlignment(JLabel.CENTER);
                jP2.add(levelTitle);
                
        // Main Content
        JPanel main = new JPanel();
        main.setLayout(null);
        main.setBackground(color1);           
                       
            JPanel actions = new JPanel();
            actions.setLayout(new GridLayout(3,1));
            actions.setSize(135, 90);
            actions.setLocation(0, 400 - actions.getHeight());

                // Get Hint Button
                hintBtn = new AppJButton("HINT", 14, color2, color3);
                actions.add(hintBtn);
            
                // View Rules Button
                viewRulesBtn = new AppJButton("VIEW RULES", 14, color2, color3);
                actions.add(viewRulesBtn);
            
                // Sign Out Button
                endGameBtn = new AppJButton("END GAME", 14, color2, color3);
                actions.add(endGameBtn);
            main.add(actions, BorderLayout.WEST);
            
            // Game Grid Panel
            grid = new JPanel();
            grid.setLayout(new GridLayout(9, 9));
            grid.setPreferredSize(new Dimension(120, 120));
            grid.setMaximumSize(new Dimension(433, 433));
            grid.setBorder(new LineBorder(color3, 2));
            grid.setBackground(color1.darker());
            grid.setForeground(Color.white);
            grid.setBounds(285,0,400,400);
            grid.setLocation(260, 50);
            
        showHeart();
        showSideNumbers();
        
        this.add(jP2);
        
        this.add(banner);
        this.add(grid);
        this.add(main);
    }
    
    public void showSideNumbers() {
        jP5.setBackground(color1);
        jP5.setPreferredSize(new Dimension(200, 100));
        jP5.setBounds(215,50,40,315);
        jP5.setLayout(new GridLayout(9,0));
        
        for(int i = 0; i < 9; i++){
            numbers[i] = new JLabel(""+(i+1));
            numbers[i].setFont(new Font("Avenir", Font.PLAIN, 20));
            numbers[i].setForeground(Color.WHITE);
            numbers[i].setVerticalAlignment(JLabel.BOTTOM);
            numbers[i].setHorizontalAlignment(JLabel.CENTER);
            jP5.add(numbers[i]);
            this.add(jP5);
        }
    }
    
    public void checkDoneNumber(int i){
            getNumbers()[i].setVisible(false);
        
    }
    
    public void remmoveSideNumbers() {
        
    }
    
    
    
    /**
     * Add heart to Game Panel.
     */
    public void showHeart() {
        for(int i = 0 ; i < 3; i++){
            // Heart Logo
            jP3[i] = new HeartImage(32, 32);
            jP3[i].setPreferredSize(new Dimension(32, 96));
            jP3[i].setMaximumSize(new Dimension(32, 96));
            // Empty_Heart Logo
            jP4[i] = new EmptyHeartImage(32, 32);
            jP4[i].setPreferredSize(new Dimension(32, 96));
            jP4[i].setMaximumSize(new Dimension(32, 96));
            //Heart Panel
                heart[i] = new JPanel();
                heart[i].setLayout(new BoxLayout(heart[i], BoxLayout.LINE_AXIS));
                heart[i].setBounds(675,50+(i * 35), 32, 32);
                heart[i].setBackground(color1);
                heart[i].add(jP3[i]);
                this.add(heart[i]);
            //Empty_Heart Panel
                Empty_heart[i] = new JPanel();
                Empty_heart[i].setLayout(new BoxLayout(Empty_heart[i], BoxLayout.LINE_AXIS));
                Empty_heart[i].setBounds(675,50+(i * 35), 32, 32);
                Empty_heart[i].setBackground(color1);
                Empty_heart[i].add(jP4[i]);
                this.add(Empty_heart[i]);
            }
    }
    /**
     * Change heart to empty heart.
     */
    
    public void changeHeart() {
            //Set heart visible false heart
            if(failed_count > 0 && failed_count < 4){
                heart[failed_count-1].setVisible(false);
                Timer timer = new Timer(65, new ActionListener() {
                    private int count = 1;

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (count >= 0) {
                            grid.setBorder(new LineBorder(Color.red, 2));
                            count--;
                        } 
                        else {
                            grid.setBorder(new LineBorder(color3, 2));
                            ((Timer) e.getSource()).stop(); // Stop the timer after 5 iterations
                        }
                    }
                });
                timer.start();
                
                
            }
            if(resetHeart){
                    Timer timer = new Timer(300, new ActionListener() {
                    private int count = 2;

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (count >= 0) {
                            heart[count].setVisible(true);
                            count--;
                        } else {
                            ((Timer) e.getSource()).stop(); // Stop the timer after 5 iterations
                        }
                    }
                });
                timer.start();
            }         
            // Trigger a repaint to update the panel
            repaint();
    }
    
    /**
     * @return the endGameBtn
     */
    public JButton getEndGameBtn() {
        return endGameBtn;
    }

    /**
     * @return the viewRulesBtn
     */
    public JButton getViewRulesBtn() {
        return viewRulesBtn;
    }

    /**
     * @return the hintBtn
     */
    public JButton getHintBtn() {
        return hintBtn;
    }

    /**
     * @return the levelTitle
     */
    public JLabel getLevelTitle() {
        return levelTitle;
    }

    /**
     * @param levelTitle the levelTitle to set
     */
    public void setLevelTitle(JLabel levelTitle) {
        this.levelTitle = levelTitle;
    }

    /**
     * @return the timeLabel
     */
    public JLabel getTimeLabel() {
        return timeLabel;
    }

    /**
     * @param timeLabel the timeLabel to set
     */
    public void setTimeLabel(JLabel timeLabel) {
        this.timeLabel = timeLabel;
    }

    /**
     * @return the grid
     */
    public JPanel getGrid() {
        return grid;
    }

    /**
     * @return the viewCellList
     */
    public List<Cell> getViewCellList() {
        return viewCellList;
    }

    /**
     * @param viewCellList the viewCellList to set
     */
    public void setViewCellList(List<Cell> viewCellList) {
        this.viewCellList = viewCellList;
    }
    
    public int getFailed_count() {
        return failed_count;
    }

    public void setFailed_count(int failed_count) {
        this.failed_count = failed_count;
    }

    public JLabel getHeart() {
        return Heart;
    }

    public void setHeart(JLabel Heart) {
        this.Heart = Heart;
    }

    public Boolean getResetHeart() {
        return resetHeart;
    }

    public void setResetHeart(Boolean resetHeart) {
        this.resetHeart = resetHeart;
    }

    public JLabel[] getNumbers(){
        return numbers;
    }
    
    
    
}
