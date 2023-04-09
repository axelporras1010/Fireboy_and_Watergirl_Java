
package soundManager;

import Levels.level;
import entity.Player;
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class soundManager {
    private boolean gem = false;
    public Clip levelClip, menuClip, platformClip;
    private volatile boolean menuMusicPlaying = false;
    private volatile boolean levelMusicPlaying = false;
    private volatile boolean platformMusicPlaying = false;
    
   //* Reproduce la musica de los niveles */
   public void playLevelMusic() {
    Thread soundThread = new Thread() {
        public void run() {
            try {
                URL url = soundManager.class.getResource("sounds/Level Music.wav");
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                levelMusicPlaying = true;
                while (levelMusicPlaying) {
                    Thread.sleep(100); // espera un poco antes de verificar la variable de nuevo
                }
                clip.stop();
                clip.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    soundThread.start();
}
   //* Deten la musica de los niveles */
   public void stopLevelMusic() {
    levelMusicPlaying = false;
}
   
   //* Reproduce la musica de los menu */
   public void playMenuMusic() {
    Thread soundThread = new Thread() {
        public void run() {
            try {
                URL url = soundManager.class.getResource("sounds/Menu Music.wav");
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                menuMusicPlaying = true;
                while (menuMusicPlaying) {
                    Thread.sleep(100); // espera un poco antes de verificar la variable de nuevo
                }
                clip.stop();
                clip.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    soundThread.start();
}
   
   //* Deten la musica de los menu */
   public void stopMenuMusic() {
    menuMusicPlaying = false;
}
   
   //* Reproduce la musica de las plataformas */
   public void playPlatformMusic() {
    Thread soundThread = new Thread() {
        public void run() {
            try {
                URL url = soundManager.class.getResource("sounds/Platform.wav");
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
                platformMusicPlaying = true;
                while (platformMusicPlaying) {
                    Thread.sleep(100); // espera un poco antes de verificar la variable de nuevo
                }
                clip.stop();
                clip.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    soundThread.start();
}
   
   //* Deten la musica de las plataformas */
   public void stopPlatformMusic() {
    platformMusicPlaying = false;
}
    
   //* Reproduce la musica de las gemas */
    public void playGemMusic(){
        Thread soundThread = new Thread(){
            public void run(){
                try{
                    URL url = soundManager.class.getResource("sounds/Gem.wav");
                    AudioClip clip = Applet.newAudioClip(url);
                    clip.play();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        soundThread.start();
    }
    
    //* Reproduce la musica de los ventiladores */
    public void playFanMusic(){
        Thread soundThread = new Thread(){
            public void run(){
                try{
                    URL url = soundManager.class.getResource("sounds/Wind.wav");
                    AudioClip clip = Applet.newAudioClip(url);
                    clip.play();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        soundThread.start();
    }
    
    //* Reproduce la musica de la muerte */
    public void playDeathMusic(){
        Thread soundThread = new Thread(){
            public void run(){
                try{
                    URL url = soundManager.class.getResource("sounds/Death.wav");
                    AudioClip clip = Applet.newAudioClip(url);
                    clip.play();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        soundThread.start();
    }
    
    //* Reproduce la musica de los saltos */
    public void playJumpFbMusic(){
        Thread soundThread = new Thread(){
            public void run(){
                try{
                    URL url = soundManager.class.getResource("sounds/Jump fb.wav");
                    AudioClip clip = Applet.newAudioClip(url);
                    clip.play();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        soundThread.start();
    }
    
    //* Reproduce la musica de los saltos */
    public void playJumpWgMusic(){
        Thread soundThread = new Thread(){
            public void run(){
                try{
                    URL url = soundManager.class.getResource("sounds/Jump wg.wav");
                    AudioClip clip = Applet.newAudioClip(url);
                    clip.play();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        soundThread.start();
    }
    
    //* Reproduce la musica de las puertas */
    public void playDoorMusic(){
        Thread soundThread = new Thread(){
            public void run(){
                try{
                    URL url = soundManager.class.getResource("sounds/Door.wav");
                    AudioClip clip = Applet.newAudioClip(url);
                    clip.play();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        soundThread.start();
    }
}
