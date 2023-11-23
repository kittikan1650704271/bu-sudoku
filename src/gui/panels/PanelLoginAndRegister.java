package gui.panels;

import gui.swing.Button;
import gui.swing.MyPasswordField;
import gui.swing.MyTextField;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

public class PanelLoginAndRegister extends javax.swing.JLayeredPane {
    
    private MyTextField txtUsernameSignin = new MyTextField();
    private MyPasswordField txtPassSignin = new MyPasswordField();
    private Button btnSignin = new Button();
    
    private MyTextField txtUsernameSignup = new MyTextField();
    private MyTextField txtEmailSignup = new MyTextField();
    private MyPasswordField txtPassSignup = new MyPasswordField();
    private Button btnSignup = new Button();
    
    public PanelLoginAndRegister() {
        initComponents();
        initRegister();
        initLogin();
        login.setVisible(false);
        register.setVisible(true);
    }

    private void initRegister() {
        register.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Create Account");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(255, 153, 255));
        register.add(label);
//        MyTextField txtUser = new MyTextField();
        txtUsernameSignup.setPrefixIcon(new ImageIcon(getClass().getResource("/user.png")));
        txtUsernameSignup.setHint("Name");
        register.add(txtUsernameSignup, "w 60%");
//        MyTextField txtEmail = new MyTextField();
        txtEmailSignup.setPrefixIcon(new ImageIcon(getClass().getResource("/mail.png")));
        txtEmailSignup.setHint("Email");
        register.add(txtEmailSignup, "w 60%");
//        MyPasswordField txtPass = new MyPasswordField();
        txtPassSignup.setPrefixIcon(new ImageIcon(getClass().getResource("/pass.png")));
        txtPassSignup.setHint("Password");
        register.add(txtPassSignup, "w 60%");
        Button cmd = new Button();
        cmd.setBackground(new Color(255, 153, 255));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("SIGN UP");
        register.add(cmd, "w 40%, h 40");
    }

    private void initLogin() {
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]52[]10[]10[]25[]push"));
        JLabel label = new JLabel("Sign In");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(255, 153, 255));
        login.add(label);
//        MyTextField txtUsername = new MyTextField();
        txtUsernameSignin.setPrefixIcon(new ImageIcon(getClass().getResource("/user.png")));
        txtUsernameSignin.setHint("Username");
        login.add(txtUsernameSignin, "w 60%");
//        MyPasswordField txtPassSignIn = new MyPasswordField();
        txtPassSignin.setPrefixIcon(new ImageIcon(getClass().getResource("/pass.png")));
        txtPassSignin.setHint("Password");
        login.add(txtPassSignin, "w 60%");
        JButton cmdForget = new JButton("Forgot your password ?");
        cmdForget.setForeground(new Color(100, 100, 100));
        cmdForget.setFont(new Font("sansserif", 1, 12));
        cmdForget.setContentAreaFilled(false);
        cmdForget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.add(cmdForget);
//        Button signInBtn = new Button();
        btnSignin.setBackground(new Color(255, 153, 255));
        btnSignin.setForeground(new Color(62, 74, 83));
        btnSignin.setText("SIGN IN");
//        cmd.setFont(new Font("sansserif", 1, 14));
        login.add(btnSignin, "w 40%, h 40");
    }

    public void showRegister(boolean show) {
        if (show) {
            register.setVisible(true);
            login.setVisible(false);
        } else {
            register.setVisible(false);
            login.setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JPanel();
        register = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(42, 54, 63));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(login, "card3");

        register.setBackground(new java.awt.Color(42, 54, 63));

        javax.swing.GroupLayout registerLayout = new javax.swing.GroupLayout(register);
        register.setLayout(registerLayout);
        registerLayout.setHorizontalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        registerLayout.setVerticalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(register, "card2");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel login;
    private javax.swing.JPanel register;
    // End of variables declaration//GEN-END:variables

    public void clearSignin() {
        txtUsernameSignin.setText("");
        txtPassSignin.setText("");
    }
    
    public JTextField getUsernameinText() {
        return txtUsernameSignin;
    }
    
    public JPasswordField getPasswordinText() {
        return txtPassSignin;
    }
    
    public JButton getBtnSignin() {
        return btnSignin;
    }
    
    public void clearSignup() {
        txtUsernameSignup.setText("");
        txtPassSignup.setText("");
    }
    
    public JTextField getUsernameupText() {
        return txtUsernameSignup;
    }
    
    public JTextField getEmailupText() {
        return txtEmailSignup;
    }
    
    public JPasswordField getPasswordupText() {
        return txtPassSignup;
    }
    
    public JButton getBtnSignup() {
        return btnSignup;
    }
    
}
