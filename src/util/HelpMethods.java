
package util;

import Levels.level;
import Levels.levelOne;
import Levels.levelTwo;
import entity.entity;
import java.awt.Rectangle;
import objects.moveCube;
import objects.giratoryPlatform;

public class HelpMethods {
    
    public boolean canMove(entity entity, level level){
        boolean move=true;
        
        for( Rectangle hitbox : level.getHitbox()){
            if(entity.hitbox.intersects(hitbox)){
                move = false;
            }
        }
        
        levelOne levelOneaux = (levelOne) level;
        if(entity.hitbox.intersects(levelOneaux.elevator.hitbox))
            move = false;
        
        if(entity.hitbox.intersects(levelOneaux.sidePlatform.hitbox))
            move = false;
        
        if(entity.hitbox.intersects(levelOneaux.moveCube.hitbox) && entity.direction == "left"){
            move = false;
            levelOneaux.moveCube.x-=entity.speed;
            levelOneaux.moveCube.hitbox.x-=entity.speed;
         }
         
        if(entity.hitbox.intersects(levelOneaux.moveCube.hitbox) && entity.direction == "right"){
            move = false;
            levelOneaux.moveCube.x+=entity.speed;
            levelOneaux.moveCube.hitbox.x+=entity.speed;
         }
        
        return move;
    }
    
    public boolean canMove2(entity entity, level level){
        boolean move=true;
        
        for( Rectangle hitbox : level.getHitbox()){
            if(entity.hitbox.intersects(hitbox)){
                move = false;
            }
        }
        
        //Editar esto a valores de levelTwo
        levelTwo leveltwoaux = (levelTwo) level;
        if(entity.hitbox.intersects(leveltwoaux.sidePlatform.hitbox))
            move = false;
        
        if(entity.hitbox.intersects(leveltwoaux.upPlatform.hitbox))
            move = false;
        
        for(giratoryPlatform giratoryPlatform : leveltwoaux.giratoryPlatform){
            if(entity.hitbox.intersects(giratoryPlatform.hitbox) && entity.hitbox.x < giratoryPlatform.hitbox.x+giratoryPlatform.hitbox.width/2){
                giratoryPlatform.direction = false;
                giratoryPlatform.move = true;
                move = false;
            }

            if(entity.hitbox.intersects(giratoryPlatform.hitbox) && entity.hitbox.x > giratoryPlatform.hitbox.x+giratoryPlatform.hitbox.width/2){
                giratoryPlatform.direction = true;
                giratoryPlatform.move = true;
                move = false;
            }

            if(entity.hitbox.intersects(giratoryPlatform.shape.getBounds2D())){
                giratoryPlatform.balance = true;
                move = false;
            }
            else{
                giratoryPlatform.balance = false;
                giratoryPlatform.move = false;
            }
                

        }
        
        return move;
    }
    
    public boolean gravityCube(moveCube cube, level level){
        for(Rectangle hitbox : level.getHitbox()){
            if(cube.hitbox.intersects(hitbox))
                return false;
        }
        return true;
    }
    
    public boolean canMoveCube(moveCube entity, level level){
        boolean move=true;
        
        for( Rectangle hitbox : level.getHitbox()){
            if(entity.hitbox.intersects(hitbox)){
                move = false;
            }
        }
        return move;
    }
}
