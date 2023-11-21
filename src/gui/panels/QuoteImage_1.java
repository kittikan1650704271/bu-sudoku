/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.panels;
import static gui.SudokuGame.color1;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author kaann
 */
public class QuoteImage_1 extends JPanel {

    // Logo Attributes
    private BufferedImage logo;

    /**
     * Constructs a Logo with the preferred dimensions
     *
     * @param width the width of the logo
     * @param height the hight of the logo
     */
    public QuoteImage_1(int width, int height) {

        try {
            this.logo = ImageIO.read(getClass().getResource("/Quote/Quotes2.png"));
            setPreferredSize(new Dimension(width, height));
        } catch (Exception ex) {
            System.err.println("Error Logo Image: " + ex);
            setBackground(color1);
        }
    }

    /**
     * Paints the logo to the container
     *
     * @param g the graphics space
     */
    @Override
    protected void paintComponent(Graphics g) {
        if (this.logo != null) {
            g.drawImage(this.logo, 0, 0, this);
        } else {
            super.paintComponent(g);
        }
    }
}