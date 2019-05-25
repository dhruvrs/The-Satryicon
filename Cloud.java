import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Cloud {
	private final int SCREENWIDTH = 1300;
	private final int MAXHEIGHT = 300;
	private final int MINHEIGHT = 20;//minimum distance 
	private final int MAXVELOCITY = 10;
	private final int MINVELOCITY = 2;
	private final int SIZE_HEIGHT = 100;
	private final int SIZE_WIDTH = 200;
	private BufferedImage CLOUD1;
	private BufferedImage CLOUD2;
	private BufferedImage CLOUD3;
	private int height, velocity, x;
	private double sizeRatio;
	private BufferedImage image;
	
	public Cloud(){
		try {
		CLOUD1 = ImageIO.read(new File("src/graphics/Cloud1.png"));
		CLOUD2 = ImageIO.read(new File("src/graphics/Cloud2.png"));
		CLOUD3 = ImageIO.read(new File("src/graphics/Cloud3.png"));
		}catch(IOException e) {}
		generate();
	}
	
	/***********
	 generate method description:
	 	sets all the random variables of the cloud object
	 **********/
	public void generate(){
		height = (int)(Math.random()*(MAXHEIGHT-MINHEIGHT)+MINHEIGHT);
		velocity = (int)(Math.random()*(MAXVELOCITY-MINVELOCITY)+MINVELOCITY);
		double temp = Math.random();
		if(temp<.3)
			image = CLOUD1;
		else if(temp<.6)
			image = CLOUD2;
		else
			image = CLOUD3;
		sizeRatio = Math.random()+.5;
		x = (int)(-SIZE_WIDTH*sizeRatio);	
	}
	/***********
	 draw method description:
	 	draws the cloud, and then shifts it
	 	if cloud is offscreen, creates a new 
	 	cloud to replace it
	 * @param g
	 **********/
	public void draw(Graphics g){
		g.drawImage(image, x, height, (int)(SIZE_WIDTH*sizeRatio), (int)(SIZE_HEIGHT*sizeRatio), null);
		x+=velocity;
		if(x>SCREENWIDTH)
			generate();
	}	
	public int getX(){
		return x;		
	}
	public void setX(int x) {
		this.x = x;
	}
}
	
