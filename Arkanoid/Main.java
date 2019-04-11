import Arkanoid.Arkanoid;
import javax.swing.JFrame;

public class Main
{
  public Main() {}
  
  public static void main(String[] args)
  {
    JFrame frame = new JFrame("Arkanoid");
    frame.setDefaultCloseOperation(3);
    frame.setLayout(new java.awt.BorderLayout());
    frame.setSize(1280, 720);
    frame.setLocationRelativeTo(null);
    
    Arkanoid game = new Arkanoid(1280, 720);
    frame.add(game, "Center");
    
    frame.setVisible(true);
  }
}
