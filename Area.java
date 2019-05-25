import java.io.IOException;
import java.io.File;
import javax.imageio.*;
import java.util.Scanner;
import java.util.HashMap;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
/***********
Area class
Stores images and specific tile properties of 
current area, properties include(Image, Walkable, and battleChance)
additional tile properties will be indirectly attributed at another level
***********/
public class Area
{  
   //Instance data
	private String name;
   private int xIndex, yIndex;
   private int numRows, numCols;
   private Tile[][] tiles;
   private int tileSize;
   //centerX and centerY refer to the center of the screen for display purposes
   private int centerX, centerY;
   //EXH and EXV refer to the amount of tiles to render in each direction
   private final int EXV = 4, EXH = 6;
   
   public Area(String file, int tileSize, int centerX, int centerY)
   {
	  this.name = file;
      this.tileSize = tileSize;
      this.centerX = centerX;
      this.centerY = centerY;
      //attempts to load area
      try{this.loadArea(new File(file));}
      catch(IOException e){}
   }
   public void loadArea(File file) throws IOException
   {
	   
      //create scanner to read area file
      Scanner scan = new Scanner(file);
      //create temporary mapping
      HashMap<Character, Tile> tileMap = new HashMap<Character, Tile>();
      //fills a mapping of each Character to a tile
      int numMappings = scan.nextInt();
      //room for moretimization in terms of ram managment
      for(int i=0; i<numMappings; i++){
         //hold values to store in map
         Character key = scan.next().charAt(0);
         BufferedImage image = ImageIO.read(new File("src/graphics/"+scan.next()));
         String valDir = scan.next();
         char type = scan.next().charAt(0);
         Tile temp = null;
         if(type == 'B')
         {
            int battleChance = scan.nextInt();
            temp = new EncounterTile(tileSize, image, valDir, battleChance);
         }
         else if(type == 'T')
         {
            String newArea = "src/maps/"+scan.next(); 
            int newX = scan.nextInt();
            int newY = scan.nextInt();
            temp = new TeleportTile(tileSize, image, valDir, newArea, newX, newY);
         }
         //Fills area mappings
         tileMap.put(key, temp);       
      }
      //scans a grid of characters to generate a 2d representation of an area
      //charaters used to map to specific mapping values
      this.numRows = scan.nextInt();
      this.numCols = scan.nextInt();
      tiles = new Tile[this.numRows][this.numCols];
      for(int i=0; i<this.numRows; i++){
         String temp = scan.next();
         for(int j=0; j<temp.length(); j++)
            tiles[i][j] = tileMap.get(temp.charAt(j));  
      }
   }
   /***********
   draw method:
   description:
      only renders EXV tiles above and below
      and EXH tiles to the left and right relative
      to the players coordinates
   @Param:
      xIndex and yIndex determine what tiles need to be drawn,
      while xOffset and yOffset determine to what extent those 
      coordinates are off center
   ***********/
   public void draw(Graphics g, int xOffset, int yOffset)
   {
	   
      for(int i = yIndex-EXV, yPos = centerY-tileSize/2-tileSize*EXV; i<=yIndex+EXV; i++, yPos+=tileSize)
      {
         for(int j = xIndex-EXH, xPos = centerX-tileSize/2-tileSize*EXH; j<=xIndex+EXH; j++, xPos+=tileSize)
         {
            if(!(i>=numRows||i<0||j>=numCols||j<0))
               tiles[i][j].draw(g, xPos+xOffset, yPos+yOffset);
         }
      }
   }
   public int interactStep()
   {
	   return this.getTile(xIndex, yIndex).interactStep(this);
   }
   
   /***********
   isValidStep method
   **preconditions, (x, y) must be a valid tile**
   @param: x,y coorinates of Tile to check
   @return: boolean value, whether the tile is walkable or not
   ***********/
   public boolean isValidStep(int x, int y, int dir)
   {
      return tiles[y][x].getWalkable(dir);
   }
   /***********
   getTile method
   **preconditions, (x, y) must be a valid tile**
   @param: x,y coorinates of Tile to retrieve
   @return: Tile object
   ***********/
   public Tile getTile(int x, int y)
   {
      return tiles[y][x];
   }
   /***********
   other accessor methods
   ***********/
   public int getXIdx()
   {
      return xIndex;
   }
   public int getYIdx()
   {
      return yIndex;
   }
   /***********
   Modifier methods
   ***********/
   public void setTileSize(int size)
   {
      this.tileSize = size;
   }
   public void setCoor(int x, int y)
   {
      this.xIndex=x;
      this.yIndex=y;
   }
   public void incXIdx()
   {
      this.xIndex++;
   }
   public void decXIdx()
   {
      this.xIndex--;
   }
   public void incYIdx()
   {
      this.yIndex++;
   }
   public void decYIdx()
   {
      this.yIndex--;
   }
   
}