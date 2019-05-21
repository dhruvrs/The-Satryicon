import java.awt.image.BufferedImage;
import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.awt.image.*;
   
   import java.io.*;
public class Coin
{
   private int numCoin;
   private ImageIcon image;
   public Coin(int nc)
   { 
      numCoin = nc;
      image = new ImageIcon("coin");
   }
   public int getNumCoin()
   {
      return numCoin;
   }
   public void draw(Graphics myBuffer, int x, int y, int width, int height)
   {
      if(numCoin < 2)
      {
         myBuffer.drawImage(image.getImage(), x, y, width, height, null);
      }
      else
      {
         myBuffer.drawImage(image.getImage(), x, y, width, height, null);
         myBuffer.setColor(Color.BLACK);
         myBuffer.setFont(new Font("Monospaced", Font.BOLD, 10));
         myBuffer.drawString("X " + numCoin, x, y + height);
      }
   }
}