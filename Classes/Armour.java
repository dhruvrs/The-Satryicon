public class Armour extends Equipment
{
   private double defenseHelp;
   public Armour(double p, boolean ip, boolean is, String n, double dh, String file)
   {
      super(p, ip, is, n, file);
      defenseHelp = dh;
   }
   public double getDefenseHelp()
   {
      return defenseHelp;
   }
   public void setDefenseHelp(double dh)
   {
      defenseHelp = dh;
   }
   public void setNameArm()
   {
      if(getPrice() > 30)
         setName("Great Armour");
      else if(getPrice() > 20)
         setName("Good Armour");
      else
         setName("Fair Armour");
   }
   public void benefit()
   {
   }
   public char identify()
   {
      return 'a';
   }
   
}