package gui.panels;

import static gui.SudokuGame.color1;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Logo of Sudoku Game Application
 *
 * @author masahiro
 * @version 1.0
 */
public class LoadingImage extends JPanel {

    // Logo Attributes
    private BufferedImage logo;

    /**
     * Constructs a Logo with the preferred dimensions
     *
     * @param width the width of the logo
     * @param height the hight of the logo
     */
    public LoadingImage(int width, int height) {
        try {
            this.logo = ImageIO.read(getClass().getResource("/1490.gif"));
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
