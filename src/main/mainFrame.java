
package main;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import main.mainFrame.playButtonListener;
import soundManager.soundManager;


public class mainFrame extends JFrame{
    public int typeOfPlayer;
    public String userName;
    
    private soundManager soundManager = new soundManager();
    public mainMenu mainMenu;
    public creditsPanel creditsPanel = new creditsPanel();
    public instructionsPanel instructionsPanel = new instructionsPanel();
    public deathPanel deathPanel = new deathPanel();
    public GamePanel gamePanel;
    public GamePanel2 gamePanel2;
    public finishLevelOnePanel finishLevelOnePanel = new finishLevelOnePanel(0, 0, 0, 0, 0, 0);;
    public finishLevelTwoPanel finishLevelTwoPanel = new finishLevelTwoPanel(0, 0, 0, 0, 0, 0);;
    
    /** Clase mediante la cual todos los paneles del juego se almacenan, y mediante distintas 
interacciones se muestran u ocultan dependiendo del usuario */
    public mainFrame(){
        super("FireBoy & Watergirl");
        
        mainMenu = new mainMenu(this);
        soundManager.playMenuMusic();
        
        // Agrega los paneles al JFrame con el CardLayout
        CardLayout cardLayout = new CardLayout();
        getContentPane().setLayout(cardLayout);
        getContentPane().add(mainMenu, "mainMenu");
        getContentPane().add(creditsPanel, "creditsPanel");
        getContentPane().add(instructionsPanel, "instructionsPanel");
        getContentPane().add(deathPanel, "deathPanel");
        getContentPane().add(finishLevelOnePanel, "finishLevelOnePanel");
        getContentPane().add(finishLevelTwoPanel, "finishLevelTwoPanel");
        
        // Crea y agrega los componentes al panel principal
        ActionListener playButtonListener = new playButtonListener();
        ActionListener playButtonListener2 = new playButtonListener2();
        ActionListener replayButtonListener = new replayButtonListener();
        ActionListener creditsButtonListener = new creditsButtonListener();
        ActionListener backButtonListener = new backButtonListener();
        ActionListener instruButtonListener = new instruButtonListener();
        ActionListener exitButtonListener = new exitButtonListener();
        
        JButton startButton = new JButton("JUGAR!");
        startButton.addActionListener(playButtonListener);
        startButton.setBounds(864/2-80, 550, 130, 50);
        mainMenu.add(startButton);
                
        JButton exitButton = new JButton("SALIR");
        exitButton.addActionListener(exitButtonListener);
        exitButton.setBounds(864/2-80, 610, 130, 50);
        mainMenu.add(exitButton);
        
        JButton instructionButton = new JButton("INSTRUCCIONES");
        instructionButton.addActionListener(instruButtonListener);
        instructionButton.setBounds(864/2-230, 550, 130, 50);
        mainMenu.add(instructionButton);
        
        JButton creditsButton = new JButton("CREDITOS");
        creditsButton.addActionListener(creditsButtonListener);
        creditsButton.setBounds(864/2+75, 550, 130, 50);
        mainMenu.add(creditsButton);
        
        JButton backButton = new JButton("VOLVER AL MENU");
        backButton.setBounds(330, 600, 150, 30);
        backButton.addActionListener(backButtonListener);
        creditsPanel.add(backButton);
        
        JButton backButton2 = new JButton("VOLVER AL MENU");
        backButton2.setBounds(360, 550, 150, 30);
        backButton2.addActionListener(backButtonListener);
        instructionsPanel.add(backButton2);
        
        JButton backButton3 = new JButton("VOLVER AL MENU");
        backButton3.setBounds(250, 550, 150, 50);
        backButton3.addActionListener(backButtonListener);
        deathPanel.add(backButton3);
        
        JButton startButton3 = new JButton("REINTENTAR!");
        startButton3.addActionListener(replayButtonListener);
        startButton3.setBounds(500, 550, 150, 50);
        deathPanel.add(startButton3);
        
        JButton startButton2 = new JButton("SIGUIENTE NIVEL!");
        startButton2.addActionListener(playButtonListener2);
        startButton2.setBounds(600, 550, 150, 50);
        finishLevelOnePanel.add(startButton2);
        
        JButton startButton4 = new JButton("REINTENTAR!");
        startButton4.addActionListener(replayButtonListener);
        startButton4.setBounds(100, 550, 150, 50);
        finishLevelOnePanel.add(startButton4);
        
        JButton backButton4 = new JButton("VOLVER AL MENU");
        backButton4.setBounds(350, 550, 150, 50);
        backButton4.addActionListener(backButtonListener);
        finishLevelOnePanel.add(backButton4);
        
        JButton backButton5 = new JButton("VOLVER AL MENU");
        backButton5.setBounds(350, 550, 150, 50);
        backButton5.addActionListener(backButtonListener);
        finishLevelTwoPanel.add(backButton5);
        
        JButton startButton5 = new JButton("REPETIR NIVEL 2!");
        startButton5.addActionListener(playButtonListener2);
        startButton5.setBounds(600, 550, 150, 50);
        finishLevelTwoPanel.add(startButton5);
        
        JButton startButton6 = new JButton("VOLVER A EMPEZAR!");
        startButton6.addActionListener(replayButtonListener);
        startButton6.setBounds(100, 550, 170, 50);
        finishLevelTwoPanel.add(startButton6);
        
        // Configura el JFrame
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        
        }
    
