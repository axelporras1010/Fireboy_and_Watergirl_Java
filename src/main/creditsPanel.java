
package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import soundManager.soundManager;


public class creditsPanel extends JPanel{
    private ImageIcon icon;
    private JLabel fotoAxel, nombre;
    private Color lightBrown = new Color(102, 51, 0);
    public soundManager soundManager;
    
    /** JPanel que da creditos al autor del juego y permite interactuar con el usuario */
    public creditsPanel(){
        this.setLayout(null);
        this.setPreferredSize(new Dimension(864, 672));
        this.setBackground(this.lightBrown);
        
        this.nombre = new JLabel("Autor: Axel Orlando Porras Gonzalez CI 29545523");
        Font font = new Font("Arial", Font.BOLD, 16);
        this.nombre.setFont(font);
        this.nombre.setBounds(182, 60, 500, 15);
        this.add(this.nombre);
        this.icon = new ImageIcon(this.getClass().getResource("images/axelFoto.png"));
        this.fotoAxel = new JLabel(this.icon);
        this.fotoAxel.setBounds(182, 85, this.icon.getIconWidth(), this.icon.getIconHeight());
        this.add(this.fotoAxel);
    }
}
