import java.awt.image.BufferedImage;
import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.awt.image.*;
   
   import java.io.*;
public class TestTile
{
   public int x;
   public int y;
   public ImageIcon image;
   public TestTile( int x, int y, String file)
   {
    
      this.x = x;
      this.y = y;
      image = new ImageIcon(file);
   }

   public void draw(Graphics g, int width, int height)
   {
      g.drawImage(image.getImage(), x, y, width, height, null);
   }
}