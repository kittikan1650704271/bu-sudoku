/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.test;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;


/**
 *
 * @author kaann
 */
public class testCardlayout {
    JFrame frame = new JFrame("testCardlayout");
    
    JPanel panelCont = new JPanel();
    JPanel panelFirst = new JPanel();
    JPanel panelSecond = new JPanel();
    
    JButton buttonOne = new JButton("Switch to second panel/workspace");
    JButton buttonSecond = new JButton("Switch to first panel/workspace");
    JButton buttonThird = new JButton("Switch to thrid panel/workspace");

    CardLayout cl = new CardLayout();
    
    
    public testCardlayout(){
        JLabel label1= new JLabel();
        label1.setOpaque(true);
        label1.setBackground(Color.RED);
        label1.setBounds(50,50,200,200);
        
        JPanel panel1 = new JPanel();
        panel1.setBounds(50, 80, 200, 200);
        panel1.setBackground(Color.BLUE);
        
        JLayeredPane panelThird = new JLayeredPane();
        panelCont.setLayout(cl);
        
        panelFirst.add(buttonOne);
        panelSecond.add(buttonSecond);
        panelFirst.setBackground(Color.BLUE);
        panelSecond.setBackground(Color.GREEN);
        
        panelThird.setBackground(Color.red);
        panelThird.setBounds(0, 0, 400, 400);
        panelThird.add(label1);
        panelThird.add(panel1);
       

        panelCont.add(panelFirst, "1");
        panelCont.add(panelSecond, "2");
        panelCont.add(panelThird, "3");

        
        cl.show(panelCont, "1");
        buttonOne.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panelCont, "2");
            }
        });
        
        buttonSecond.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panelCont, "3");
            }
        });
        
        
        frame.add(panelCont);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                new testCardlayout();
                
            }
        }); 
    }
}
