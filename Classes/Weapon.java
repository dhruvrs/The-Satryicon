public class Weapon extends Equipment 
{
   private double defenseHelp;
   private double healthHelp;
   private double staminaHelp;
   private double damage;
   private double staminaNeeded;
   public Weapon(double p, boolean ip, boolean is, String n, double dh, double hh, double sh, double d, double sn, String file)
   {
      super(p, ip, is, n, file);
      defenseHelp = dh;
      healthHelp = hh;
      staminaHelp = sh;
      damage = d;
      staminaNeeded = sn;
   }
   public double getDefenseHelp()
   {
      return defenseHelp;
   }
   public void setDefenseHelp(double dh)
   {
      defenseHelp = dh;
   }
   public double getHealthHelp()
   {
      return healthHelp;
   }
   public void setHealthHelp(double hh)
   {
      healthHelp = hh;
   }
   public double getStaminaHelp()
   {
      return staminaHelp;
   }
   public void setStaminaHelp(double sh)
   {
      staminaHelp = sh;
   }
   public double getDamage()
   {
      return damage;
   }
   public void setDamage(double d)
   {
      damage = d;
   }
   public double getStaminaNeeded()
   {
      return staminaNeeded;
   }
   public void setStaminaNeeded(double sn)
   {
      staminaNeeded = sn;
   }
   public void benefit()
   {
   }
   public char identify()
   {
      return 'w';
   }
   
}