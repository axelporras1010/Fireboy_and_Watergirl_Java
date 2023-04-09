
package main;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class deathPanel extends JPanel{
    ImageIcon icon;
    JLabel image;
    
    /** JPanel que permite interactuar con el juego luego de la muerte del personaje en 
     el juego */
    public deathPanel(){
        this.icon = new ImageIcon(this.getClass().getResource("images/deathImage.png"));
        this.image = new JLabel(this.icon);
        this.image.setBounds(290, 250, this.icon.getIconWidth(), this.icon.getIconHeight());
        this.add(this.image);
        
        this.setLayout(null);
        this.setPreferredSize(new Dimension(864, 672));
        this.setBackground(new Color(102, 51, 0));
    }
    
}
