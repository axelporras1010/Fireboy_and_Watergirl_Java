
package Levels;

import Tile.Tile;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import main.GamePanel;
import main.GamePanel2;

public class level {
    protected GamePanel gp;
    protected GamePanel2 gp2;
    protected Tile tile;
    protected   Rectangle hitbox [];
    protected int [][] design;
    public boolean levelMusic;
    public boolean closeLevel;
    public int seconds, minutes, fireGems, waterGems, foundedGems;
    
    public void update(){}
    public void draw(Graphics2D g2){}
    
    public Rectangle [] getHitbox() {
        return this.hitbox;
    }
    
    public boolean getCloseLevel(){
        return this.closeLevel;
    }
    
    public void setSeconds(int second){
        this.seconds = second;
    }
    
    public int getSeconds(){
        return this.seconds;
    }
    
    public void setMinutes(int minutes){
        this.minutes = minutes;
    }
    
    public int getMinutes(){
        return this.minutes;
    }
}



