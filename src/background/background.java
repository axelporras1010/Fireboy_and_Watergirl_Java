
package background;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import main.GamePanel;
import main.GamePanel2;


public class background {
    
    ImageIcon icon;
    Image image;
    GamePanel gp;
    GamePanel2 gp2;
    
    /** Clase encargada de dibujar el background de los niveles */
    public background(GamePanel gp){
        this.gp = gp;
        getBackground();
    }
    
    /** Clase encargada de dibujar el background de los niveles */
    public background(GamePanel2 gp2){
        this.gp2 = gp2;
        getBackground();
    }
    
    /** Obten la imagen del background */
    public void getBackground(){
        this.icon = new ImageIcon(this.getClass().getResource("image/rBackground.png"));
        this.image = this.icon.getImage();
    }
    
    /** Dibuja el background dependiendo de en que nivel esten */
    public void draw(Graphics2D g2){
        if(gp!=null)
            g2.drawImage(this.image, 0, 0, gp.screenWidth, gp.screenHeight, null);
        if(gp2!=null)
            g2.drawImage(this.image, 0, 0, gp2.screenWidth, gp2.screenHeight, null);
    }
    
}
