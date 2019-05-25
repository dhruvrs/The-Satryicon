//Jesse Raphael 
//last edit May 11, 2019
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.IOException;
import java.io.File;
import java.awt.MouseInfo;
import java.util.HashMap;
import javax.imageio.*;
public class GamePanel extends JPanel
{
   private static final int WIDTH = 1300;
   private static final int HEIGHT = 800;
   private static final int TILESIZE = 125;
   
   private static final Color BACKGROUND = Color.BLACK;
   private BufferedImage myImage;
   private Graphics g;
   private Timer t; 
   
   private HashMap<String, String> saveData;
   private int gameState; 
 
   /*************
   gameState 1 instance data
   relevant to events occuring while moving on the grid
   *************/
   private int dir;//0=n,1=e,2=s,3=w
   //private int tileSize;
   private int xShift, yShift, moveState;
   private Area a;
   private KeyListener gridModeKey;
   private HeroGraphics heroGraphics;

   /*************
   Main menu instance data
   relevant to events occuring in the main menu
   *************/
   private JButton continueButton;//
   private JButton newGameButton;//
   private BufferedImage sky;
   private BufferedImage grass;
   private Cloud[] clouds;
   //private int bgCount;
   private int grassVelocity;
   private int grassPosition;
      
   public GamePanel()
   {
      loadMenu();
      myImage =  new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
      g = myImage.getGraphics();
      t = new Timer(50, new Listener());
      t.start();
      setFocusable(true);
   }
   
   public void paintComponent(Graphics g)
   {  //takes information to draw the backgroud
      g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
   }

   private void LoadGame(String saveFile)
   {
      saveData = Save.loadSave(saveFile);
      gameState = 1;
      //prepare for grid gamestate
      try {
    	  heroGraphics = new HeroGraphics();
      }catch(IOException e){}
      a = new Area("src/maps/"+saveData.get("currentArea"), TILESIZE, WIDTH/2, HEIGHT/2);
      a.setCoor(Integer.parseInt(saveData.get("currentX")), Integer.parseInt(saveData.get("currentY")));
      xShift = 0;
      yShift = 0;
      moveState = 0;
      
      remove(continueButton);
      remove(newGameButton);
      gridModeKey = new GridModeKey();
      addKeyListener(gridModeKey);
   }
   private void loadMenu()
   {
	  gameState = 0;
      setLayout(new GridBagLayout());
      GridBagConstraints c = new GridBagConstraints();
      newGameButton = new Button(new ImageIcon("src/graphics/button_1.png"),
				 				new ImageIcon("src/graphics/button_1.1.png"),
				 				new ImageIcon("src/graphics/button_1.2.png"),
				 				new ActionListener(){
    	  							public void actionPerformed(ActionEvent e){
    	  								LoadGame("src/saves/orginSave.txt");}
      			 				});      

      continueButton = new Button(new ImageIcon("src/graphics/button_2.png"),
         	 					  new ImageIcon("src/graphics/button_2.1.png"),
         	 					  new ImageIcon("src/graphics/button_2.2.png"),
         	 					  new ActionListener(){
            							public void actionPerformed(ActionEvent e){
            								LoadGame("src/saves/loadSave.txt");}
         						  }); 
      c.gridx = 0;
      c.gridy = 0;
      add(newGameButton, c);
      c.gridx = 0;
      c.gridy = 1;
      add(continueButton, c);

      grassPosition = 0;
      grassVelocity = 5;
      clouds = new Cloud[4]; 
      for(int i=0; i<4;i++) {
    	  clouds[i] = new Cloud();
    	  clouds[i].setX(WIDTH-200*i);
      }
      try{
         sky = ImageIO.read(new File("src/graphics/Sky.png"));
         grass = ImageIO.read(new File("src/graphics/VerticalGrass.png"));
      }catch(IOException e){}
   }
 /*  private void loadPause() {
	   saveButton = new Button();
	   inventory = new Button();
   }
   */
   private class Listener implements ActionListener
   {
      public void actionPerformed(ActionEvent e) 
      {
         if(gameState==0)
         {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            g.drawImage(sky, 0, 0, WIDTH, HEIGHT-200, null);
            for(Cloud cloud: clouds)
            	cloud.draw(g);
            g.drawImage(grass, grassPosition, HEIGHT-200, WIDTH, 200, null);
            g.drawImage(grass, grassPosition+WIDTH, HEIGHT-200, WIDTH, 200, null);
            grassPosition-=grassVelocity;
            if(grassPosition+WIDTH<=0)
            	grassPosition=0;
         }
         else if(gameState==1)
         {
            g.setColor(BACKGROUND);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            a.draw(g, xShift, yShift);
            
            if(xShift!=0||yShift!=0)
               moveState = moveState%2+1;
            else
               moveState = 0;
               
            if(xShift!=0) {
               xShift-=(xShift/Math.abs(xShift))*TILESIZE/5;
               if(xShift==0)
            	   gameState = a.interactStep();
            }
            else if(yShift!=0) {
               yShift-=(yShift/Math.abs(yShift))*TILESIZE/5;
               if(yShift==0)
            	   gameState = a.interactStep();
            }
            heroGraphics.draw(g, dir, moveState, WIDTH/2-TILESIZE/2, HEIGHT/2-TILESIZE*3/2, TILESIZE, TILESIZE*2);
            
         }
         else if(gameState==2)
         {//pause menu opened
         
         }
         /*
         else if(gameState==4)
         {//engaged in battle
         
         }*/
         repaint();
      }
   } 
   
   private class GridModeKey extends KeyAdapter
   {
      public void keyPressed(KeyEvent e)
      {       
         if(gameState==1 && xShift+yShift==0){
        	 if (e.getKeyCode() == KeyEvent.VK_W){
                 if(dir==0 && a.isValidStep(a.getXIdx(), a.getYIdx()-1, dir)){
                    a.decYIdx();
                    yShift = -TILESIZE;
                 }
                 else
                    dir=0;           
              }
        	 else if (e.getKeyCode() == KeyEvent.VK_D){
        		 if(dir==1 && a.isValidStep(a.getXIdx()+1, a.getYIdx(), dir)){
            	  	a.incXIdx();
            	  	xShift = TILESIZE;
        		 }
        		 else
        			 dir=1; 
        	 } 
        	 else if (e.getKeyCode() == KeyEvent.VK_S) {
               if(dir==2 && a.isValidStep(a.getXIdx(), a.getYIdx()+1, dir)){
                  a.incYIdx();
                  yShift = TILESIZE;
               }
               else
                  dir=2;
            }
            else if (e.getKeyCode() == KeyEvent.VK_A){
               if(dir==3 && a.isValidStep(a.getXIdx()-1, a.getYIdx(), dir)){
                  a.decXIdx();
                  xShift = -TILESIZE;
               }
               else
                  dir=3; 
            } 
            else if (e.getKeyCode() == KeyEvent.VK_I){
                gameState = 2;
            } 
         } 
         else if(gameState==2 && e.getKeyCode() == KeyEvent.VK_ESCAPE) {
        	 gameState = 1;
         }
        	 
      }
   }
}