
package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import main.GamePanel;
import main.KeyHandler;
import javax.swing.ImageIcon;
import main.GamePanel2;
import soundManager.soundManager;
import util.HelpMethods;


public final class Player extends entity{
    
    public  GamePanel gp;
    public  GamePanel2 gp2;
    public  KeyHandler keyH;
    public  boolean canMove, inGround=true, jump=false, roof=false, fan = false, onTop = false, steps = true;
    private static HelpMethods help = new HelpMethods();
    private int airSpeed, yPos;
    public volatile int typeOfPlayer;
    public boolean  draw;
    private soundManager soundManager = new soundManager();
    
    /** Clase encargada de dar vida al personaje, sus mecanicas y como puede interactuar
     * el mismo con su entorno */
    public Player(int x, int y, int speed, int width, int height, GamePanel gp, KeyHandler keyH, int typeOfPlayer){
        super(x, y, speed, width, height);
        this.gp = gp;
        this.keyH = keyH;
        this.direction = "front";
        canMove = true;
        airSpeed = gp.tileSize*3;
        this.typeOfPlayer = typeOfPlayer;
        getPlayerImage();
        this.draw = true;
    }
    
    /** Clase encargada de dar vida al personaje, sus mecanicas y como puede interactuar
     * el mismo con su entorno */
    public Player(int x, int y, int speed, int width, int height, GamePanel2 gp2, KeyHandler keyH, int typeOfPlayer){
        super(x, y, speed, width, height);
        this.gp2 = gp2;
        this.keyH = keyH;
        this.direction = "front";
        canMove = true;
        airSpeed = gp2.tileSize*3;
        this.typeOfPlayer = typeOfPlayer;
        getPlayerImage();
        this.draw = true;
    }
    
    /** Obten las imagenes para las animaciones del jugador */
    public void getPlayerImage(){
        if(this.typeOfPlayer == 0){
            this.front1 = new ImageIcon(this.getClass().getResource("images/FireBoyFront1.png"));
            this.front2 = new ImageIcon(this.getClass().getResource("images/FireBoyFront2.png"));
            this.right1 = new ImageIcon(this.getClass().getResource("images/FireBoyR1.png"));
            this.right2 = new ImageIcon(this.getClass().getResource("images/FireBoyR2.png"));
            this.left1 = new ImageIcon(this.getClass().getResource("images/FireBoyL1.png"));
            this.left2 = new ImageIcon(this.getClass().getResource("images/FireBoyL2.png"));
        }else{
            this.front1 = new ImageIcon(this.getClass().getResource("images/watergirlFront1.png"));
            this.front2 = new ImageIcon(this.getClass().getResource("images/watergirlFront1.png"));
            this.right1 = new ImageIcon(this.getClass().getResource("images/watergirlR1.png"));
            this.right2 = new ImageIcon(this.getClass().getResource("images/watergirlR2.png"));
            this.left1 = new ImageIcon(this.getClass().getResource("images/watergirlL1.png"));
            this.left2 = new ImageIcon(this.getClass().getResource("images/watergirlL2.png"));
        }
    }
    
