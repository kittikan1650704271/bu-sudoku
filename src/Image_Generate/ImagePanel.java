package Image_Generate;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel {
    private BufferedImage currentImage;

    public ImagePanel(BufferedImage initialImage) {
        this.currentImage = initialImage;
    }

    public void setImage(BufferedImage newImage) {
        this.currentImage = newImage;
        repaint(); // Trigger a repaint to update the panel
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (currentImage != null) {
            g.drawImage(currentImage, 0, 0, this);
        }
    }
}