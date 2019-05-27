public class Battle
{
   private Hero player;
   private Monster enemy;
   
   public Battle(Hero hero, Monster monster)
   {
      player = hero;
      enemy = monster;
   }
  
   public void attack(int type)
   {
      player.setType(type);
      enemy.setHealth(enemy.getHealth()-player.getDamage());
      player.setHealth(player.getHealth()-enemy.getDamage());
   }  
//assumes we have a way to get button presses from the user
   
   public void battle()
   {
   
   }
}