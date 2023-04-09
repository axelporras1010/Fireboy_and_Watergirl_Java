
package objects;

import entity.Player;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import soundManager.soundManager;


public class elevator extends objects{
    private boolean activated;
    private soundManager soundManager = new soundManager();
    private int firstY;
    private lever lever;
    
    /** Clase que maneja a los elevadores puestos en los niveles */
    public elevator(int x, int y, int width, int height, Player player, lever lever){
        this.x = x;
        this.y = y;
        this.firstY = y;
        this.width = width;
        this.height = height;
        this.hitbox = new Rectangle(this.x+2, this.y+3, this.width-6, this.height-8);
        this.player = player;
        this.intersect = false;
        this.draw = true;
        this.icon = new ImageIcon(this.getClass().getResource("images/elevator.png"));
        this.image = this.icon.getImage();
        this.activated = false;
        this.lever = lever;
    }


    @Override
    public boolean intersectsPlayer() {
        if(this.hitbox.intersects(player.hitbox)){
            return true;
        }
        return false;
    }
    
    @Override
    public void draw(Graphics2D g2){
        g2.drawImage(this.image, this.x, this.y, this.width, this.height, null);
    }
    
    /** Actualiza las posiciones del elevador dependiendo de las interacciones 
     del jugador */
    public void update(){
        if(this.lever.stateOfLever==1 && this.y <= (this.firstY+86)){
            this.y+=1;
            this.hitbox.y+=1;
        }else if(this.lever.stateOfLever==0 && this.y >= (this.firstY)){
            this.y-=1;
            this.hitbox.y-=1;
        }
    }
}