    /** Deten el audio del nivel, deten los hilos que esten dandole vida a los niveles 
     * y luego muestra el JPanel con las opciones a tomar luego de la muerte del personaje */
    public void showDeathPanel(){
        //deten la musica de los niveles
        soundManager.stopLevelMusic();
        
        // Detener el hilo del GamePanel y eliminarlo del contenedor
        if (gamePanel != null) {
            gamePanel.stopRunig();
            getContentPane().remove(gamePanel);
        }
        
        // Detener el hilo del GamePanel2 y eliminarlo del contenedor
        if (gamePanel2 != null) {
            gamePanel2.stopRunig();
            getContentPane().remove(gamePanel2);
        }
        
        // Oculta el panel actual y muestra el panel de muerte
        CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
        cardLayout.show(getContentPane(), "deathPanel");
        
        // Establece el foco en el JPanel de los creditos
        deathPanel  = (deathPanel) getContentPane().getComponent(3);
        deathPanel.requestFocusInWindow();
        
         
        //Vuelve a reproducir la musica del menu
        soundManager.playMenuMusic();
    }
    
    /** Deten la musica, almacena los valores obtenidos del nivel para crear un JPanel 
     donde se muestren los valores obtenidos, se muestren los botones para interactuar
     y reproduce la musica de menu */
    public void showFinishLevelOnePanel(){
        
        //detener musica
        soundManager.stopLevelMusic();
        soundManager.stopMenuMusic();
        
        
        //Obten datos del primer nivel
        int min = gamePanel.levelOne.minutes;
        int sec = gamePanel.levelOne.seconds;
        int foundedFireGems, foundedWaterGems;
        if(gamePanel.player.typeOfPlayer==0){
            foundedFireGems = gamePanel.levelOne.foundedGems;
        }else{
            foundedFireGems = 0;
        }
        if(gamePanel.player.typeOfPlayer==1){
            foundedWaterGems = gamePanel.levelOne.foundedGems;
        }else{
            foundedWaterGems = 0;
        }
        int fireGems = gamePanel.levelOne.fireGems;
        int waterGems = gamePanel.levelOne.waterGems;
        
        finishLevelOnePanel.deleteElements();
        finishLevelOnePanel.setSeconds(sec);
        finishLevelOnePanel.setMinutes(min);
        finishLevelOnePanel.setFireGems(fireGems);
        finishLevelOnePanel.setWaterGems(waterGems);
        finishLevelOnePanel.setFoundedFireGems(foundedFireGems);
        finishLevelOnePanel.setFoundedWaterGems(foundedWaterGems);
        finishLevelOnePanel.mostrar();
        
        // Detener el hilo del GamePanel y eliminarlo del contenedor
        if (gamePanel != null) {
            gamePanel.stopRunig();
            getContentPane().remove(gamePanel);
        }        
        
        
        // Oculta el panel actual y muestra el panel de nivel terminado
        CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
        cardLayout.show(getContentPane(), "finishLevelOnePanel");
        
        // Establece el foco en el JPanel del nivel terminado
        finishLevelOnePanel  = (finishLevelOnePanel) getContentPane().getComponent(4);
        finishLevelOnePanel.requestFocusInWindow();
        
        //deten la musica de los niveles
        soundManager.stopLevelMusic();
        
        //Vuelve a reproducir la musica del menu
        soundManager.playMenuMusic();
    }
    
