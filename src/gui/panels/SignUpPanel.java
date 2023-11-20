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
 * Sign Up Panel View
 *
 * @author masahiro
 */
public class SignUpPanel extends JPanel {

    // SignUp Panel Attributes
    private final JTextField fullnameText;
    private final JTextField emailText;
    private final JPasswordField passwordText;
    private final AppJButton signupButton;
    private final AppJButton signinButton;

    /**
     * Constructs a Sign Up Panel
     */
    public SignUpPanel() {

        this.setLayout(new GridLayout(9, 0));
        this.setBackground(color1);

        // Title Label
        JLabel actionLabel = new JLabel("Sign Up");
        actionLabel.setFont(new Font("Avenir", Font.PLAIN, 24));
        actionLabel.setForeground(Color.white);
        actionLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(actionLabel);

        // Full Name Label
        JLabel fullnameLabel = new JLabel("Username");
        fullnameLabel.setFont(new Font("Halvetica Neue", Font.PLAIN, 14));
        fullnameLabel.setForeground(Color.white);
        fullnameLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(fullnameLabel);

        // Full Name Text Field
        fullnameText = new JTextField();
        fullnameText.setBackground(color1);
        fullnameText.setForeground(Color.white);
        fullnameText.setHorizontalAlignment(JLabel.CENTER);
        fullnameText.setFont(new Font("Halvetica Neue", Font.PLAIN, 14));
        fullnameText.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, color3));
        this.add(fullnameText);

        // Email Label
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Halvetica Neue", Font.PLAIN, 14));
        emailLabel.setForeground(Color.white);
        emailLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(emailLabel);

        // Email Text Field
        emailText = new JTextField();
        emailText.setBackground(color1);
        emailText.setForeground(Color.white);
        emailText.setHorizontalAlignment(JLabel.CENTER);
        emailText.setFont(new Font("Halvetica Neue", Font.PLAIN, 14));
        emailText.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, color3));
        this.add(emailText);

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

        // Sign Up Button
        signupButton = new AppJButton("Sign Up", 14, color3, color1);
        this.add(signupButton);

        // Sign In Button
        signinButton = new AppJButton("I am already registered", 10, color1, color3);
        this.add(signinButton);

    }

    /**
     * Clears text fields in this view.
     */
    public void clear() {
        fullnameText.setText("");
        emailText.setText("");
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
     * @return the fullnameText
     */
    public JTextField getFullnameText() {
        return fullnameText;
    }

    /**
     * @return the emailText
     */
    public JTextField getEmailText() {
        return emailText;
    }

    /**
     * @return the passwordText
     */
    public JPasswordField getPasswordText() {
        return passwordText;
    }
}
