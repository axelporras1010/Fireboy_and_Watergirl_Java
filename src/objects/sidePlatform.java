
package objects;

import entity.Player;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import soundManager.soundManager;


public class sidePlatform extends objects{
    private boolean activated;
    private soundManager soundManager = new soundManager();
    private int firstX;
    private groundButton groundButton;
    
    /** Clase que maneja las plataformas en horizontal de los niveles, como se relacionan con su
     entorno y el jugador */
    public sidePlatform(int x, int y, int width, int height, Player player, groundButton groundButton){
        this.x = x;
        this.y = y;
        this.firstX = x;
        this.width = width;
        this.height = height;
        this.hitbox = new Rectangle(this.x+2, this.y+3, this.width-6, this.height-8);
        this.player = player;
        this.intersect = false;
        this.draw = true;
        this.icon = new ImageIcon(this.getClass().getResource("images/elevator.png"));
        this.image = this.icon.getImage();
        this.activated = false;
        this.groundButton = groundButton;
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
    
    //* Actualiza el estado de la plataforma dependiendo de la interaccion del jugador con el entorno */
    public void update(){
        if(this.groundButton.stateOfButton==1 && this.x >= (this.firstX-this.width)){
            this.x-=1;
            this.hitbox.x-=1;
        }else if(this.groundButton.stateOfButton==0 && this.x <= (this.firstX)){
            this.x+=1;
            this.hitbox.x+=1;
        }
    }
}
