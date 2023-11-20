package gui.panels;

import gui.AppJButton;
import static gui.SudokuGame.color1;
import static gui.SudokuGame.color3;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Sign in Panel View
 *
 * @author masahiro
 * @version 1.0
 */
public class SignInPanel extends JPanel {

    // SignIn Panel Attributes
    private final JTextField nameText;
    private final JPasswordField passwordText;
    private final AppJButton signupButton;
    private final AppJButton signinButton;

    /**
     * Constructs a Sign In Panel.
     */
    public SignInPanel() {

        this.setLayout(new GridLayout(7, 0));
        this.setBackground(color1);

        // Title Label
        JLabel actionLabel = new JLabel("Sign In");
        actionLabel.setFont(new Font("Avenir", Font.PLAIN, 24));
        actionLabel.setForeground(Color.white);
        actionLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(actionLabel);

        // Name Label
        JLabel emailLabel = new JLabel("Username");
        emailLabel.setFont(new Font("Halvetica Neue", Font.PLAIN, 14));
        emailLabel.setForeground(Color.white);
        emailLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(emailLabel);

        // Name Text Field
        nameText = new JTextField();
        nameText.setBackground(color1);
        nameText.setForeground(Color.white);
        nameText.setHorizontalAlignment(JLabel.CENTER);
        nameText.setFont(new Font("Halvetica Neue", Font.PLAIN, 14));
        nameText.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, color3));
        
        this.add(nameText);

        // Password Label
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Halvetica Neue", Font.PLAIN, 14));
        passwordLabel.setForeground(Color.white);
        passwordLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(passwordLabel);

        // Password Text Field
        passwordText = new JPasswordField();
        passwordText.setBackground(color1);
        passwordText.setForeground(Color.white);
        passwordText.setHorizontalAlignment(JLabel.CENTER);
        passwordText.setFont(new Font("Halvetica Neue", Font.PLAIN, 14));
        passwordText.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, color3));
        this.add(passwordText);

        // Sign In Button
        signinButton = new AppJButton("Sign In", 14, color3, color1);
        this.add(signinButton);

        // Sign Up Button
        signupButton = new AppJButton("I am not yet registered", 10, color1, color3);
        this.add(signupButton);

    }

    /**
     * Clears text fields in this view.
     */
    public void clear() {
        nameText.setText("");
        passwordText.setText("");
    }

    /**
     * @return the signupButton
     */
    public JButton getSignupButton() {
        return signupButton;
    }

    /**
     * @return the signinButton
     */
    public JButton getSigninButton() {
        return signinButton;
    }

    /**
     * @return the emailText
     */
    public JTextField getNameText() {
        return nameText;
    }

    /**
     * @return the passwordText
     */
    public JPasswordField getPasswordText() {
        return passwordText;
    }
}
