
package objects;

import entity.Player;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import soundManager.soundManager;


public class fan extends objects{
    public int yPrevious, found=0;
    private int spriteCounter=0, spriteNum=1;
    private Image fan2, fan3, fan4;
    private soundManager soundManager = new soundManager();
    
    /** Clase que maneja los ventiladores que se encuentran en los niveles y 
     como se relacionan con el jugador */
    public fan(int x, int y, int width, int height, Player player){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.hitbox = new Rectangle( this.x, this.y-5, this.width, this.height-10);
        this.player = player;
        this.intersect = false;
        this.draw = true;
        this.icon = new ImageIcon(this.getClass().getResource("images/fan.png"));
        this.image = this.icon.getImage();
        this.icon = new ImageIcon(this.getClass().getResource("images/fan2.png"));
        this.fan2 = this.icon.getImage();
        this.icon = new ImageIcon(this.getClass().getResource("images/fan3.png"));
        this.fan3 = this.icon.getImage();
        this.icon = new ImageIcon(this.getClass().getResource("images/fan4.png"));
        this.fan4 = this.icon.getImage();
    }


    @Override
    public boolean intersectsPlayer() {
        if(this.hitbox.intersects(player.hitbox)){
            this.found=1;
            if(this.found == 1){
                this.soundManager.playFanMusic();
            }
            return true;
        }
        return false;
    }
    
    @Override
    public void draw(Graphics2D g2){
        this.intersect = intersectsPlayer();
        if(intersect){
            this.player.fan = true;
            this.player.keyH.upPressed=true;
        }else{
            if(this.player.inGround){
                this.player.fan = false;
            }
        }
        switch(this.spriteNum){
            case 1:
                g2.drawImage(this.image, this.x, this.y, this.width, this.height, null);
                break;
            case 2:
                g2.drawImage(this.fan2, this.x, this.y, this.width, this.height, null);
                break;
            case 3:
                g2.drawImage(this.fan3, this.x, this.y, this.width, this.height, null);
                break;
            case 4:
                g2.drawImage(this.fan4, this.x, this.y, this.width, this.height, null);
                break;
        }
    }
    
    /** Actualiza las imagenes que se muestran de los ventiladores */
    public void update(){
        this.spriteCounter++;
        if(this.spriteCounter>10){
            switch(this.spriteNum){
                case 1: this.spriteNum = 2; break;
                case 2: this.spriteNum = 3; break;
                case 3: this.spriteNum = 4; break;
                case 4: this.spriteNum = 1; break;    
            }
            this.spriteCounter = 0;
        }
    }
}
