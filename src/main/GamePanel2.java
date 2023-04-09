
package main;

import Levels.level;
import Levels.levelTwo;
import background.background;
import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.Timer;
import soundManager.soundManager;


public class GamePanel2 extends JPanel implements Runnable{
    //Screen settings
    final int originalTileSize = 16;
    final int scale = 3;
    
    public int tileSize = originalTileSize * scale; // 48x48 tile
    public int maxScreenCol = 18;
    public int maxScreenRow = 14;
    public int screenWidth = tileSize * maxScreenCol; //864 pixels
    public int screenHeight = tileSize * maxScreenRow; //672 pixels
    
    //FPS
    int FPS = 60;
    
    //Game functions
    private KeyHandler keyH = new KeyHandler();
    private Thread gameThread;
    public soundManager soundManager = new soundManager();
    private  background background = new background(this);
    public Player player;
    public level levelTwo;
    private mainFrame mainFrame;
    private boolean isRunning;
    private String userName;
    private int seconds=0, minutes=0;
    private Timer timer;
    private ActionListener reloj;
    public boolean playerFinishedLevel;
    
    /** Panel en el cual mediante el uso de hilos como motor del juego se realizan
     las operaciones pertinentes para que mediante los inputs dados por el usuario 
     el juego realice una constante interaccion con el jugador y el entorno */
    public GamePanel2(int typeOfPlayer, mainFrame mainFrame, String userName){
        this.setLayout(null);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.isRunning = true;
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.player = new Player(0, (maxScreenRow-2)*tileSize, 2, tileSize, tileSize, this, keyH, typeOfPlayer);
        this.levelTwo = new levelTwo(this);
        this.mainFrame = mainFrame;
        this.userName = userName;
        this.playerFinishedLevel=false;
        Timer timer = new Timer(1000, new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            // Actualiza el contador de tiempo
            if(levelTwo.getSeconds()<59){
                levelTwo.setSeconds(levelTwo.getSeconds() + 1);   
            }else{
                levelTwo.setSeconds(0);
                levelTwo.setMinutes(levelTwo.getMinutes()+1);
            }
        }
        });
        timer.start();
        startGameThread();
    }
    
    /** Comenzar el hilo que da vida al juego */
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;//0.01666 seconds
        double nextDrawTime = System.nanoTime()+ drawInterval; //Draw time for the JPanel 
        while (gameThread != null && this.isRunning){    
            //1.Update: update information of the character position
            update();
            //2.Chequea si el personaje esta vivo            
            if(!player.draw){
                this.isRunning = false;
                this.playerDied();
                break;
            }
            //3.Draw: draw the screen with the updated information
            repaint();
            
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                if (remainingTime<0)
                    remainingTime=0;
                Thread.sleep((long)remainingTime);
                nextDrawTime += drawInterval;
                
            } catch (InterruptedException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /** Actualiza las posiciones e interacciones entre jugador y nivel */
    public void update(){
        player.update();
        levelTwo leveltwoaux = (levelTwo) levelTwo;
        levelTwo.update();
        if(this.playerFinishedLevel){
            this.playerFinishedLevelTwo();
        }
    }
    
    /** Dibuja al jugador y componentes del nivel constantemente */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        background.draw(g2);
        levelTwo.draw(g2);
        player.draw(g2);
        Font font = new Font("Arial", Font.BOLD, 12);
        g2.setFont(font);
        g2.setColor(Color.WHITE);
        g2.drawString("Usuario: "+userName, 0, 20);
        String timeString = String.format("Tiempo: %02d:%02d", levelTwo.getMinutes(), levelTwo.getSeconds());
        g2.drawString(timeString, 90, 20);
        g2.dispose();
    }
 
  /** Deten el hilo del juego */  
  public void stopRunig(){
      this.isRunning = false;
  }
  
  /** Metodo que realiza los llamados pertinentes luego de que el personaje muera */
  public void playerDied(){
      //Mostrar  menu de muerte
      mainFrame.showDeathPanel();
      //Detener el hilo del juego
      gameThread.stop();
      //Remover el GamePanel actual
      mainFrame.remove(this);
      //Liberar memoria
      System.gc();
  }
  
  /** Metodo que realiza los llamados pertinentes luego de que el jugador termine el nivel */
  public void playerFinishedLevelTwo(){
      //Mostrar  menu de finalizar el primer nivel
      mainFrame.showFinishLevelTwoPanel();
      //Detener el hilo del juego
      gameThread.stop();
      //Remover el GamePanel actual
      mainFrame.remove(this);
      //Liberar memoria
      System.gc();
  }
}
