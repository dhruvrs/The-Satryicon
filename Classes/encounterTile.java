    
import java.awt.image.BufferedImage;
public class encounterTile extends Tile
{
   private int battleChance;
   public encounterTile(int size, BufferedImage image, String walk, int battle)
   {
      super(size, image, walk);
      this.battleChance = battle;
   }
   public void interactStep(Area source)
   {//need to check how keyListner works, 
      int random = (int)(Math.random()*100)+1;
      if(random<=battleChance);/*callBattle();*/
   }
}