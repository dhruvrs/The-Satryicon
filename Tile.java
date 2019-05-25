import java.awt.image.BufferedImage;
import java.awt.Graphics;
import javax.imageio.*;

public abstract class Tile
{
   private int size;
   private BufferedImage image;
   private boolean[] validEntryDir;

   public Tile(int size, BufferedImage image, String valDir){
      this.size = size; 
      this.image = image;  
      validEntryDir = new boolean[4];
      for(int i=0; i<4; i++) 
         validEntryDir[i] = (valDir.charAt(i)=='t');
   }
   public boolean getWalkable(int dir){
      return validEntryDir[dir];
   }
   public void draw(Graphics g, int x, int y){
      g.drawImage(image, x, y, size, size, null);
   }
   public abstract int interactStep(Area source);
}