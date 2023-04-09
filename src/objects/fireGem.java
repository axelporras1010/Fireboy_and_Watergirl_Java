
package objects;

import entity.Player;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import soundManager.soundManager;


public class fireGem extends objects{
    private soundManager soundManager = new soundManager();
    public int found;
    
    /** Clase que maneja las gemas de fuego de los niveles y como relacionan con su
     entorno y el jugador */
    public fireGem(int x, int y, int width, int height, Player player){
        this.x = x+8;
        this.y = y+16;
        this.width = width-16;
        this.height = height-16;
        this.hitbox = new Rectangle( this.x+8, this.y+10, this.width-16, this.height-16);
        this.player = player;
        this.intersect = false;
        this.draw = true;
        this.icon = new ImageIcon(this.getClass().getResource("images/fireGem.png"));
        this.image = this.icon.getImage();
        this.found = 0;
    }


    @Override
    public boolean intersectsPlayer() {
        if(this.hitbox.intersects(player.hitbox) && this.player.typeOfPlayer == 0){
            this.found+=1;
            if(this.found==1){
                this.soundManager.playGemMusic();
                if(player.gp!=null)
                    this.player.gp.levelOne.foundedGems+=1;
                if(player.gp2!=null)
                    this.player.gp2.levelTwo.foundedGems+=1;
            }   
            this.draw = false;
            return true;
        }
        return false;
    }
    
    @Override
    public void draw(Graphics2D g2){
        this.intersect = intersectsPlayer();
        if(!intersect && this.draw){
            g2.drawImage(this.image, this.x, this.y, this.width, this.height, null);
        }
    }
    
}
