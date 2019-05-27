public class Hero
{
   private int health = 100 ;
   private int attackType;
   private int[] attacks;
   public Hero(int l, int h)
   {
      attacks = new int[2];
      attacks[0] = 5;
      attacks[1] = 10;
      health = h;
   }
   
   public Hero(int[] types, int type)
   {
      attacks = types;
      attackType = type;
   }
   
   public int getDamage()
   {
      return attacks[attackType];
   }
   
   public int getType()
   {
      return attackType;
   }
   
        
     public int getHeroHealth()
   {
      return health;
   }
   
   public void setHealth(int newHealth)
   {
      newHealth = health;
   }
   
   public boolean isDead()
   {
      if(health <= 0)
         return true;
      else if(health > 0)
         return false;
      else 
         return false;
   }
}