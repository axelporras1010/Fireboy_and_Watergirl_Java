
package objects;

import entity.Player;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import soundManager.soundManager;

public class lava extends objects{
    private int death;
    private soundManager soundManager = new soundManager();
    
    /** Clase que maneja los pozos de lava de los niveles y como se relacionan con
     el jugador */
    public lava(int x, int y, int width, int height, Player player){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.hitbox = new Rectangle( this.x, this.y-10, this.width-5, this.height);
        this.player = player;
        this.intersect = false;
        this.draw = true;
        this.icon = new ImageIcon(this.getClass().getResource("images/lava.png"));
        this.image = this.icon.getImage();
        this.death = 0;
    }


    @Override
    public boolean intersectsPlayer() {
        if(this.hitbox.intersects(player.hitbox) && this.player.typeOfPlayer!=0){
            this.death+=1;
            if(this.death==1)
                soundManager.playDeathMusic();
            return true;
        }
        return false;
    }
    
    @Override
    public void draw(Graphics2D g2){
        this.intersect = intersectsPlayer();
        if(intersect){
            this.player.draw=false;   
        }
        g2.drawImage(this.image, this.x, this.y, this.width, this.height, null);
    }
}
