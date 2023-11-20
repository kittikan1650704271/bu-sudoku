/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.test;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author kaann
 */
public class ImagesIcon {
    
    public Image getImage(String filename) {
    try {
        return ImageIO.read(getClass().getResourceAsStream(
                "/" + filename));
    } catch (IOException e) {
        e.printStackTrace();
        return null;
    }
}
}
