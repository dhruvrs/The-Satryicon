   //Name:  Samantha Frietchen  Date: 4/26/18  Lab: Lab17KeyInput
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.ArrayList;
import java.io.*;
import java.io.IOException;
import java.io.File;
import java.awt.MouseInfo;
import java.util.HashMap;
import javax.swing.JPopupMenu;
import javax.swing.event.*;
public class GamePanel extends JPanel
{
   private static final int WIDTH = 1300;
   private static final int HEIGHT = 800;
   private static final int TILESIZE = 125;
  
   private static final Color BACKGROUND = Color.BLACK;
   private BufferedImage myImage;
   private Graphics g;
     // private ArrayList<Monster> monsters;
   private Monster monster1;
   private Monster monster2;
   private Monster monster3;
   private Hero hero;
   private JPopupMenu inventoryMenu;
   private Timer t; 
  
      
      
      
   private HashMap<String, Integer> saveData;
   private int gameState = 0; 
 
   /*************
   gameState 1 instance data
   relevant to events occuring while moving on the grid
   *************/
   private int dir;//0=n,1=e,2=s,3=w
   private int tileSize;
   private int xShift = 0, yShift = 0;
   private Area a;
   private KeyListener gridModeKey;
   /*************
   Main menu instance data
   relevant to events occuring in the main menu
   *************/
   private MouseListener mml;
   private int lB_1 = WIDTH/2-300;
   private int rB_1 = WIDTH/2+300;
   private int tB_1 = HEIGHT/2-100;
   private int bB_1 = HEIGHT/2+100;
     
   public GamePanel() throws IOException
   {
         //load Menu
      myImage =  new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
      g = myImage.getGraphics();
         
       
      hero = new Hero(1000, 1000, 1000, 10, 30, 30, 12, 12, "StickFigure.png");
      monster1 = new Monster(50, 50, 50, 10, 1, false, false, false, 30, 30, "slime with thiccer border.png");
      monster2 = new Monster(50, 50, 50, 10, 1, false, false, false, 30, 30, "slimey boy 2.png");
      monster3 = new Monster(50, 50, 50, 10, 1, false, false, false, 30, 30, "happy slime alt.png");
         
         
      t = new Timer(50, new Listener());
      t.start();
      gridModeKey = new GridModeKey();
      mml = new MainMenuListener();
      addMouseListener(mml);
      inventoryMenu = new JPopupMenu("inventory");
      
      addKeyListener(new Key());
      setFocusable(true);
   }
   public void paintComponent(Graphics g)
   {//takes information to draw the backgroud
      g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
   }
   private class Listener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {//sets background color and the fills the background rectangle
           
         if(gameState==0)
         {
            g.setColor(new Color(200,200,200));
            g.fillRect(0, 0, WIDTH, HEIGHT);
            g.setColor(new Color(100,0,0));
            g.fillRect(lB_1, tB_1, rB_1-lB_1, bB_1-tB_1);
           
         }
         else if(gameState==1)
         {
            g.setColor(BACKGROUND);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            a.draw(g, xShift, yShift);
            if(xShift!=0)
            {
               xShift-=(xShift/Math.abs(xShift))*TILESIZE/5;
               /*code to implement character running animation*/
            }
            else if(yShift!=0)
            {
               yShift-=(yShift/Math.abs(yShift))*TILESIZE/5;
            }
         }
       /*  else if(gameState==2)
         {//pause menu opened
         
         }
         else if(gameState==4)
         {//engaged in battle
         
         }*/
           
