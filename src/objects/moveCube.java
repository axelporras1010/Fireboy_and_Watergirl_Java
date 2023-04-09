
package objects;

import entity.Player;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import util.HelpMethods;


public class moveCube extends objects{
    public boolean canMove, upMove;
    public HelpMethods help = new HelpMethods();
    public int yFirst;

    /** Clase que maneja los cubos que se mueven de los niveles, como se relacionan con su
     entorno y el jugador */
    public moveCube(int x, int y, int width, int height, Player player){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.hitbox = new Rectangle( this.x, this.y, this.width+1, this.height-6);
        this.player = player;
        this.intersect = false;
        this.draw = true;
        this.icon = new ImageIcon(this.getClass().getResource("images/moveCube.png"));
        this.image = this.icon.getImage();
        this.canMove = true;
        this.upMove = false;
        this.yFirst = y;
    }
    
    @Override
    public void draw(Graphics2D g2) {
        this.y+=this.player.speed*6;
        this.hitbox.y+=this.player.speed*6;
        canMove = help.gravityCube(this, this.player.gp.levelOne);
        if(canMove==false){
            this.y-=this.player.speed*6; 
            this.hitbox.y-=this.player.speed*6;
            if(this.y>this.yFirst+30){
                this.upMove = true;
            }
        }
        g2.drawImage(this.image, this.x-2, this.y, this.width+5, this.height, null);
    }

    @Override
    public boolean intersectsPlayer() {
        return false;
    }
    
    //* Actualiza como se gestiona el movimiento del jugador con respecto al cubo */
    public void update(){
        if( this.hitbox.y == this.player.hitbox.y+this.player.hitbox.height && this.upMove && this.player.hitbox.x > this.hitbox.x-this.player.hitbox.width && this.player.hitbox.x < this.hitbox.x+this.hitbox.width){
            this.player.onTop = true;
            this.player.canMove = true;
            this.player.inGround = true;
        }else{
            this.player.onTop = false;
        } 
    }
}
