
// 3/27/19

//need to make this class abstract
public abstract class Equipment extends Items
{
   public Equipment(double p, boolean ip, boolean is, String n, String file)
   {
      super(p, ip, is, n, file);
   }
   public Equipment(double p, boolean ip, boolean is, String file)
   {
      super(p, ip, is, file);
   }
   
}