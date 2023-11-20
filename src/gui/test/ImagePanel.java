/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.test;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
 
public class ImagePanel extends JPanel {
 
  Image image;
 
  public ImagePanel(int width, int height) {
    image = Toolkit.getDefaultToolkit().createImage("C:\\Users\\kaann\\OneDrive - bu.ac.th\\Desktop\\Work\\CS318\\Project\\images\\ZKZg.gif");
    this.setPreferredSize(new Dimension(width, height));
  }
 
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (image != null) {
      g.drawImage(image, 0, 0, this);
    }
  }
}