
package entity;

import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class entity {
    public int x, y, speed, width, height;
    public Rectangle hitbox = new Rectangle();
    
    public ImageIcon front1, front2, right1, right2, left1, left2;
    public String direction;
    
    public int spriteCounter = 0;
    public int spriteNum = 1;
    
    public entity(int x, int y, int speed, int width, int height){
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;
        this.hitbox.setBounds(this.x+12, this.y+8, this.width-24, this.height-16);
    }
}
