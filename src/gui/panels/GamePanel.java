package gui.panels;

import gui.AppJButton;
import static gui.SudokuGame.APP_PINK;
import static gui.SudokuGame.BKGD_DARK_GRAY;
import static gui.SudokuGame.BKGD_LIGHT_GRAY;
import gui.SudokuGameApp;
import static java.awt.Component.CENTER_ALIGNMENT;
import gui.model.Cell;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
    private int failed_count = 0;
    private JPanel banner;
    private LogoImage jP1;
    private JPanel jP2;
    private JPanel main;

    public JPanel getBanner() {
        return banner;
    }

    public void setBanner(JPanel banner) {
        this.banner = banner;
    }

    public LogoImage getjP1() {
        return jP1;
    }

    public void setjP1(LogoImage jP1) {
        this.jP1 = jP1;
    }

    public JPanel getMain() {
        return main;
    }

    public void setMain(JPanel main) {
        this.main = main;
    }

    public JPanel getjP2() {
        return jP2;
    }

    public void setjP2(JPanel jP2) {
        this.jP2 = jP2;
    }
    private final JPanel actions;

    public int getFailed_count() {
        return failed_count;
    }

    public void setFailed_count(int failed_count) {
        this.failed_count = failed_count;
    }
    public final JPanel[] heart = new JPanel[3];
    private final HeartImage[] jP3 = new HeartImage[3];
    public final JPanel[] Empty_heart = new JPanel[3];
    private final EmptyHeartImage[] jP4 = new EmptyHeartImage[3];
    
    private JLabel levelTitle;
    private JLabel timeLabel;
    
    
    /**
     * Constructs a Game Panel.
     */
    public GamePanel() {
        
        this.setLayout(new BorderLayout());
        // Banner
        this.banner = new JPanel();
        this.banner.setLayout(new BoxLayout(this.banner, BoxLayout.LINE_AXIS));
        this.banner.setBounds(0,0, 115, 115);
        this.banner.setPreferredSize(new Dimension(115, 115));
        this.banner.setBackground(BKGD_DARK_GRAY);
        this.banner.setAlignmentX(CENTER_ALIGNMENT);
        
        
            // Sudoku Logo
            this.jP1 = new LogoImage(115, 115);
            this.jP1.setBackground(BKGD_DARK_GRAY);
            this.jP1.setPreferredSize(new Dimension(115, 115));
            this.jP1.setMaximumSize(new Dimension(115, 115));
            this.jP1.setAlignmentY(CENTER_ALIGNMENT);
            
            // Spacing
            this.banner.add(Box.createRigidArea(new Dimension(5,0)));
            this.banner.add(jP1, BorderLayout.NORTH);
            
            // Dynamic Banner Content
            this.jP2 = new JPanel();
            this.jP2.setBackground(BKGD_DARK_GRAY);
            this.jP2.setPreferredSize(new Dimension(200, 100));
            this.jP2.setBounds(800,25,200,115);
            this.jP2.setLayout(new GridLayout(2,0));
            //jP2.setLocation(800, 50);
            
                this.timeLabel = new JLabel();
                this.timeLabel.setFont(new Font("Avenir", Font.PLAIN, 36));
                this.timeLabel.setForeground(Color.WHITE);
                this.timeLabel.setVerticalAlignment(JLabel.BOTTOM);
                this.timeLabel.setHorizontalAlignment(JLabel.CENTER);
                this.jP2.add(this.timeLabel);

                this.levelTitle = new JLabel();
                this.levelTitle.setFont(new Font("Avenir", Font.PLAIN, 24));
                this.levelTitle.setForeground(Color.WHITE);
                this.levelTitle.setVerticalAlignment(JLabel.TOP);
                this.levelTitle.setHorizontalAlignment(JLabel.CENTER);
                this.jP2.add(this.levelTitle);    

        // Main Content
        this.main = new JPanel();
        this.main.setLayout(null);
        this.main.setBackground(BKGD_DARK_GRAY);           
                       
            this.actions = new JPanel();
            this.actions.setLayout(new GridLayout(3,1));
            this.actions.setSize(135, 90);
            this.actions.setLocation(0, 400 - this.actions.getHeight());

                // Get Hint Button
                this.hintBtn = new AppJButton("HINT", 14, BKGD_LIGHT_GRAY, APP_PINK);
                this.actions.add(this.hintBtn);
            
                // View Rules Button
                this.viewRulesBtn = new AppJButton("VIEW RULES", 14, BKGD_LIGHT_GRAY, APP_PINK);
                this.actions.add(this.viewRulesBtn);
            
                // Sign Out Button
                this.endGameBtn = new AppJButton("END GAME", 14, BKGD_LIGHT_GRAY, APP_PINK);
                this.actions.add(this.endGameBtn);
            this.main.add(this.actions, BorderLayout.WEST);
            
            // Game Grid Panel
            this.grid = new JPanel();
            this.grid.setLayout(new GridLayout(9, 9));
            this.grid.setPreferredSize(new Dimension(120, 120));
            this.grid.setMaximumSize(new Dimension(433, 433));
            this.grid.setBorder(new LineBorder(APP_PINK, 2));
            this.grid.setBackground(BKGD_DARK_GRAY.darker());
            this.grid.setForeground(Color.white);
            this.grid.setBounds(285,0,400,400);
            this.grid.setLocation(260, 50);

//        showHeart();
//        //removeHeart();
//
//        this.add(jP2);
//        this.add(banner);
//        this.add(grid);
//        this.add(main);
        
    }
    public void createGamePanel(){
        showHeart();
        //removeHeart();

        this.add(jP2);
        this.add(banner);
        this.add(grid);
        this.add(main);
    }

    public void showHeart() {
        System.out.print(this);
        for(int i = 0 ; i < 3; i++){
            // Heart Logo
            this.jP3[i] = new HeartImage(32, 32);
            this.jP3[i].setPreferredSize(new Dimension(32, 96));
            this.jP3[i].setMaximumSize(new Dimension(32, 96));
            // Empty_Heart Logo
            this.jP4[i] = new EmptyHeartImage(32, 32);
            this.jP4[i].setPreferredSize(new Dimension(32, 96));
            this.jP4[i].setMaximumSize(new Dimension(32, 96));
            //Heart Panel
                this.heart[i] = new JPanel();
                this.heart[i].setLayout(new BoxLayout(heart[i], BoxLayout.LINE_AXIS));
                this.heart[i].setBounds(675,50+(i * 35), 32, 32);
                this.heart[i].setBackground(BKGD_DARK_GRAY);
                this.heart[i].add(jP3[i]);
                this.add(heart[i]);
            //Empty_Heart Panel
                this.Empty_heart[i] = new JPanel();
                this.Empty_heart[i].setLayout(new BoxLayout(Empty_heart[i], BoxLayout.LINE_AXIS));
                this.Empty_heart[i].setBounds(675,50+(i * 35), 32, 32);
                this.Empty_heart[i].setBackground(BKGD_DARK_GRAY);
                this.Empty_heart[i].add(jP4[i]);
                this.add(Empty_heart[i]);
            }
    }
    
    public void removeHeart() {
           if(failed_count > 0 && failed_count < 4){
                    heart[failed_count-1].setVisible(false);
                }
           }
    public void fillHeart(){
        for(int i = 0; i < 3; i++){
                   heart[i].setVisible(true);
               }
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
}
