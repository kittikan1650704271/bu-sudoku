package gui.panels;

import static gui.SudokuGame.color1;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Welcome Image of Sudoku Game Application
 *
 * @author masahiro
 * @version 1.0
 */
public class WelcomeImage extends JPanel {

    // Welcome Image Attributes
    private BufferedImage background;

    /**
     * Constructs a Welcome Image with the preferred dimensions
     *
     * @param width the width of the image
     * @param height the hight of the image
     */
    public WelcomeImage(int width, int height) {
        try {
            this.background = ImageIO.read(getClass().getResource("/LoginBG.png"));
            setPreferredSize(new Dimension(width, width));
        } catch (Exception ex) {
            System.err.println("Error Welcome Image: " + ex);
            setBackground(color1);
        }
    }

    /**
     * Paints the image to the container
     *
     * @param g the graphics space
     */
    @Override
    protected void paintComponent(Graphics g) {
        if (this.background != null) {
            g.drawImage(this.background, 0, 0, this);
        } else {
            super.paintComponent(g);
        }
    }
}
