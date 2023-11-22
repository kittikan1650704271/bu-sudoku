
package gui.panels;

import static gui.SudokuGame.color1;
import static gui.SudokuGame.color3;
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
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

/**
 *
 * @author kaann
 */
public class LoadingPanel extends JPanel {
    

    private final CardLayout cardLayoutManager = new CardLayout();
    private JPanel quotePanel = new JPanel();
    private List<String> quoteList = new ArrayList<>();
    private QuoteImage[] gen_qoute_pack = new QuoteImage[14];

    
    
    
    public LoadingPanel(){
        this.setLayout(null);
        this.setBounds(0, 0, 1000, 550);
        this.setBackground(color1);
           
        
        // quote-Banner
        QuoteShuffle();
        quotePanel.setBackground(color3);
        quotePanel.setBounds(170, 50, 650, 350);
        quotePanel.setBorder(BorderFactory.createLineBorder(color3, 4));
        //quotePanel.add(new QuoteImage(650, 350));
        this.add(quotePanel);
        
        //loading logo
        JPanel loadingLogo = new JPanel();
        loadingLogo.setBackground(color1);
        loadingLogo.setBounds(45, 400, 84, 84);
        
        
        JLabel label = new JLabel( new ImageIcon(getClass().getClassLoader().getResource("Loading.gif")));
        loadingLogo.add( label );
        this.add(loadingLogo);
    }
    
    public void QuoteShuffle(){
        for(int i = 0; i<14; i++){
            String strI = String.valueOf(i+1);
            gen_qoute_pack[i] = new QuoteImage(650,350,strI);
            quoteList.add(strI);
        }
        

        
        quotePanel.setLayout(cardLayoutManager);
        for(int i = 0; i<14; i++){
            String strII = String.valueOf(i+1);
            quotePanel.add(gen_qoute_pack[i]);
            cardLayoutManager.addLayoutComponent(gen_qoute_pack[i], strII);
        }

        
        randomQuote();
    }
    
    public void randomQuote(){
        Random random = new Random();
        int randomIndex = random.nextInt(quoteList.size());
        String strRandomIndex = String.valueOf(randomIndex);
        System.out.println(strRandomIndex);
        cardLayoutManager.show(quotePanel, strRandomIndex);
    }
    
    public CardLayout getCardLayoutManager(){
        return cardLayoutManager;
    }
    
    public JPanel getQuotepanel(){
        return quotePanel;
    }
}
