//Modified by: Samantha Frietchen Date: 4/26/18 Lab: Lab17

import javax.swing.JFrame;
public class GameDriver
{
   private static final int HEIGHT = 1300;
   private static final int WIDTH = 800;
   public static void main(String[] args)
   { 
      JFrame frame = new JFrame("Its a game");
      frame.setSize(HEIGHT, WIDTH);    //makes the mouse location correct
      frame.setLocation(0, 0);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      GamePanel p = new GamePanel();
      frame.setContentPane(p);
      p.requestFocus();
      frame.setVisible(true);     
   }
}