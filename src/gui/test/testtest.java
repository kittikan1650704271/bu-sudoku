
package gui.test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;

/**
 *
 * @author masahiro
 */
public class testtest {
    private static JFrame mainFrame = new JFrame();
    private static JPanel loginPanel = new JPanel();
    private static JPanel Panel1 = new JPanel();
    private static JPanel Panel2 = new JPanel();
    
    public static void main(String[] args) {
        
        
        mainFrame.setTitle("welcome");
        mainFrame.setSize(1280,720);
        mainFrame.setLayout(new GridLayout(0,3));
        mainFrame.setMinimumSize(new Dimension(1280,720));
        mainFrame.setVisible(true);
        
        loginPanel.setBackground(Color.white);
        loginPanel.setLayout(new GridLayout(3,0));
        loginPanel.setMinimumSize(new Dimension(200,200));
//        loginPanel.setBounds(0, 0, 100, 100);

        Panel1.setBackground(Color.green);
        Panel1.setLayout(null);
        Panel1.setBounds(0, 0, 200, 200);
        
        Panel2.setBackground(Color.red);
        Panel2.setBounds(0, 0, 10, 10);

        Panel1.add(Panel2);
        
        loginPanel.add(new JPanel());
        loginPanel.add(Panel1);
        loginPanel.add(new JPanel());
        
        
        mainFrame.add(new JPanel());
        mainFrame.add(loginPanel);
        mainFrame.add(new JPanel());
        
        
    }
    
}
