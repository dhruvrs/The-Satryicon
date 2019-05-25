import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
   
import java.io.*;
import java.io.IOException;
public class HeroGraphics 
{
   private BufferedImage[][] images;
   public HeroGraphics() throws IOException
   {
      BufferedImage fn1 = ImageIO.read(new File("src/graphics/facingNorth1.png"));
      BufferedImage fn2 = ImageIO.read(new File("src/graphics/box.png"));//"facingNorth2.png"));
      BufferedImage fn3 = ImgEdit.reverse(fn2);
      BufferedImage fe1 = ImageIO.read(new File("src/graphics/box.png"));//"facingEast1.png"));
      BufferedImage fe2 = ImageIO.read(new File("src/graphics/box.png"));//"facingEast2.png"));
      BufferedImage fe3 = ImageIO.read(new File("src/graphics/box.png"));//"facingEast3.png"));
      BufferedImage fs1 = ImageIO.read(new File("src/graphics/facingSouth1.png"));
      BufferedImage fs2 = ImageIO.read(new File("src/graphics/box.png"));//"facingSouth2.png"));
      BufferedImage fs3 = ImgEdit.reverse(fs2);
      BufferedImage fw1 = ImgEdit.reverse(fe1);
      BufferedImage fw2 = ImgEdit.reverse(fe2);
      BufferedImage fw3 = ImgEdit.reverse(fe3);
      BufferedImage[][] tmp = {{fn1,fn2,fn3}, {fe1,fe2,fe3}, {fs1,fs2,fs3}, {fw1,fw2,fw3}};
      images = tmp;
   }
   
   public void draw(Graphics g, int dir, int state, int x, int y, int width, int height)
   {
      g.drawImage(images[dir][state], x, y, width, height, null);
   }
}