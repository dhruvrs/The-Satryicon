
public class Monster extends Hero
{
   public static final int[] damages = {5, 10, 15, 20, 40};
   public static final int[] healths=  {20, 40, 60, 100, 200};
   public Monster(int type)
   {
      super(damages, type);
      setHealth(healths[getType()]);
   }
   
}
        
         
     
   

