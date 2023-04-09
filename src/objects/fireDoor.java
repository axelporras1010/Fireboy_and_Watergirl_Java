
package objects;

import entity.Player;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import soundManager.soundManager;


public class fireDoor extends objects{
    soundManager soundManager = new soundManager();
    public int found;
    
    /** Clase que maneja las puertas de fuego de los niveles y como relacionan con su
     entorno y el jugador */
    public fireDoor(int x, int y, int width, int height, Player player){
        this.x = x;
        this.y = y+5;
        this.width = width;
        this.height = height;
        this.hitbox = new Rectangle( this.x+8, this.y+10, this.width-18, this.height-12);
        this.player = player;
        this.intersect = false;
        this.draw = true;
        this.icon = new ImageIcon(this.getClass().getResource("images/fireDoor.png"));
        this.image = this.icon.getImage();
        this.found = 0;
    }


    @Override
    public boolean intersectsPlayer() {
        if(this.hitbox.intersects(player.hitbox) && this.player.typeOfPlayer==0){
            return true;
        }
        return false;
    }
    
    @Override
    public void draw(Graphics2D g2){
        g2.drawImage(this.image, this.x, this.y, this.width, this.height, null);
    }
    
    /** Metodo que actualiza las interacciones del jugador con la puerta */ 
    public void update(){
        boolean intersect = intersectsPlayer();
        if(intersect){
            this.found+=1;
            if(found==1){
                soundManager.playDoorMusic();
            }
            if(this.player.gp!=null)
                this.player.gp.playerFinishedLevel = true;
            if(this.player.gp2!=null)
                this.player.gp2.playerFinishedLevel = true;
        }        
    }
}
