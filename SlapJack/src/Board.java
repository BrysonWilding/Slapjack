import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class Board extends JFrame {

	Board(File imageFile){
		setContentPane(new JLabel(new ImageIcon(imageFile.getAbsolutePath())));
      initComponents();
      
	}
   
   Board(){
      // add color here
      initComponents();
   }
   
   private void initComponents(){
      setTitle("Slapjack");
      
      // --------------Establish Central Column Layout-------------
      getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
      add(Box.createHorizontalGlue());
      
      JPanel panel = new JPanel();
      panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
      panel.setOpaque(false);
      
      add(panel);
      
      add(Box.createHorizontalGlue());
      // --------------Establish Central Column Layout-------------
      
      // --------------Add Central Column Components-------------
      panel.add(Box.createRigidArea(new Dimension(0, 20)));      
      
      drawTop(panel); // add computer "deck"
      
      panel.add(Box.createVerticalGlue());      
      
      drawMiddle(panel); // add "pile"
      
      panel.add(Box.createVerticalGlue()); 
      
      drawBottom(panel); // add player "deck"
     
      panel.add(Box.createRigidArea(new Dimension(0, 20)));
      // --------------Add Central Column Components-------------      
          
		pack(); // Sizes window to fit its components
		setLocationRelativeTo(null); // center this window on the screen
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
   }
	
   private void drawTop(JPanel panel){
      panel.add(new JButton("Computer"));
   }
   
   private void drawMiddle(JPanel panel){
      panel.add(new JButton("Board"));
   }
   
   private void drawBottom(JPanel panel){
      panel.add(new JButton("Player"));
   }
   
	public static void main(String[] args){
		Board bob = new Board(new File("feltTable.jpg"));
	}
}
