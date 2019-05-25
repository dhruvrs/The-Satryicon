import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*; 
public class Button extends JButton
{
	private ImageIcon idle, hover, pressed;
   public Button(ImageIcon idle, ImageIcon hover, ImageIcon pressed, ActionListener action)
   {
	  super(idle);
	  this.idle = idle;
	  this.hover = hover;
	  this.pressed = pressed;
      addActionListener(action);
      addMouseListener(new MouseListener());
      setBorderPainted(false);
   }
   private class MouseListener extends MouseAdapter
   {
      public void mouseEntered(MouseEvent e)
      {
         setIcon(hover);
      }
      public void mouseExited(MouseEvent e)
      {
         setIcon(idle);
      }
      public void mousePressed(MouseEvent e)
      {
         setIcon(pressed);
      }
   } 
}