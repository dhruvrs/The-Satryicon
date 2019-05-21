import java.awt.image.BufferedImage;
import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.awt.image.*;
   
   import java.io.*;
public class Hero extends Creature
{
   private boolean isArmourEquipped;
   private boolean isWeaponEquipped;
   private boolean isInBattle;
   private Armour armour;
   private Weapon weapon;
   private Items[] inventory;
   private int invIndex;
   private double ex;
   private double money;
   private int dx;
   private int dy;
   private int myX;
   private int myY;
   private int width;
   private int height;
  // array of images?
   private ImageIcon images;
   
   public Hero(double d, double h, double s, double p,  int x, int y, int w, int hi, String files)
     throws IOException
   {
      super(d, h, s, p, 1);
      isArmourEquipped = false;
      isWeaponEquipped = false;
      isInBattle = false;
      armour = null;
      weapon = null;
      inventory = new Items[25];
      invIndex = 0;
      ex = 0;
      money = 0;
      dx = 0;
      dy = 0;
      myX = x;
      myY = y;
    
      width = w;
      height = hi;
      
      images = new ImageIcon(files);
     
      
   }
   
   public double getEX()
   {
      return ex;
   }
   public void addEX(double e)
   {
      ex = ex + e;
   }
   public void setEX(double e)
   {
      ex = e;
   }
   public double getMoney()
   {
      return money;
   }
   public void addMoney(double m)
   {
      money = money + m;
   }
   public void spendMoney(double m)
   {
      money = money - m;
   }
   
   public void setMoney(double m)
   {
      money = m;
   }
   public int getX()
   {
      return myX;
   }
   public int getY()
   {
      return myY;
   }
   public void setX(int x)
   {
      myX = x;
   }
   public void setY(int y)
   {
      myY = y;
   }
   public int getdx()
   {
      return dx;
   }
   public int getdy()
   {
      return dy;
   }
   public void setdx(int x)
   {
      dx = x;
   }
   public void setdy(int y)
   {
      dy = y;
   }
   public void move()
   {
      myX = myX + dx;
      myY = myY + dy;
   }
   public boolean getIsArmourEquipped()
   {
      return isArmourEquipped;
   }
   public void setIsArmourEquipped(boolean ie)
   {
      isArmourEquipped = ie;
   }
   public boolean getIsWeaponEquipped()
   {
      return isWeaponEquipped;
   }
   public void setIsWeaponEquipped(boolean ie)
   {
      isWeaponEquipped = ie;
   }
   public boolean getIsInBattle()
   {
      return isInBattle;
   }
   public void inBattle(Monster monster)
   {
      isInBattle = true;
   
   }
   public void notInBattle()
   {
      isInBattle = false;
  
   }
   public Armour getArmour()
   {
      return armour;
   } 
   public Weapon getWeapon()
   {
      return weapon;
   }
   public Items[] getInventory()
   {
      return inventory;
   }
   public void addToInv(Items item)
   {
      if(invIndex < inventory.length)
      {
         inventory[invIndex] = item;
         invIndex++;
      }
   }
   public int getInvIndex()
   {
      return invIndex;
   }
   
   public void equipArmour(Armour arm)
   {
      if(isArmourEquipped)
         unequipArmour();
      if(arm.getDefenseHelp() + getDefense() > getMaxDefense())
         setDefense(getMaxDefense());
      else
         addDefense(arm.getDefenseHelp());
      armour = arm;
   }
   public void equipWeapon(Weapon wea)
   {
      if(isWeaponEquipped)
         unequipWeapon();
      if(wea.getDefenseHelp() + getDefense() > getMaxDefense())
         setDefense(getMaxDefense());
      else
         addDefense(wea.getDefenseHelp());
      if(wea.getHealthHelp() + getHealth() > getMaxHealth())
         setHealth(getMaxHealth());
      else
         addHealth(wea.getHealthHelp());
      if(wea.getStaminaHelp() + getStamina() > getMaxStamina())
         setStamina(getMaxStamina());
      else
         addStamina(wea.getStaminaHelp());
      weapon = wea;
   }
   public void unequipArmour()
   {
      //figure out what would happen if defense is max before equip then unequip
      setDefense(getDefense() - armour.getDefenseHelp());
      armour = null;
   }
   public void unequipWeapon()
   {
      setDefense(getDefense() - weapon.getDefenseHelp());
      setStamina(getStamina() - weapon.getStaminaHelp());
      setHealth(getHealth() - weapon.getHealthHelp());
      weapon = null;
   }
   public void buyItem(Items item)
   {
      if(invIndex < 25 && item.getPrice() < money)
      {
        addToInv(item);
        spendMoney(item.getPrice());
      }
   }
   //ask if when a character want to remove inventory if they 
   //would "sell" it and get money or if it just removes the item
   //(if you have to go to a store to sell items
   public void sellItem(int index)
   {
      addMoney(inventory[index].getPrice());
      inventory[index] = null;
   }
   
   public void wound()
   {
      setHealth(getHealth()- 5);
   }
   public void deplete()
   {
      setStamina(getStamina() - 3);
   }
   public void skill()
   {
      
   }

   public void drawHealthBar(Graphics myBuffer, int x, int y)
   {
      myBuffer.setColor(Color.BLACK);
      myBuffer.fillRect(x, y, 104, 8);
      myBuffer.setColor(Color.RED);
      myBuffer.fillRect(x+2, y+2, (int)(((double)(getHealth())/getMaxHealth()) * 100), 4);
   }
   public void drawStaminaBar(Graphics myBuffer, int x, int y)
   {
      int h2 = (int)(((double)(getStamina())/getMaxStamina()) * 50);
      int y2 = 51 + y - h2;
      myBuffer.setColor(Color.BLACK);
      myBuffer.fillRect(x, y, 6, 52);
      myBuffer.setColor(Color.GREEN);
      myBuffer.fillRect(x+1, y2, 4, h2);
   }
   
   public void draw(Graphics myBuffer)
   {
      myBuffer.drawImage(images.getImage(), myX, myY, width, height, null);
   }
}
