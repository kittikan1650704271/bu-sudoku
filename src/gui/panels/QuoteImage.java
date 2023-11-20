/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.panels;
import static gui.SudokuGame.color1;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
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
public class QuoteImage extends JPanel {

    // Logo Attributes
    private List<String> quoteList = new ArrayList<>();
    private BufferedImage logo;
    private CardLayout cl = new CardLayout();
    private JPanel Quote = new JPanel();

    /**
     * Constructs a Logo with the preferred dimensions
     *
     * @param width the width of the logo
     * @param height the hight of the logo
     */
    public QuoteImage(int width, int height) {
        
        
        this.setLayout(new GridLayout());
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        
        Quote.setLayout(cl);
        
        Quote.add(panel1, "1");
        Quote.add(panel2, "2");
        
        cl.addLayoutComponent(panel1, "1");
        cl.addLayoutComponent(panel2, "2");
        
        cl.show(Quote, "1");
        
        quoteList.add("Einstein_tell");
        quoteList.add("Elon_tell");
        quoteList.add("newton_tell");
        
        Random random = new Random();
        int randomIndex = random.nextInt(quoteList.size());
        String randomQuote = quoteList.get(randomIndex);
        
        try {
            this.logo = ImageIO.read(getClass().getResource("/"+randomQuote+".png"));
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