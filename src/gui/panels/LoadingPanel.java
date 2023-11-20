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
import gui.test.ImagePanel;
import java.awt.GridLayout;
import javax.swing.ImageIcon;

/**
 *
 * @author kaann
 */
public class LoadingPanel extends JPanel {
    private JLabel quote;
    
    
    
    public LoadingPanel(){
        this.setLayout(null);
        this.setBounds(0, 0, 1000, 550);
        this.setBackground(color1);
           
        
        // quote-Banner
        JPanel quotePanel = new JPanel();
        quotePanel.setLayout(new GridLayout());
        quotePanel.setBackground(Color.blue);
        quotePanel.setBounds(170, 50, 650, 350);
        quotePanel.add(new QuoteImage(650, 350));
        this.add(quotePanel);
        //loading logo
        JPanel loadingLogo = new JPanel();
        loadingLogo.setBackground(color1);
        loadingLogo.setBounds(60, 375, 84, 84);
        
        
        JLabel label = new JLabel( new ImageIcon(getClass().getClassLoader().getResource("loading_resized_84.gif")));
        loadingLogo.add( label );
        this.add(loadingLogo);
    }
    
    
}