    /** Deten la musica, almacena los valores obtenidos del nivel para crear un JPanel 
     donde se muestren los valores obtenidos, se muestren los botones para interactuar
     y reproduce la musica de menu */
    public void showFinishLevelTwoPanel(){
        
        //Obten datos del primer nivel
        int min = gamePanel2.levelTwo.minutes;
        int sec = gamePanel2.levelTwo.seconds;
        int foundedFireGems, foundedWaterGems;
        if(gamePanel2.player.typeOfPlayer==0){
            foundedFireGems = gamePanel2.levelTwo.foundedGems;
        }else{
            foundedFireGems = 0;
        }
        if(gamePanel2.player.typeOfPlayer==1){
            foundedWaterGems = gamePanel2.levelTwo.foundedGems;
        }else{
            foundedWaterGems = 0;
        }
        int fireGems = gamePanel2.levelTwo.fireGems;
        int waterGems = gamePanel2.levelTwo.waterGems;
        
        finishLevelTwoPanel.deleteElements();
        finishLevelTwoPanel.setSeconds(sec);
        finishLevelTwoPanel.setMinutes(min);
        finishLevelTwoPanel.setFireGems(fireGems);
        finishLevelTwoPanel.setWaterGems(waterGems);
        finishLevelTwoPanel.setFoundedFireGems(foundedFireGems);
        finishLevelTwoPanel.setFoundedWaterGems(foundedWaterGems);
        finishLevelTwoPanel.mostrar();
        
        // Detener el hilo del GamePanel y eliminarlo del contenedor
        if (gamePanel2 != null) {
            gamePanel2.stopRunig();
            getContentPane().remove(gamePanel2);
        }        
        
        
        // Oculta el panel actual y muestra el panel de nivel terminado
        CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
        cardLayout.show(getContentPane(), "finishLevelTwoPanel");
        
        // Establece el foco en el JPanel del nivel terminado
        finishLevelTwoPanel  = (finishLevelTwoPanel) getContentPane().getComponent(5);
        finishLevelTwoPanel.requestFocusInWindow();
        
        //deten la musica de los niveles
        soundManager.stopLevelMusic();
        
        //Vuelve a reproducir la musica del menu
        soundManager.playMenuMusic();
    }
    
    /** Boton mediante el cual se da inicio al juego comenzando con el nivel 1 */
    public class playButtonListener implements ActionListener{
    public void actionPerformed(ActionEvent e) {
        //Obten el typeOfPlayer
        typeOfPlayer = mainMenu.getTypeOfPlayer();
        
        //Elimina el nombre del jugador previo
        userName = null;
        
        //Obten el nombre del jugador
        while(userName == null || userName.trim().isEmpty() || userName.length()>8){
            userName = JOptionPane.showInputDialog("Por favor ingresa tu nombre (maximo 8 caracteres): ");
            if(userName == null){ // si el usuario cancela el diálogo
                return;
            }else if(userName.trim().isEmpty()){ // si el usuario ingresa un nombre vacío
                JOptionPane.showMessageDialog(null, "Por favor ingresa un nombre valido");
            }else if(userName.length()>8){ //si el usuario ingresa un nombre de mas de 8 caracteres
                JOptionPane.showMessageDialog(null, "El nombre debe tener maximo 8 caracteres");
            }
            
        }
        
        //Silencia la musica del Menu
        soundManager.stopMenuMusic();
        
        //Inicializa el gamePanel
        gamePanel = new GamePanel(typeOfPlayer, mainMenu.getMainFrame(), userName);
        getContentPane().add(gamePanel, "gamePanel");
        
        // Oculta el panel actual y muestra el panel del juego
        CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
        cardLayout.show(getContentPane(), "gamePanel");
        
        // Establece el foco en el JPanel del juego
        GamePanel gamePanel = (GamePanel) getContentPane().getComponent(6);
        gamePanel.requestFocusInWindow();
        gamePanel.startGameThread();
        soundManager.playLevelMusic();
    }
    }
    
