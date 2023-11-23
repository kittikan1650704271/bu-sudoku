package gui.panels;

import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 * Welcome Panel View
 *
 * @author masahiro
 * @version 1.0
 */
public class WelcomePanel extends JPanel {

    // Welcome Panel Attributes
    private final CardLayout cardLayoutManager = new CardLayout();
    private final SignUpPanel signUpPanel = new SignUpPanel();
    private final SignInPanel signInPanel = new SignInPanel();
    private final JPanel slider = new JPanel();

    /**
     * Constructs a Welcome Panel
     */
    public WelcomePanel() {

        this.setLayout(new GridLayout(0, 2));

        slider.setLayout(this.cardLayoutManager);
        slider.add(this.signInPanel);
        slider.add(this.signUpPanel);

        // Adding to Panel
        this.add(new WelcomeImage(500, 550));
        this.add(slider);
    }

    /**
     * @return the cardLayoutManager
     */
    public CardLayout getCardLayoutManager() {
        return cardLayoutManager;
    }

    /**
     * @return the slider
     */
    public JPanel getSlider() {
        return slider;
    }

    /**
     * @return the signUpPanel
     */
    public SignUpPanel getSignUpPanel1() {
        return signUpPanel;
    }

    /**
     * @return the signInPanel
     */
    public SignInPanel getSignInPanel1() {
        return signInPanel;
    }
}
