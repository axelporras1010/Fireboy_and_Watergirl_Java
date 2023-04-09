
package Levels;

import Tile.Tile;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import main.GamePanel;
import objects.acid;
import objects.elevator;
import objects.fan;
import objects.fireDoor;
import objects.fireGem;
import objects.giratoryPlatform;
import objects.groundButton;
import objects.lava;
import objects.lever;
import objects.moveCube;
import objects.sidePlatform;
import objects.water;
import objects.waterDoor;
import objects.waterGem;
import soundManager.soundManager;


public class levelOne extends level{
    private fireGem fireGem [] = new fireGem[3];
    private waterGem waterGem[] = new waterGem[3];
    private lava lava;
    private acid acid;
    private fan fan;
    private fireDoor fireDoor;
    private waterDoor waterDoor;
    private water water;
    public moveCube moveCube;
    private lever lever;
    private soundManager soundManager = new soundManager();
    public elevator elevator;
    private groundButton groundButton;
    public sidePlatform sidePlatform;
    
    /** Clase encargada de dar valores y estructura al nivel uno, donde se encuentra su
     estructura y establece las bases para que jugador pueda interactuar con el mismo */
    public levelOne(GamePanel gp){
        this.seconds = 0;
        this.minutes = 0;
        this.levelMusic = true;
        this.gp = gp;
        tile = new Tile(gp);
        this.design = new int [][] {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                                    {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
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
        this.fireGems=3;
        this.waterGems=3;
        this.foundedGems=0;
        
        this.fireGem[0] = new fireGem((3*gp.tileSize)+16, (gp.maxScreenRow-2)*gp.tileSize, gp.tileSize, gp.tileSize, gp.player );
        this.fireGem[1] = new fireGem((4*gp.tileSize), (gp.maxScreenRow/2-1)*gp.tileSize, gp.tileSize, gp.tileSize, gp.player );
        this.fireGem[2] = new fireGem((6*gp.tileSize), (1)*gp.tileSize, gp.tileSize, gp.tileSize, gp.player );
        this.waterGem[0] = new waterGem((6*gp.tileSize)+16, (gp.maxScreenRow-2)*gp.tileSize, gp.tileSize, gp.tileSize, gp.player );
        this.waterGem[1] = new waterGem((5*gp.tileSize), (gp.maxScreenRow/2-1)*gp.tileSize, gp.tileSize, gp.tileSize, gp.player );
        this.waterGem[2] = new waterGem((7*gp.tileSize), (1)*gp.tileSize, gp.tileSize, gp.tileSize, gp.player );
        this.lava = new lava((3*gp.tileSize), (gp.maxScreenRow-1)*gp.tileSize, gp.tileSize+28, gp.tileSize-16, gp.player);
        this.acid = new acid((13*gp.tileSize)-48, (gp.maxScreenRow-4)*gp.tileSize, gp.tileSize+28, gp.tileSize-16, gp.player);
        this.fan = new fan((16*gp.tileSize), (gp.maxScreenRow/2)*gp.tileSize, gp.tileSize*2, gp.tileSize, gp.player);
        this.fireDoor = new fireDoor((17*gp.tileSize), (1)*gp.tileSize, gp.tileSize, gp.tileSize, gp.player );
        this.waterDoor = new waterDoor((16*gp.tileSize), (1)*gp.tileSize, gp.tileSize, gp.tileSize, gp.player );
        this.water = new water((6*gp.tileSize), (gp.maxScreenRow-1)*gp.tileSize, gp.tileSize+28, gp.tileSize-16, gp.player);
        this.moveCube = new moveCube((4*gp.tileSize), 3*gp.tileSize-2, gp.tileSize-10, gp.tileSize-10, gp.player);
        this.lever = new lever((7*gp.tileSize), (gp.maxScreenRow-5)*gp.tileSize+10, gp.tileSize, gp.tileSize, gp.player);
        this.elevator = new elevator((0*gp.tileSize), (gp.maxScreenRow/2)*gp.tileSize, gp.tileSize*2-15, gp.tileSize-20, gp.player, this.lever);
        this.groundButton = new groundButton((11*gp.tileSize), (gp.maxScreenRow/2-1)*gp.tileSize+gp.tileSize/2+2, gp.tileSize-16, gp.tileSize/2, gp.player);
        this.sidePlatform = new sidePlatform((16*gp.tileSize), 4*gp.tileSize, gp.tileSize*2, gp.tileSize-20, gp.player, this.groundButton);
        this.closeLevel = false;
    }
    
    @Override
    /** Actualiza los componentes del nivel para saber que ocurre constantemente con
     ellos */
    public void update(){
        sidePlatform.update();
        fan.update();
        fireDoor.update();
        waterDoor.update();
        lever.update();
        groundButton.update();
        elevator.update();
        moveCube.update();
    }
    
    @Override
    /** Dibuja todos los componentes del nivel */
    public void draw(Graphics2D g2){
        int x = 0, y = 0;
        this.sidePlatform.draw(g2);
        for(int i = 0 ; i < gp.maxScreenRow ; i++ ){
            for(int j = 0 ; j < gp.maxScreenCol ; j++ ){
                if(design[i][j] == 1){
                    g2.drawImage(tile.image, x, y, gp.tileSize, gp.tileSize, null);
                }
                x+=gp.tileSize;
            }
            x=0;
            y+=gp.tileSize;
        }
        for(fireGem gem : this.fireGem){
            gem.draw(g2);
        }
        for(waterGem gem : this.waterGem){
            gem.draw(g2);
        }
        lava.draw(g2);
        acid.draw(g2);
        fan.draw(g2);
        fireDoor.draw(g2);
        waterDoor.draw(g2);
        water.draw(g2);
        moveCube.draw(g2);
        lever.draw(g2);
        groundButton.draw(g2);
        elevator.draw(g2);
    }
    
    
}
