
package main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import soundManager.soundManager;

public class mainMenu extends JPanel{
    private ImageIcon icon;
    private JLabel  fireBoy, waterGirl, title;
    private ButtonGroup playerSelection;
    private JRadioButton fireBoyButton, waterGirlButton;
    public soundManager soundManager;
    private int tileSize = 64, typeOfPlayer;
    private JButton playButton;
    private Color lightBrown = new Color(102, 51, 0);
    private mainFrame mainFrame;
    
    /** JPanel encargado de obtener los datos del player y servir de puente para que el mismo
     usuario interactue y se vayan mostranod/ocultando los otros paneles */
    public mainMenu(mainFrame mainFrame){
        this.icon = new ImageIcon(this.getClass().getResource("images/FireBoyFront1.png"));
        this.fireBoy = new JLabel(icon);
        this.fireBoy.setBounds (864/2-220, 200, icon.getIconWidth(), icon.getIconHeight());
        this.add(fireBoy);
        this.icon = new ImageIcon(this.getClass().getResource("images/waterGirlFront1.png"));
        this.waterGirl = new JLabel(icon);
        this.waterGirl.setBounds (864/2, 200, icon.getIconWidth(), icon.getIconHeight());
        this.add(waterGirl);
        this.icon = new ImageIcon(this.getClass().getResource("images/titleSized.png"));
        this.title = new JLabel(icon);
        this.title.setBounds (864/2-icon.getIconWidth()/2, 50, icon.getIconWidth(), icon.getIconHeight());
        this.add(title);
        this.playerSelection = new ButtonGroup();
        this.fireBoyButton = new JRadioButton("Fire Boy", true);
        this.waterGirlButton = new JRadioButton("Water Girl", false);
        
        this.setPreferredSize(new Dimension(864, 672));
        this.setLayout(null);
        ManejaRadioButton handler = new ManejaRadioButton();
        this.waterGirlButton.setBounds(864/2+70, 400, 100, 25);
        this.waterGirlButton.setBackground(lightBrown);
        this.waterGirlButton.addItemListener(handler);
        this.add(waterGirlButton);
        this.fireBoyButton.setBounds(864/2-168, 400, 80, 25);
        this.fireBoyButton.addItemListener(handler);
        this.fireBoyButton.setBackground(lightBrown);
        this.add(fireBoyButton);
        playerSelection.add(fireBoyButton);
        playerSelection.add(waterGirlButton);
        this.setBackground(this.lightBrown);
        this.mainFrame=mainFrame;
    }
    
    
    /** Clase que permite saber que tipo de personaje escogio el usuario */
    private class ManejaRadioButton implements ItemListener{
        public void itemStateChanged(ItemEvent event){
            if ( event.getSource() == fireBoyButton)
                typeOfPlayer=0;
            else if ( event.getSource() == waterGirlButton)
                    typeOfPlayer=1;     
        }
    }
    
    /** Metodo para obtener el tipo de personaje */
    public int getTypeOfPlayer(){
        return this.typeOfPlayer;
    }
    
    /** Metodo getter del mainFrame principal */
    public mainFrame getMainFrame(){
        return this.mainFrame;
    }
}
