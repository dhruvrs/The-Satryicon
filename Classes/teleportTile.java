import java.io.IOException;
import java.io.File;
import java.awt.image.BufferedImage;
public class teleportTile extends Tile
{
   private String tpArea;
   private int x, y;
   public teleportTile(int size, BufferedImage image, String valDir, String tpArea, int x, int y)
   {
      super(size, image, valDir);
      this.tpArea = tpArea;
      this.x = x;
      this.y = y;
   }
   public void interactStep(Area source)
   {//need to check how keyListner works,
      try{
         source.loadArea(new File(tpArea));
      }catch(IOException e){}
      source.setCoor(x, y);
   }
}