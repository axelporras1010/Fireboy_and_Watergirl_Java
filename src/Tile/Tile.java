
package Tile;

import java.awt.Image;
import javax.swing.ImageIcon;
import main.GamePanel;
import main.GamePanel2;


public class Tile {
    public ImageIcon icon;
    public Image image;
    public int size;
    
    /** Clase encargada de definir los bloques que componen los escenarios de los niveles */
    public Tile(GamePanel gp){
        this.icon = new ImageIcon(this.getClass().getResource("images/block.png"));
        this.image = this.icon.getImage();
        this.size = gp.tileSize; 
    }
    
    /** Clase encargada de definir los bloques que componen los escenarios de los niveles */
    public Tile(GamePanel2 gp){
        this.icon = new ImageIcon(this.getClass().getResource("images/block.png"));
        this.image = this.icon.getImage();
        this.size = gp.tileSize; 
    }
    
}
