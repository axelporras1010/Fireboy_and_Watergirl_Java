
package Levels;

import Tile.Tile;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import main.GamePanel;
import main.GamePanel2;
import objects.acid;
import objects.fan;
import objects.fireDoor;
import objects.fireGem;
import objects.giratoryPlatform;
import objects.groundButton;
import objects.lava;
import objects.moveCube;
import objects.sidePlatform;
import objects.upPlatform;
import objects.water;
import objects.waterDoor;
import objects.waterGem;
import soundManager.soundManager;


public class levelTwo extends level{
    private fireGem fireGem [] = new fireGem[8];
    private waterGem waterGem[] = new waterGem[8];
    private lava lava [] = new lava[2];
    private acid acid [] = new acid [2];
    private fireDoor fireDoor;
    private waterDoor waterDoor;
    private water water [] = new water [2];
    private groundButton groundButton [] = new groundButton [2];
    public sidePlatform sidePlatform;
    public upPlatform upPlatform;
    public giratoryPlatform giratoryPlatform [] = new giratoryPlatform [2];
    private soundManager soundManager = new soundManager();
    
    /** Clase encargada de dar valores y estructura al nivel dos, donde se encuentra su
     estructura y establece las bases para que jugador pueda interactuar con el mismo */
    public levelTwo(GamePanel2 gp){
        this.gp2 = gp;
        tile = new Tile(gp2);
        this.design = new int [][] {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                                    {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1},
                                    {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1},
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
        
        int x = 0, y = 0, aux=0;
        for(int i = 0 ; i < gp.maxScreenRow ; i++ ){
            for(int j = 0 ; j < gp.maxScreenCol ; j++ ){
                if(design[i][j] == 1)
                    aux+=1;
            }
        }
        this.hitbox = new Rectangle[aux];
        aux=0;
        for(int i = 0 ; i < gp.maxScreenRow ; i++ ){
            for(int j = 0 ; j < gp.maxScreenCol ; j++ ){
                if(design[i][j] == 1){
                    this.hitbox[aux] = new Rectangle(x, y, gp.tileSize, gp.tileSize);
                    aux++;
                }
                x+=gp.tileSize;
            }
            x=0;
            y+=gp.tileSize;
        }
        
        this.fireGems=8;
        this.waterGems=8;
        this.foundedGems=0;
        this.seconds = 0;
        this.minutes = 0;
        
        this.fireGem[0] = new fireGem((3*gp.tileSize), (gp.maxScreenRow-2)*gp.tileSize, gp.tileSize, gp.tileSize, gp.player );
        this.fireGem[1] = new fireGem((5*gp.tileSize), (gp.maxScreenRow-2)*gp.tileSize, gp.tileSize, gp.tileSize, gp.player );
        this.fireGem[2] = new fireGem((9*gp.tileSize), (gp.maxScreenRow-4)*gp.tileSize, gp.tileSize, gp.tileSize, gp.player );
        this.fireGem[3] = new fireGem((11*gp.tileSize), (gp.maxScreenRow-4)*gp.tileSize, gp.tileSize, gp.tileSize, gp.player );
        this.fireGem[4] = new fireGem((11*gp.tileSize), (gp.maxScreenRow-6)*gp.tileSize, gp.tileSize, gp.tileSize, gp.player );
        this.fireGem[5] = new fireGem((5*gp.tileSize), (gp.maxScreenRow-6)*gp.tileSize, gp.tileSize, gp.tileSize, gp.player );
        this.fireGem[6] = new fireGem((9*gp.tileSize), (4)*gp.tileSize, gp.tileSize, gp.tileSize, gp.player );
        this.fireGem[7] = new fireGem((6*gp.tileSize), (0)*gp.tileSize, gp.tileSize, gp.tileSize, gp.player );
        this.waterGem[0] = new waterGem((3*gp.tileSize), (gp.maxScreenRow-4)*gp.tileSize, gp.tileSize, gp.tileSize, gp.player );
        this.waterGem[1] = new waterGem((5*gp.tileSize), (gp.maxScreenRow-4)*gp.tileSize, gp.tileSize, gp.tileSize, gp.player );
        this.waterGem[2] = new waterGem((9*gp.tileSize), (gp.maxScreenRow-2)*gp.tileSize, gp.tileSize, gp.tileSize, gp.player );
        this.waterGem[3] = new waterGem((11*gp.tileSize), (gp.maxScreenRow-2)*gp.tileSize, gp.tileSize, gp.tileSize, gp.player );
        this.waterGem[4] = new waterGem((9*gp.tileSize), (gp.maxScreenRow-6)*gp.tileSize, gp.tileSize, gp.tileSize, gp.player );
        this.waterGem[5] = new waterGem((3*gp.tileSize), (gp.maxScreenRow-6)*gp.tileSize, gp.tileSize, gp.tileSize, gp.player );
        this.waterGem[6] = new waterGem((8*gp.tileSize), (4)*gp.tileSize, gp.tileSize, gp.tileSize, gp.player );
        this.waterGem[7] = new waterGem((8*gp.tileSize), (0)*gp.tileSize, gp.tileSize, gp.tileSize, gp.player );
        this.lava [0] = new lava((3*gp.tileSize), (gp.maxScreenRow-1)*gp.tileSize, gp.tileSize*3, gp.tileSize-16, gp.player);
        this.lava [1] = new lava((9*gp.tileSize), (gp.maxScreenRow-3)*gp.tileSize, gp.tileSize*3, gp.tileSize-16, gp.player);
        this.acid [0] = new acid((11*gp.tileSize), (gp.maxScreenRow-8)*gp.tileSize, gp.tileSize*4, gp.tileSize-16, gp.player);
        this.acid [1] = new acid((3*gp.tileSize), (gp.maxScreenRow-8)*gp.tileSize, gp.tileSize*4, gp.tileSize-16, gp.player);
        this.water [0] = new water((3*gp.tileSize), (gp.maxScreenRow-3)*gp.tileSize, gp.tileSize*3, gp.tileSize-16, gp.player);
        this.water [1] = new water((9*gp.tileSize), (gp.maxScreenRow-1)*gp.tileSize, gp.tileSize*3, gp.tileSize-16, gp.player);
        this.giratoryPlatform [0] = new giratoryPlatform((4*gp.tileSize), (gp.maxScreenRow-9)*gp.tileSize, gp.tileSize*2, gp.tileSize/3,(float) 11.25, (float) 0.25, this.gp2.player);
        this.giratoryPlatform [1] = new giratoryPlatform((12*gp.tileSize), (gp.maxScreenRow-9)*gp.tileSize, gp.tileSize*2, gp.tileSize/3,(float) 11.25, (float) 0.25, this.gp2.player);
        this.groundButton [0] = new groundButton((10*gp.tileSize), (0)*gp.tileSize+gp.tileSize/2+2, gp.tileSize-16, gp.tileSize/2, gp.player);
        this.groundButton [1] = new groundButton((13*gp.tileSize), (gp.maxScreenRow+3)*gp.tileSize/2+2, gp.tileSize-16, gp.tileSize/2, gp.player);
        this.sidePlatform = new sidePlatform((9*gp.tileSize), (1)*gp.tileSize, gp.tileSize*3, gp.tileSize-20, gp.player, this.groundButton[0]);
        this.upPlatform = new upPlatform((8*gp.tileSize), (7)*gp.tileSize, gp.tileSize-20, gp.tileSize*2, gp.player, this.groundButton[1]);
        this.fireDoor = new fireDoor((2*gp.tileSize), (0)*gp.tileSize, gp.tileSize, gp.tileSize, gp.player );
        this.waterDoor = new waterDoor((1*gp.tileSize), (0)*gp.tileSize, gp.tileSize, gp.tileSize, gp.player );
   
    }
    
    @Override
    /** Actualiza los componentes del nivel para saber que ocurre constantemente con
     ellos */
    public void update(){
        for(giratoryPlatform giratoryPlatform : this.giratoryPlatform){
            giratoryPlatform.update();
        }
        for(groundButton groundButton : this.groundButton){
            groundButton.update();
        }
        this.sidePlatform.update();
        this.upPlatform.update();
        this.fireDoor.update();
        this.waterDoor.update();
    }
    
    @Override
    /** Dibuja todos los componentes del nivel */
    public void draw(Graphics2D g2){
        int x = 0, y = 0;
        this.sidePlatform.draw(g2);
        this.upPlatform.draw(g2);
        for(int i = 0 ; i < gp2.maxScreenRow ; i++ ){
            for(int j = 0 ; j < gp2.maxScreenCol ; j++ ){
                if(design[i][j] == 1){
                    g2.drawImage(tile.image, x, y, gp2.tileSize, gp2.tileSize, null);
                }
                x+=gp2.tileSize;
            }
            x=0;
            y+=gp2.tileSize;
        }
        for(fireGem gem : this.fireGem){
            gem.draw(g2);
        }
        for(waterGem gem : this.waterGem){
            gem.draw(g2);
        }
        for(lava lava : this.lava){
            lava.draw(g2);
        }
        for(acid acid : this.acid){
            acid.draw(g2);
        }
        for(water water : this.water){
            water.draw(g2);
        }
        for(groundButton groundButton : this.groundButton){
            groundButton.draw(g2);
        }
        for(giratoryPlatform giratoryPlatform : this.giratoryPlatform){
            giratoryPlatform.draw(g2);
        }
        fireDoor.draw(g2);
        waterDoor.draw(g2);  
    }
}
