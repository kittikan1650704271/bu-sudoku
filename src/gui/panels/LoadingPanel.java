/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.panels;

import static gui.SudokuGame.color1;
import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.AbstractBorder;
import javax.swing.border.LineBorder;
import gui.test.ColorFadingAnimation;

/**
 *
 * @author kaann
 */
public class LoadingPanel extends JPanel {
    private JLabel quote;
    
    
    
    public LoadingPanel(){
        this.setLayout(null);
        this.setBounds(0, 0, 1000, 550);
        this.setBackground(Color.red);
        
        //round_border
        AbstractBorder brdr = new TextBubbleBorder(Color.BLACK,3,16,0);
        
        
        // quote-Banner
        JPanel quotePanel = new JPanel();
        //banner.setLayout(new BoxLayout(banner, BoxLayout.LINE_AXIS));
        quotePanel.setPreferredSize(new Dimension(650, 300));
        quotePanel.setAlignmentX(CENTER_ALIGNMENT);
        //banner.setBounds(0, 0, 1, 1);
        quotePanel.setBackground(Color.blue);
        quotePanel.setBounds(170, 50, 650, 350);
        quotePanel.setBorder(brdr);
        
        this.add(quotePanel);
        
        //loading logo
        JPanel loadingLogo = new JPanel();
        loadingLogo.setBackground(Color.pink);
        loadingLogo.setBounds(60, 375, 84, 84);
        loadingLogo.setBorder(brdr);
        
        this.add(loadingLogo);
    }
    
    
}
