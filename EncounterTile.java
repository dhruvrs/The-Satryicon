import java.awt.image.BufferedImage;
public class EncounterTile extends Tile
{
   private int battleChance;
   public EncounterTile(int size, BufferedImage image, String walk, int battle)
   {
      super(size, image, walk);
      this.battleChance = battle;
   }
   public int interactStep(Area source)
   {//need to check how keyListner works, 
      int random = (int)(Math.random()*100)+1;
      if(random<=battleChance)
         return 4;
      return 1;
   }
}