    /** Actualiza el jugador con respecto a los inputs dados por el jugador y como
     se relaciona con su entorno*/
    public void update(){
        
        if (keyH.upPressed == true || keyH.leftPressed==true || keyH.rightPressed==true){
            if(keyH.upPressed==true && inGround){
               inGround = false;
               jump=true;
               yPos = this.y;
               if(this.typeOfPlayer==0)
                   this.soundManager.playJumpFbMusic();
               else
                   this.soundManager.playJumpWgMusic();
               this.direction  = "up";
               this.keyH.upPressed = false;
            }
            else if(keyH.rightPressed==true){
                this.x+=this.speed;
                this.hitbox.x+=speed;
                if(this.gp!=null){
                    canMove = help.canMove(this, this.gp.levelOne);
                }else if(this.gp2!=null){
                    canMove = help.canMove2(this, this.gp2.levelTwo);
                }
                if(canMove==false){
                   this.x-=this.speed; 
                   this.hitbox.x-=speed;
                }
                if(this.hitbox.x+this.hitbox.width>=864){
                   this.x-=this.speed; 
                   this.hitbox.x-=speed;
                }
                this.direction  = "right";
                
            }    
            else if (keyH.leftPressed==true){
                this.x-=this.speed;
                this.hitbox.x-=speed;
                if(this.gp!=null){
                    canMove = help.canMove(this, this.gp.levelOne);
                }else if(this.gp2!=null){
                    canMove = help.canMove2(this, this.gp2.levelTwo);
                }
                if(canMove==false){
                   this.x+=this.speed; 
                   this.hitbox.x+=speed;
                }
                if(this.hitbox.x<=0){
                   this.x+=this.speed; 
                   this.hitbox.x+=speed;
                }
                this.direction  = "left";
            }
        }else{
            this.direction="front";     
        }
        
        if(!inGround && jump){
            roof=true;
            this.y-=(this.speed); 
            this.hitbox.y-=(this.speed);
            if(!fan){
                if(this.gp!=null){
                    if(this.y<=(this.yPos-(this.gp.tileSize*2))){
                    jump=false;
                    roof=false;
                    }
                }else if(this.gp2!=null){
                    if(this.y<=(this.yPos-(this.gp2.tileSize*2))){
                    jump=false;
                    roof=false;
                    }
                }
            }
            else{
                if(this.gp!=null){
                    if(this.y<=(this.gp.tileSize*3)){
                    jump=false;
                    roof=false;
                    }
                }else if(this.gp2!=null){
                    if(this.y<=(this.gp2.tileSize*3)){
                    jump=false;
                    roof=false;
                    }
                }
                
            }
            this.keyH.upPressed = false;
        }
        else if(!this.onTop){
            this.y+=this.speed;
            this.hitbox.y+=this.speed;
        }

        if(this.gp!=null){
            canMove = help.canMove(this, this.gp.levelOne);
        }else if(this.gp2!=null){
            canMove = help.canMove2(this, this.gp2.levelTwo);
        }
        if(!canMove && !roof){
            this.y-=this.speed; 
            this.hitbox.y-=speed;
            inGround = true;
        }
        if(canMove==false && roof){
            jump = false;
            roof = false;
        }
        
        this.spriteCounter++;
            if(this.spriteCounter>10){
                if(this.spriteNum==1){
                    this.spriteNum=2;
                }
                else if(this.spriteNum==2){
                    this.spriteNum=1;
                }
                this.spriteCounter=0;
            }
    }
    
    /** Dibuja al jugador */
    public void draw(Graphics2D g2){
        
        Image image = null;
        
        switch(this.direction){
            case "up": 
                if(this.spriteNum==1){
                    image = front1.getImage();
                }
                if(this.spriteNum==2){
                    image = front2.getImage();
                }
                break;
            case "down" : 
                if(this.spriteNum==1){
                    image = front1.getImage();
                }
                if(this.spriteNum==2){
                    image = front2.getImage();
                }
                break;
            case "right":
                if(this.spriteNum==1){
                    image = right1.getImage();
                }
                if(this.spriteNum==2){
                    image = right2.getImage();
                }
                break;
            case "left":
                if(this.spriteNum==1){
                    image = left1.getImage();
                }
                if(this.spriteNum==2){
                    image = left2.getImage();
                }
                break;
            default:
                if(this.spriteNum==1){
                    image = front1.getImage();
                }
                if(this.spriteNum==2){
                    image = front2.getImage();
                }
        }
        if(draw){
            if(gp!=null)
                g2.drawImage(image, this.x, this.y, gp.tileSize, gp.tileSize, null);
            if(gp2!=null)
                g2.drawImage(image, this.x, this.y, gp2.tileSize, gp2.tileSize, null);
        } 
    }
    
    /** Metodo setter para obtener el tipo de personaje */
    public void setTypeOfPlayer(int typeOfPlayer){
        this.typeOfPlayer = typeOfPlayer;
    }
}
