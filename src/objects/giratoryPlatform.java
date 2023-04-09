
package objects;

import entity.Player;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;


public class giratoryPlatform extends objects{
    public Shape shape;
    public double angle;
    public Point pivot;
    public boolean direction;
    public float speed;
    public float maxAngle;
    public Rectangle hitboxR;
    public boolean move, balance, back, low;
    public int maxX, maxY, firstX, firstY, xSpeed, ySpeed, balanced, onPlatform;
    
    /** Clase que maneja las plataformas giratorias de los niveles y como se relacionan con
     el jugador */
    public giratoryPlatform(int x, int y, int width, int height, float maxAngle, float speed, Player player) {
        this.x = x;
        this.y = y;
        this.firstX = x;
        this.firstY = y;
        this.width = width;
        this.height = height;
        this.hitbox = new Rectangle(x, y, width/2, height);
        this.hitboxR = new Rectangle(x+width/2, y, width/2, height);
        this.shape = new Rectangle(x, y, width, height);
        this.angle = 0.0;
        this.pivot = new Point(x + (width / 2), y + (height / 2));
        this.direction = true;
        this.speed = speed;
        this.maxAngle = maxAngle;
        this.move = false;
        this.maxX = 40;
        this.maxY = 40;
        this.balance = false;
        this.back = false;
        this.low = false;
        this.onPlatform=0;
        this.player = player;
        this.xSpeed=1;
        this.ySpeed= -1;
        this.onPlatform=0;
        this.balanced=10;
    }

    public void update() {
        if(move){
          if (direction) {
            angle += speed;
            //this.player.y+=1;
            //this.player.hitbox.y+=1;
          } else {
            angle -= speed;
            //this.player.y-=1;
            //this.player.hitbox.y-=1;
          }  

          if (angle == maxAngle) {
            speed*=-1;
            move=false;
          } else if (angle == -maxAngle) {
            speed*=-1;
            move=false;
          } 
          
          
        }
        if(balance){
            onPlatform+=1;
            if(onPlatform==1)
                balanced=0;
        if(balanced<4){
            if(back){
                this.xSpeed*=-1;
                back = false;
            }
            if(low){
                this.ySpeed*=-1;
                low = false;
            }
            this.x+=xSpeed;
            this.y+=ySpeed;
            if(balance){
                this.player.x+=xSpeed;
                this.player.hitbox.x+=xSpeed;
                this.player.y+=ySpeed;
                this.player.hitbox.y+=ySpeed;
            }
            if(x>=firstX+maxX)
                back = true;
            if(x<=firstX-maxX)
                back = true;
            if(y<=firstY-maxY)
                low = true;
            if(y>=firstY){
                low = true;    
                balanced+=1;
            }
        }    
        }
        
        if(balanced<4){
            if(back){
                this.xSpeed*=-1;
                back = false;
            }
            if(low){
                this.ySpeed*=-1;
                low = false;
            }
            this.x+=xSpeed;
            this.y+=ySpeed;
            if(balance){
                this.player.x+=xSpeed;
                this.player.hitbox.x+=xSpeed;
                this.player.y+=ySpeed;
                this.player.hitbox.y+=ySpeed;
            }
            if(x>=firstX+maxX)
                back = true;
            if(x<=firstX-maxX)
                back = true;
            if(y<=firstY-maxY)
                low = true;
            if(y>=firstY){
                low = true;    
                balanced+=1;
            }
        }    
        
        if(player.hitbox.x<hitbox.x+10 || player.hitbox.x>hitbox.x+hitbox.width+10)
            onPlatform=0;

        AffineTransform transform = AffineTransform.getRotateInstance(Math.toRadians(angle), x + (width / 2), y + (height / 2));
        shape = transform.createTransformedShape(new Rectangle(x, y, width, height));
        
        hitbox = shape.getBounds();
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.darkGray);
        g2.fill(shape);
    }
    
    /** Metodo getter del hibox de las plataformas giratorias */
    public Rectangle getHitbox() {
        return hitbox;
    }   

    @Override
    public boolean intersectsPlayer() {
        return false;
    }
}
