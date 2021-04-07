import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.border.Border;
import javax.swing.text.AbstractDocument.Content;

public class basicsPanel extends JPanel{
    //@Override

  private JButton bet;
    public basicsPanel(){
      bet = new JButton("Bet");
      bet.setBounds(350, 400,100, 100);
      add(bet);
    }

    public void paintComponent( Graphics g )
    {
        g.setColor(Color.white);
        g.fillRect(280, 360 , 100, 150);

        g.fillRect(400, 360 , 100, 150);

    }


}
