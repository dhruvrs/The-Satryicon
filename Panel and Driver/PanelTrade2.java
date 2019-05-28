//Max Desousa
//Dhruv Sridhar
import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.awt.image.*;
   import java.util.ArrayList;
   import java.io.*;
   import javax.swing.JPopupMenu;
   import javax.swing.event.*;
public class PanelTrade2 extends JPanel
{
   private Hero shop;
   private JLabel labelNumber;
   private JTextField userInputBox;
   private JTextField userInputBox2;
   private JLabel labelBuy;
   private JLabel labelSell;
   private int sellIndex = 0;
   private int buyIndex = 0;

   public PanelTrade()
   {
     setLayout(new GridBagLayout());
      GridBagConstraints c = new GridBagConstraints();
      
      JPanel panel = new JPanel();
      //panel.setLayout(new FlowLayout());
      add(panel);
   
      labelBuy = new JLabel("What # item is to be bought or sold?");
      labelBuy.setFont(new Font("Serif", Font.BOLD, 20));
      labelBuy.setForeground(Color.blue);
      //add(labelBuy);
               
      userInputBox = new JTextField("0", 5);
      //userInputBox.setHorizontalAlignment(SwingConstants.RIGHT);
      //panel.add(userInputBox);
     
      userInputBox2 = new JTextField("0", 5);
     //userInputBox2.setHorizontalAlignment(SwingConstants.RIGHT);
     //panel.add(userInputBox2, BorderLayout.SOUTH);
      
           

           // labelSell = new JLabel("What # item is to be sold?");
            //labelSell.setFont(new Font("Serif", Font.BOLD, 20));
           // labelSell.setForeground(Color.blue);
            //add(labelSell);




   
      JButton confirmButton = new JButton("Confirm Buy");
      confirmButton.addActionListener(new ConfirmListener());
      //panel.add(confirmButton);
      
      JButton confirmButton2 = new JButton("Confirm Sell");
      confirmButton2.addActionListener(new ConfirmListener2());
     // panel.add(confirmButton2);
      
      c.gridx = 0;
      c.gridy = 0;
      add((labelBuy),c);
      
      c.gridy = 1;
      add(userInputBox,c);
      
      c.gridx = 1;
      add(confirmButton, c);
      
      c.gridy = 2;
      c.gridx = 0;
      add(userInputBox2,c);
      
      
     
      c.gridx = 1;
      add(confirmButton2, c);
   }

   
   private class ConfirmListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         buyIndex = (int)Double.parseDouble(userInputBox.getText());
      }
   }
   
      private class ConfirmListener2 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         sellIndex = (int)Double.parseDouble(userInputBox.getText());
      }
   }
   //shop = new Hero(0,0,0,0,0,0,0,0,"Transparent.png")
   //Make a "Hero" but just to store stuff
   //shop.addToInv(new Armour(20, true, true, "Good Armour", 10, "TestArmour.png"));
   //add items to "shop" 
   
   //hero.sellItem(sellIndex);
  // hero.buyItem(/*use index to get item with method somewhere*/)

  //I really don't know how to tie this in with everything else
            
            
  /*    public void addMoney(double m)
   {
      money = money + m;
   }
   public void spendMoney(double m)
   {
      money = money - m;
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
*/
}
