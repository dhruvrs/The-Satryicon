	// Torbert, 7.20.06
   //Modified by: Samantha Frietchen Date: 4/26/18 Lab: Lab17
import java.io.*;
   import javax.swing.JFrame;
    public class GameDriver
   {
       private static final int HEIGHT = 1300;
       private static final int WIDTH = 800;
       public static void main(String[] args) throws IOException
       { 
         JFrame frame = new JFrame("Hero's Journey");
         frame.setSize(HEIGHT, WIDTH);    //makes the mouse location correct
         frame.setLocation(0, 0);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         TestPanel p = new TestPanel();
         frame.setContentPane(p);
         p.requestFocus();
         frame.setVisible(true);
         
       }
   }