    /** Boton mediante el cual se da inicio al segundo nivel */
    public class playButtonListener2 implements ActionListener{
    public void actionPerformed(ActionEvent e) {
        //Obten el typeOfPlayer
        typeOfPlayer = mainMenu.getTypeOfPlayer();  
        
        //Silencia la musica del Menu
        soundManager.stopMenuMusic();
        
        //Inicializa el gamePanel2
        gamePanel2 = new GamePanel2(typeOfPlayer, mainMenu.getMainFrame(), userName);
        getContentPane().add(gamePanel2, "gamePanel2");
        
        // Oculta el panel actual y muestra el panel del juego
        CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
        cardLayout.show(getContentPane(), "gamePanel2");
        
        
        // Establece el foco en el JPanel del juego
        GamePanel2 gamePanel2 = (GamePanel2) getContentPane().getComponent(6);
        gamePanel2.requestFocusInWindow();
        gamePanel2.startGameThread();
        soundManager.playLevelMusic();
        
    }
    }
    
    /** Boton mediante el cual se vuelve al inicio del juego */
    public class replayButtonListener implements ActionListener{
    public void actionPerformed(ActionEvent e) {
        //Obten el typeOfPlayer
        typeOfPlayer = mainMenu.getTypeOfPlayer();
        
        
        //Inicializa el gamePanel
        gamePanel = new GamePanel(typeOfPlayer, mainMenu.getMainFrame(), userName);
        getContentPane().add(gamePanel, "gamePanel");
        
        // Oculta el panel actual y muestra el panel del juego
        CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
        cardLayout.show(getContentPane(), "gamePanel");
        
        //Silencia la musica del Menu
        soundManager.stopMenuMusic();
        
        // Establece el foco en el JPanel del juego
        GamePanel gamePanel = (GamePanel) getContentPane().getComponent(6);
        gamePanel.requestFocusInWindow();
        gamePanel.startGameThread();
        soundManager.playLevelMusic();
    }
    }
    
    /** Boton mediante el cual se muestra el panel de creditos del juego */
    public class creditsButtonListener implements ActionListener{
    public void actionPerformed(ActionEvent e) {
        
        // Oculta el panel actual y muestra el panel del juego
        CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
        cardLayout.show(getContentPane(), "creditsPanel");
        
        // Establece el foco en el JPanel de los creditos
        creditsPanel  = (creditsPanel) getContentPane().getComponent(1);
        creditsPanel.requestFocusInWindow();
    }
    }
    
    /** Boton mediante el cual se muestra el panel de instrucciones del juego */
    public class instruButtonListener implements ActionListener{
    public void actionPerformed(ActionEvent e) {
        
        // Oculta el panel actual y muestra el panel del juego
        CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
        cardLayout.show(getContentPane(), "instructionsPanel");
        
        // Establece el foco en el JPanel de las instrucciones
        instructionsPanel  = (instructionsPanel) getContentPane().getComponent(2);
        instructionsPanel.requestFocusInWindow();
    }
    }
    
    /** Boton mediante el cual se muestra el panel de Menu Principal del juego */
    public class backButtonListener implements ActionListener{
    public void actionPerformed(ActionEvent e) {
        
        // Oculta el panel actual y muestra el panel del juego
        CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
        cardLayout.show(getContentPane(), "mainMenu");
        
        // Establece el foco en el JPanel del mainMenu
        mainMenu  = (mainMenu) getContentPane().getComponent(0);
        mainMenu.requestFocusInWindow();
    }
    }
    
    /** Boton mediante el cual se sale del juego */
    public class exitButtonListener implements ActionListener{
    public void actionPerformed(ActionEvent e) {
        
        //Silencia la musica del Menu
        soundManager.stopMenuMusic();
        
        dispose();     
    }
    }
    
}
