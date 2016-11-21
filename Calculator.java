import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 *I tried to.
 * Create a graphical calculator
 *
 *@author <em> Helen Zhao <em>
 */
public class Calculator extends JFrame implements ActionListener{
  
  /**display the input and calculation*/
  private JTextArea Area;
  
  /**the frame work of the calculator*/
  private JPanel panel = new JPanel(new GridLayout(4,4));
  
  /**
   * Constructor that creates the calculator
   */
  public Calculator(){
    /*add all the buttons*/
    JButton b1 = new JButton("7");
    panel.add(b1).setLocation(0,0);
    JButton b2 = new JButton("8");
    panel.add(b2).setLocation(0,1);
    JButton b3 = new JButton("9");
    panel.add(b3).setLocation(0,2);
    JButton b4 = new JButton("x");
    panel.add(b4).setLocation(0,3);
    JButton b5 = new JButton("4");
    panel.add(b5).setLocation(1,0);
    JButton b6 = new JButton("5");
    panel.add(b6).setLocation(1,1);
    JButton b7 = new JButton("6");
    panel.add(b7).setLocation(1,2);
    JButton b8 = new JButton("-");
    panel.add(b8).setLocation(1,3);
    JButton b9 = new JButton("1");
    panel.add(b9).setLocation(2,0);
    JButton b10 = new JButton("2");
    panel.add(b10).setLocation(2,1);
    JButton b11 = new JButton("3");
    panel.add(b11).setLocation(2,2);
    JButton b12 = new JButton("+");
    panel.add(b12).setLocation(2,3);
    JButton b13 = new JButton("0");
    panel.add(b13).setLocation(3,0);
    JButton b14 = new JButton(".");
    panel.add(b14).setLocation(3,1);
    JButton b15 = new JButton("-");
    panel.add(b15).setLocation(3,2);
  }
  public void actionPerformed(ActionEvent e) {
  }
}
  
  