import java.awt.image.BufferedImage;
public class ImgEdit
{
   public static BufferedImage reverse(BufferedImage image)
   {
      BufferedImage newImg = new BufferedImage(image.getWidth(), 
                                               image.getHeight(), 
                                               image.getType());
      for(int i=0; i<image.getWidth(); i++)
         for (int j=0; j<image.getHeight();j++)
         {
            newImg.setRGB(i, j, image.getRGB(image.getWidth()-i-1, j));
            newImg.setRGB(image.getWidth()-i-1, j, image.getRGB(i, j));
         }
      return newImg;
   }
}