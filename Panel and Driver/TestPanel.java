import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.awt.image.*;
   import java.util.ArrayList;
   import java.io.*;
   import javax.swing.JPopupMenu;
   import javax.swing.event.*;
    public class TestPanel extends JPanel
   {
      private static final int FRAME = 400;
      private Hero hero;
     
      private static final Color BACKGROUND = new Color(52, 52, 52);
      private BufferedImage myImage;
      private Graphics g;
      private JPopupMenu inventoryMenu;
      private TestTile[][] tiles;
      private Timer t; 
      public TestPanel() throws IOException
      {
         myImage =  new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB);
         g = myImage.getGraphics();
         g.setColor(BACKGROUND);
         g.fillRect(0, 0, FRAME,FRAME);
         hero = new Hero(1000, 1000, 1000, 10, 100, 360, 20, 50, "fb character.png");
         t = new Timer(10, new Listener()); 
         
         inventoryMenu = new JPopupMenu("inventory");
         tiles = new TestTile[5][5];
         for(int r = 0; r < tiles.length; r++)
         {
            for(int c = 0; c < tiles[0].length; c++)
            {
               if(r == 1)
               {
                  tiles[r][c] = new TestTile(r*80, c*80, "path tile.png");
               }
               else if(r == 0)
               {
                  tiles[r][c] = new TestTile(r*80, c*80, "Grass background.png");
               }
               else if(r == 2 || c==0)
               {
                  tiles[r][c] = new TestTile(r*80, c*80, "Brown dirt background.png");
               }
               else
               {
                  tiles[r][c] = new TestTile(r*80, c*80, "Final water texture.png");
               }
               
            }
         }
         
         addKeyListener(new Key());
         t.start();
         setFocusable(true);
      }
      
      public void paintComponent(Graphics g)
      {
         g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
      }
      
      private class Listener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            for(int r = 0; r < tiles.length; r++)
            {
               
               for(int c = 0; c < tiles[0].length; c++)
               {
                  tiles[r][c].draw(g, 80, 80);
               }
            }
            hero.draw(g);
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
      
      int count = 0;
     private class Key extends KeyAdapter
     {
         
         public void keyPressed (KeyEvent e )
         {
          
            if(e.getKeyCode() == KeyEvent.VK_P)
            {
               if(count <25)
               {
                  if(Math.random() < 0.5)
                     hero.addToInv(new Armour(20, true, true, "Good Armour", 10, "TestArmour.png"));
                  else
                     hero.addToInv(new Weapon(50, true, true, "Iron Sword", 10, 20, 20, 40, 40, "TestSword.png"));
                  JMenuItem item = new JMenuItem(hero.getInventory()[hero.getInvIndex()-1].getName());
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
            else if(e.getKeyCode() == KeyEvent.VK_UP)
            {
               hero.setY(hero.getY() - 1);
            }
            else if(e.getKeyCode() == KeyEvent.VK_DOWN)
            {
               hero.setY(hero.getY() +1);
            }
            else if(e.getKeyCode() == KeyEvent.VK_LEFT)
            {
               hero.setX(hero.getX() -1);
            }
            else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            {
               hero.setX(hero.getX() + 1);
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