/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author kaann
 */
public class testGridLayout {
    
    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLayout(new GridBagLayout());
        
        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.red);
        panel1.setSize(new Dimension(250,250));
//        JPanel panel2 = new JPanel();
//        panel2.setBackground(Color.blue);
//        JPanel panel3 = new JPanel();
//        panel3.setBackground(Color.green);
        
//        frame.add(new JButton("1"));
//        frame.add(new JButton("2"));
//        frame.add(new JButton("3"));
        
        frame.add(panel1);
//        frame.add(panel2);
//        frame.add(panel3);
        
        frame.setVisible(true);
    }
    
}
