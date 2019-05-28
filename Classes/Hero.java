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
   private Armour armour;
   private Weapon weapon;
   private Items[] inventory;
   private int invIndex;
   private double ex;
   private double money;
   private int[] attacks;
   private ImageIcon image;
   public Hero(double d, double h, double s, double p, String file)
     throws IOException
   {
      super(d, h, s, p, 1);
      isArmourEquipped = false;
      isWeaponEquipped = false;
      armour = null;
      weapon = null;
      inventory = new Items[25];
      invIndex = 0;
      ex = 0;
      money = 0;

      attacks = new int[2];
      attacks[0] = 5;
      setAttackTwo();
      image = new ImageIcon(file);
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
   public int[] getAttacks()
   {
      return attacks;
   }
   public void setAttackTwo()
   {
      if(weapon != null)
         attacks[1] = (int)weapon.getDamage() + 5;
      else
         attacks[1] = 10;
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
      setAttackTwo();
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
      setAttackTwo();
   }
   public void buyItem(Items item)
   {
      if(invIndex < 25 && item.getPrice() < money)
      {
        addToInv(item);
        spendMoney(item.getPrice());
      }
   }
   //sellItem method - 
   public void sellItem(int index)
   {
      addMoney(inventory[index].getPrice());
      for(int x = index; x < inventory.length - 1; x++)
      {
         inventory[index] = inventory[index + 1];
      }
      inventory[inventory.length - 1] = null;
   }
   
   //drawHealthBar method - creates visual representation of hero's health
   //@param Graphics object to draw bar (Graphics)
   //@param value for x coordinate (int)
   //@param value for y coordinate (int)
   public void drawHealthBar(Graphics myBuffer, int x, int y)
   {
      myBuffer.setColor(Color.BLACK);
      myBuffer.fillRect(x, y, 104, 8);
      myBuffer.setColor(Color.RED);
      myBuffer.fillRect(x+2, y+2, (int)(((double)(getHealth())/getMaxHealth()) * 100), 4);
   }
   //drawStaminaBar method - creates visual representation of hero's stamina
   //@param Graphics object to draw bar (Graphics)
   //@param value for x coordinate (int)
   //@param value for y coordinate (int)
   public void drawStaminaBar(Graphics myBuffer, int x, int y)
   {
      int h2 = (int)(((double)(getStamina())/getMaxStamina()) * 50);
      int y2 = 51 + y - h2;
      myBuffer.setColor(Color.BLACK);
      myBuffer.fillRect(x, y, 6, 52);
      myBuffer.setColor(Color.GREEN);
      myBuffer.fillRect(x+1, y2, 4, h2);
   }
   //draw method - draws hero specifically used for battle
   //              sequence where hero is stationary
   //@param Graphics object to draw image (Grpahics)
   //@param value for x coordinate (int)
   //@param value for y coordinate (int)
   //@param value for width of image (int)
   //@param value for height of image (int)
   public void draw(Graphics g, int x, int y, int width, int height)
   {
      g.drawImage(image.getImage(), x, y, width, height, null);
   }
   
}
