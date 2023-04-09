
package objects;

import entity.Player;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import soundManager.soundManager;


public class groundButton extends objects{
    private boolean activated;
    private soundManager soundManager = new soundManager();
    public int stateOfButton, canChange;
    
    /** Clase que maneja los botones de suello de los niveles y como se relacionan con
     el jugador */
    public groundButton(int x, int y, int width, int height, Player player){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.hitbox = new Rectangle( this.x+6, this.y+7, this.width-12, this.height-12);
        this.player = player;
        this.intersect = false;
        this.draw = true;
        this.icon = new ImageIcon(this.getClass().getResource("images/groundButton.png"));
        this.image = this.icon.getImage();
        this.activated = false;
        this.stateOfButton = 0;
        this.canChange = 0;
    }


    @Override
    public boolean intersectsPlayer() {
        if(this.hitbox.intersects(player.hitbox)){
            canChange++;
            return true;
        }
        return false;
    }
    
    @Override
    public void draw(Graphics2D g2){
        g2.drawImage(this.image, this.x, this.y, this.width, this.height, null);
    }
    
    /** Actualiza el boton para saber si este interactua con el jugador y lo que ocurre
     despues de esto */
    public void update(){
        this.intersect = intersectsPlayer();
        if(this.intersect && this.stateOfButton==0 && this.canChange==1){
            this.soundManager.stopPlatformMusic();
            this.stateOfButton=1;
            this.soundManager.playPlatformMusic();
        }    
        else if(this.intersect && this.stateOfButton==1 && this.canChange==1){
                this.soundManager.stopPlatformMusic();
                this.stateOfButton=0;
                this.soundManager.playPlatformMusic();;
        }    
        if((this.player.hitbox.x > this.hitbox.x+this.hitbox.width+1 || this.player.hitbox.x+this.player.hitbox.width < this.hitbox.x-1))
            this.canChange = 0;
        
    }
}
