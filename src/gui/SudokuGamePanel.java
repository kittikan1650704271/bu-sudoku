package gui;

import gui.panels.RulesPanel;
import gui.panels.GamePanel;
import gui.panels.HomePanel;
import gui.panels.LoadingPanel;
//import gui.panels.WelcomePanel;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;

import gui.panels.PanelCover;
import gui.panels.PanelLoginAndRegister;

/**
 * This is the Sudoku Game Panel (VIEW) Manager for all panels.
 *
 * @author masahiro
 */
public class SudokuGamePanel extends JPanel {

    // VIEW Manager Attributes
//    private final WelcomePanel welcomePanel;
    private final CardLayout cardLayoutManager = new CardLayout();
    private final JPanel content = new JPanel();
    private final HomePanel homePanel;
    private final GamePanel gamePanel;
    private final RulesPanel rulesPanel;
    private final LoadingPanel loadingPanel;
    private PanelLoginAndRegister loginAndRegister;
    private PanelCover cover;
    

    /**
     * View Manager Constructor.
     */
    public SudokuGamePanel() {

        // Grid Layout
        this.setLayout(new GridLayout());

        // Panel Setup
//        this.welcomePanel = new WelcomePanel();
        this.homePanel = new HomePanel();
        this.gamePanel = new GamePanel();
        this.loadingPanel = new LoadingPanel();
        this.rulesPanel = new RulesPanel();
        this.loginAndRegister = new PanelLoginAndRegister();
        this.cover = new PanelCover();

        // Content View Panels
        content.setLayout(cardLayoutManager);
//        content.add(this.welcomePanel);
        content.add(this.homePanel);
        content.add(this.loadingPanel);
        content.add(this.gamePanel);
        content.add(this.rulesPanel);
        content.add(this.loginAndRegister);
        content.add(this.cover);

        // Set Panel Identifiers
//        cardLayoutManager.addLayoutComponent(this.welcomePanel, "welcome");
        cardLayoutManager.addLayoutComponent(this.homePanel, "home");
        cardLayoutManager.addLayoutComponent(this.gamePanel, "game");
        cardLayoutManager.addLayoutComponent(this.loadingPanel, "loading");
        cardLayoutManager.addLayoutComponent(this.rulesPanel, "rules");
        cardLayoutManager.addLayoutComponent(this.loginAndRegister, "welcome");
        cardLayoutManager.addLayoutComponent(this.cover, "cover");

        // Set Start Up Panel
           cardLayoutManager.show(content, "welcome");
        
        

        // Add Content
        this.add(content);
    }
    

    /**
     * @return the welcomePanel
     */
//    public WelcomePanel getWelcomePanel() {
//        return welcomePanel;
//    }

    /**
     * @return the homePanel
     */
    public HomePanel getHomePanel() {
        return homePanel;
    }

    /**
     * @return the gamePanel
     */
    public GamePanel getGamePanel() {
        return gamePanel;
    }

    /**
     * @return the cardLayoutManager
     */
    public CardLayout getCardLayoutManager() {
        return cardLayoutManager;
    }

    /**
     * @return the content
     */
    public JPanel getContent() {
        return content;
    }

    /**
     * @return the rulesPanel
     */
    public RulesPanel getRulesPanel() {
        return rulesPanel;
    }

    public LoadingPanel getLoadingPanel(){
        return loadingPanel;
    }
    
    public PanelLoginAndRegister getLoginAndRegisterPanel(){
        return loginAndRegister;
    }
    
    public PanelCover getCoverPanel(){
        return cover;
    }
    
}
