
import java.awt.image.BufferedImage;
import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.awt.image.*;
   
   import java.io.*;
public abstract class Items
{
   private double price;
   private boolean isPresent;
   private boolean isSellable;
   private String name;
   private ImageIcon image;
   public Items(double p, boolean ip, boolean is, String n, String file)
   {
      price = p;
      isPresent = ip;
      isSellable = is;
      name = n;
      image = new ImageIcon(file);
   }
   public Items(double p, boolean ip, boolean is, String file)
   {
      price = p;
      isPresent = is;
      isSellable = is;
      name = null;
      image = new ImageIcon(file);
   }
   public double getPrice()
   {
      return price;
   }
   public boolean getIsPresent()
   {
      return isPresent;
   }
   public boolean getIsSellable()
   {
      return isSellable;
   }
   public String getName()
   {
      return name;
   }
   public void setPrice(double p)
   {
      p = price;
   }
   public void setIsPresent(boolean ip)
   {
      isPresent = ip;
   }
   public void setIsSellable(boolean is)
   {
      isSellable = is;
   }
   public void setName(String n)
   {
      name = n;
   }
   public ImageIcon getImage()
   {
      return image;
   }
   public void draw(Graphics g, int x, int y, int width, int height)
   {
      g.drawImage(image.getImage(), x, y, width, height, null);
   }
   public abstract void benefit();
   public abstract char identify();
}