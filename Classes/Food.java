
// 3/27/19

public abstract class Food extends Items
{
   public Food(double p, boolean ip, boolean is, String n, String file)
   {
      super(p, ip, is, n, file);
   }
   public char identify()
   {
      return 'f';
   }
   
}