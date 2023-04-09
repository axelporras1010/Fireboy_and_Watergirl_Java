
package objects;

import entity.Player;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;


public abstract class objects {
    public int x, y, width, height;
    public Rectangle hitbox;
    public boolean typeOfObject, intersect, draw;
    public Player player;
    public ImageIcon icon;
    public Image image;
    
    /** Dibuja todos el objeto en particular */
    public abstract void draw(Graphics2D g2);
    /** Revisa si el objeto intersecta con el jugador */
    public abstract boolean intersectsPlayer();
}