         monster1.draw(g, 200, 100);
         monster2.draw(g, 300, 50);
         monster3.draw(g, 222, 222);
         hero.draw(g);
         System.out.println(hero.getArmour());
         System.out.println(hero.getWeapon());
         hero.drawStaminaBar(g, WIDTH - 50, HEIGHT - 110);       
         hero.drawHealthBar(g, WIDTH/2 - 52, HEIGHT - 20);
         if(hero.getArmour() != null)
         {
            hero.getArmour().draw(g, 200, 200, 60, 60);
         }
         if(hero.getWeapon() != null)
         {
            hero.getWeapon().draw(g, 100, 100, 60, 60);
         }
         repaint();
      
      }
   }   
      

      
   /*   private void die(Monster monster, Hero hero)
      {
         if(monster.getHealth >= 0)
         {
            
            hero.addEX(Math.random()*8 + monster.getLevel()*10);
            monster.setCanLoot(true);
            //picture for dead monster
            //display exit option (return back to normal game play)
            
            
         }
      }*/
   private void HeroUseWeapon(Hero hero, Weapon weapon, Monster monster)
   {
      if(hero.getStamina() >= weapon.getStaminaNeeded())
      {
         hero.setStamina(hero.getStamina() -weapon.getStaminaNeeded());
         if(monster.getHealth() > weapon.getDamage())
            monster.setHealth(monster.getHealth() - weapon.getDamage());
         else
         {
            monster.setHealth(0);
         }
      }  
   }  
   private void HeroPunch(Hero hero, Monster monster)
   {
      double staminaNeeded = hero.getPower()*1.5;
      if(hero.getStamina() >= staminaNeeded)
      {
         hero.setStamina(hero.getStamina() - staminaNeeded);
         if(monster.getHealth() > hero.getPower())
            monster.setHealth(monster.getHealth() - hero.getPower());
         else
         {
            monster.setHealth(0);
         }
      } 
   }
   private class MainMenuListener extends MouseAdapter
   {
      public void mouseClicked(MouseEvent e)
      {
         Point p = MouseInfo.getPointerInfo().getLocation();
         if(p.getX()>lB_1 && p.getX()<rB_1 && p.getY()>tB_1 && p.getY()<bB_1)
         {
            try{
               saveData = Save.loadSave("orginSave.txt");
            }
            catch(IOException ioX){}
            gameState = 1;
            a = new Area(new File("area"+saveData.get("currentArea")+".txt"),
                         TILESIZE, WIDTH/2, HEIGHT/2);
            a.setCoor(saveData.get("currentX"), saveData.get("currentY"));
            removeMouseListener(mml);
            addKeyListener(gridModeKey);
         }   
      }
   }
     
   private class GridModeKey extends KeyAdapter
   {
      public void keyPressed(KeyEvent e )
      {       
         if(xShift+yShift==0){
            if (e.getKeyCode() == KeyEvent.VK_DOWN && a.isValidStep(a.getXIdx(), a.getYIdx()+1, dir)) {
               if(dir==2)
               {
                  a.incYIdx();
                  yShift = TILESIZE;
               }
               else
                  dir=2;
            }
            else if (e.getKeyCode() == KeyEvent.VK_UP && a.isValidStep(a.getXIdx(), a.getYIdx()-1, dir)){
               if(dir==0)
               {
                  a.decYIdx();
                  yShift = -TILESIZE;
               }
               else
                  dir=0;           
            }
            else if (e.getKeyCode() == KeyEvent.VK_LEFT && a.isValidStep(a.getXIdx()-1, a.getYIdx(), dir)){
               if(dir==3)
               {
                  a.decXIdx();
                  xShift = -TILESIZE;
               }
               else
                  dir=3; 
            } 
            else if (e.getKeyCode() == KeyEvent.VK_RIGHT && a.isValidStep(a.getXIdx()+1, a.getYIdx(), dir)){
               if(dir==1)
               {
                  a.incXIdx();
                  xShift = TILESIZE;
               }
               else
                  dir=1; 
            } 
         }     
         
      }
   }
    
     private class Key extends KeyAdapter
     {
         private int count;
         public Key()
         {
            count = 0;
         }
         public void keyPressed (KeyEvent e )
         {
          
            if(e.getKeyCode() == KeyEvent.VK_P)
            {
               if(count <25)
               {
                  if(Math.random() < 0.5)
                     hero.addToInv(new Armour(20, true, true, "Basic Armour", 10, "TestArmour.jpg"));
                  else
                     hero.addToInv(new Weapon(10, true, true, "Sword", 10, 10, 10, 10, 10, "TestSword.png"));
                  JMenuItem item = new JMenuItem(hero.getInventory()[hero.getInvIndex()-1].getName(), hero.getInventory()[hero.getInvIndex()-1].getImage());
                  inventoryMenu.add(item);
                  item.addActionListener(new MenuListener(hero.getInventory()[hero.getInvIndex()-1], hero, g));
                 
                  count++;
                  System.out.println(count);
                  System.out.println(hero.getInvIndex());
               }
            }
            else if(e.getKeyCode() == KeyEvent.VK_I)
            {
               inventoryMenu.show(e.getComponent(), 50, 50);
            }
         }
     }  
     
     private class MenuListener implements ActionListener
     {
         private Items i;
         private Hero hero;
         private Graphics g;
         public MenuListener(Items i, Hero hero, Graphics g)
         {
            this.i = i;
            this.hero = hero;
            this.g = g;
         }
         public void actionPerformed(ActionEvent e)
         {
            System.out.println(e.getActionCommand());
            if(i.identify() == 'w')
            {   
               System.out.println("weapon");
               System.out.println(((Weapon)i).getStaminaNeeded());
               hero.equipWeapon((Weapon)i);
               
               repaint();
            }
            else if(i.identify() == 'a')
            {
               System.out.println("armour");
               System.out.println(((Armour)i).getDefenseHelp());
               hero.equipArmour((Armour)i);
               
               repaint();
            }
            else
            {
               System.out.println("food");
               System.out.println(i.getPrice());
            }
            
         }
         
     }   

}
