
package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class finishLevelOnePanel extends JPanel{
    private int minutes, seconds, fireGems, waterGems, foundedFireGems, foundedWaterGems;
    private ImageIcon icon;
    private JLabel levelFinished, time, fGem, wGem, fireGem, waterGem;
    
    /** Panel encargado de mostrar los valores obtenidos por el usuario en el nivel uno
     e interactuaqr con el mismo */
    public finishLevelOnePanel(int minutes, int seconds, int fireGems, int waterGems, int foundedFireGems, int foundedWaterGems){
        this.minutes = minutes;
        this.seconds = seconds;
        this.fireGems = fireGems;
        this.waterGems = waterGems;
        this.foundedFireGems = foundedFireGems;
        this.foundedWaterGems=foundedWaterGems;
        this.fGem = new JLabel();
        this.wGem = new JLabel();
        this.time = new JLabel();
        
        this.setLayout(null);
        this.setPreferredSize(new Dimension(864, 672));
        this.setBackground(new Color(102, 51, 0));
    }
    
    /** Metodo encargado especificamente de darle los valores al JPanel para ver
     los resultados obtenidos */
    public void mostrar(){
        this.icon = new ImageIcon(this.getClass().getResource("images/finishedLevel.png"));
        this.levelFinished = new JLabel(this.icon);
        this.levelFinished.setBounds(180, 0, this.icon.getIconWidth(), this.icon.getIconHeight());
        this.add(this.levelFinished);
        
        this.icon = new ImageIcon(this.getClass().getResource("images/fireGem.png"));
        this.fireGem = new JLabel(this.icon);
        this.fireGem.setBounds(350, 330, this.icon.getIconWidth(), this.icon.getIconHeight());
        this.add(this.fireGem);
        
        this.icon = new ImageIcon(this.getClass().getResource("images/waterGem.png"));
        this.waterGem = new JLabel(this.icon);
        this.waterGem.setBounds(620, 330, this.icon.getIconWidth(), this.icon.getIconHeight());
        this.add(this.waterGem);
        
        Font font = new Font("Arial", Font.BOLD, 18);
        
        String timeString = String.format("Tiempo: %02d:%02d", this.minutes, this.seconds);
        this.time.setFont(font);
        this.time.setText(timeString);
        this.time.setBounds(100, 430, 150, 80);
        this.add(this.time);
        
        String string  = String.format("Fire Gems: %d/%d", this.foundedFireGems, this.fireGems);
        this.fGem.setText(string);
        this.fGem.setFont(font);
        this.fGem.setBounds(350, 430, 150, 80);
        this.add(this.fGem);
        
        string  = String.format("Water Gems: %d/%d", this.foundedWaterGems, this.waterGems);
        this.wGem.setText(string);
        this.wGem.setFont(font);
        this.wGem.setBounds(600, 430, 150, 80);
        this.add(this.wGem); 
    }
    
    public void deleteElements(){
        this.time.setText("");
        this.fGem.setText("");
        this.wGem.setText("");
    }
    

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public void setFireGems(int fireGems) {
        this.fireGems = fireGems;
    }

    public void setWaterGems(int waterGems) {
        this.waterGems = waterGems;
    }

    public void setFoundedFireGems(int foundedFireGems) {
        this.foundedFireGems = foundedFireGems;
    }

    public void setFoundedWaterGems(int foundedWaterGems) {
        this.foundedWaterGems = foundedWaterGems;
    }
    
    
}
