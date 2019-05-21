
// 3/27/18

public class Creature
{
   private double maxDefense;
   private double maxHealth;
   private double maxStamina;
   private double maxPower;
   private double defense;
   private double health;
   private double stamina;
   private double power;
   private int level;
   public Creature(double md, double mh, double ms, double mp, int l)
   {
      maxDefense = md;
      maxHealth = mh;
      maxStamina = ms;
      maxPower = mp;
      defense = md;
      health = mh;
      stamina = ms;
      power = mp;
      level = l;
   }
   public int getLevel()
   {
      return level;
   }
   public void setLevel(int l)
   {
      level = l;
   }
   public double getDefense()
   {
      return defense;
   }
   public double getHealth()
   {
      return health;
   }
   public double getStamina()
   {
      return stamina;
   }
   public double getPower()
   {
      return power;
   }
   public void setDefense(double d)
   {
      defense = d;
   }
   public void setHealth(double h)
   {
      health = h;
   }
   public void setStamina(double s)
   {
      stamina = s;
   }
   public void setPower(double p)
   {
      power = p;
   }
   public double getMaxDefense()
   {
      return maxDefense;
   }
   public double getMaxHealth()
   {
      return maxHealth;
   }
   public double getMaxStamina()
   {
      return maxStamina;
   }
   public double getMaxPower()
   {
      return maxPower;
   }
   public void setMaxDefense(double d)
   {
      maxDefense = d;
   }
   public void setMaxHealth(double h) 
   {
      maxHealth = h;
   }
   public void setMaxStamina(double s)
   {
      maxStamina = s;
   }
   public void setMaxPower(double p)
   {
      maxPower = p;
   }
   public void addDefense(double d)
   {
      defense = d + defense;
   }
   public void addHealth(double h)
   {
      health = h + health;
   }
   public void addStamina(double s)
   {
      stamina = s + stamina;
   }
   public void addPower(double p)
   {
      power = p + power;
   }
   
  }