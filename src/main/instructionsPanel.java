
package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class instructionsPanel extends JPanel{
    ImageIcon icon;
    JLabel instructionsImage, movement, fireBoy, waterGirl, acid, instructions, death;
    private Color lightBrown = new Color(102, 51, 0);
    
    /** Panel encargado de mostar las intrucciones y funcionamiento basico del
     juego */
    public instructionsPanel(){
        this.setLayout(null);
        this.setPreferredSize(new Dimension(864, 672));
        this.setBackground(this.lightBrown);
        
        Font font = new Font("Arial", Font.BOLD, 14);
        
        this.icon = new ImageIcon(this.getClass().getResource("images/instruccionesImage.png"));
        this.instructionsImage = new JLabel(this.icon);
        this.instructions = new JLabel("<html>En este juego vas a usar uno de dos personajes, tienes a Fire boy y a Water girl."
                + "Debes evitar una serie de obstáculos con el propósito de llegar a las puertas que verás en cada nivel. Cada personaje puede atravesar la puerta correspondiente a su color."
                + "Por último, no olvides recoger los diamantes!</html>");
        this.fireBoy = new JLabel("<html>Fire boy: Fire boy es un veloz  y perspicaz chico con la"
                + "capacidad de caminar sobre lava ardiente y recoger diamantes rojos"
                + "pero ten cuidado con el agua, ya que esta puede hacer que el se "
                + "evapore</html>");
        this.waterGirl = new JLabel("<html>Water girl: Water girl es una chica tranquila y"
                + "capaz de lo que sea, tiene la habilidad de caminar sobre el agua"
                + "y recoger diamantes azules pero ten cuidado con la lava, ya que esta"
                + "la puede quemar<html>");
        this.acid = new JLabel("<html>Acido: Ten mucho cuidado con los pozos de acido! ya que"
                + "estos pueden hacer que ambos chicos desaparezcan<html>");
        this.movement = new JLabel("<html>Movimiento: Para poder moverte por el mapa vas a hacer"
                + "uso de las flechas direcciones, tal cual como crees es que se usan"
                + "flecha derecha para ir a la derecha, flecha izquierda para su"
                + "contraparte y por ultimo flecha superior para saltar<html>");
        this.death = new JLabel("<html>Muerte: Si mueres se te dará la opción"
                + "de volver a empezar desde el nivel uno o volver al menu principal del"
                + "juego<html>");
        
        this.instructions.setFont(font);
        this.fireBoy.setFont(font);
        this.waterGirl.setFont(font);
        this.acid.setFont(font);
        this.movement.setFont(font);
        this.death.setFont(font);
        
        this.instructionsImage.setBounds(247, 50, this.icon.getIconWidth(), this.icon.getIconHeight());
        this.instructions.setBounds(130, 160, 650, 80);
        this.fireBoy.setBounds(130, 250, 650, 50);
        this.waterGirl.setBounds(130, 300, 650, 50);
        this.acid.setBounds(130, 360, 650, 50);
        this.movement.setBounds(130, 420, 650, 50);
        this.death.setBounds(130, 480, 650, 50);
        
        this.add(this.instructionsImage);
        this.add(this.instructions);
        this.add(this.fireBoy);
        this.add(this.waterGirl);
        this.add(this.acid);
        this.add(this.movement);
        this.add(this.death);
    }
}
