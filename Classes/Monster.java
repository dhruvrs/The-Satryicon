import java.awt.image.BufferedImage;
import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.awt.image.*;
   
   import java.io.*;
public class Monster extends Creature
{
   private boolean isBoss1;
   private boolean isBoss2;
   private boolean isBoss3;
   private boolean canLoot;
   
   private Items deadItems;
   private Coin deadCoins;
   private int width;
   private int height;
   private ImageIcon image;
   public Monster(double d, double h, double s, double p, int l, boolean ib1, boolean ib2, boolean ib3, int w, int hi, String fileImage)
   {
      super(d, h, s, p, l);
      isBoss1 = ib1;
      isBoss2 = ib2;
      isBoss3 = ib3;
      canLoot = false;
      setDeadCoin();
      if(isBoss1)
         setBossItem1();
      else if(isBoss2)
         setBossItem2();
      else if(isBoss3)
         setBossItem3();
    
      width = w;
      height = hi;
      image = new ImageIcon(fileImage);
   }
   public boolean getIsBoss()
   {
      return isBoss1 || isBoss2 || isBoss3;
   }
   public boolean getIsBoss1()
   {
      return isBoss1;
   }
   public boolean getIsBoss2()
   {
      return isBoss2;
   }
   public boolean getIsBoss3()
   {
      return isBoss3;
   }
   
   
   public void setBossItem1()
   {
      //figure out unsellable item relevant to story
   }
   public void setBossItem2()
   {
      //figure out unsellable item relevant to story
   }
   public void setBossItem3()
   {
      //figure out unsellbale item relevant to story
   }
 
   public void setDeadCoin()
   {
      if(getLevel() < 5)
         deadCoins = new Coin((int)(Math.random()*10 + getLevel()));
      else if(getLevel() < 7)
         deadCoins = new Coin((int)(Math.random()*15 + getLevel()));
      else
         deadCoins = new Coin((int)(Math.random()*30 + getLevel()));
   }
   public boolean getCanLoot()
   {
      return canLoot;
   }
   public void setCanLoot(boolean cl)
   {
      canLoot = cl;
   }
   public int getWidth()
   {
      return width;
   }
   public void setWidth(int w)
   {
      width = w;
   }
   public int getHeight()
   {
      return height;
   }
   public void setHeight(int hi)
   {
      height = hi;
   }
   public void skill()
   {
      //figure out this
   }
   public void loot(Graphics myBuffer, int x, int y)
   {
      deadItems.draw(myBuffer, x, y, 20, 20);
   }
   public void draw(Graphics myBuffer, int x, int y)
   {
      myBuffer.drawImage(image.getImage(), x, y, width, height, null);
   }
}