import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;
import java.io.*;
import java.util.*;
import java.util.List;

public class Board extends JFrame {

   private Player player = new Player();
   private Player cpu = new Player();
   private List<Card> cards = new ArrayList<>();

	Board(File imageFile){
       setContentPane(new JLabel(new ImageIcon(imageFile.getAbsolutePath())));

       initComponents();
	}

   private void initComponents(){
      setTitle("Slapjack");
      
      Deck d = new Deck();
      player.addCardToHand(d.getCards().subList(0,26));
      cpu.addCardToHand(d.getCards().subList(26, 52));

      //THIS IS A TEST LINE.  Puts cards in center deck for testing purposes
      //cards = player.getHand();
      
      // User input 
       Action playerSlap = new AbstractAction(){
    	   public void actionPerformed(ActionEvent e){
    		   slap();
    	   }
       };
       getRootPane().getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "pressed");
       getRootPane().getActionMap().put("pressed", playerSlap);
      // End User Input

      // --------------Establish Central Column Layout-------------
      getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
      add(Box.createHorizontalGlue());

      CardsPanel panel = new CardsPanel();
      panel.setOpaque(false);
      add(panel);
      
      add(Box.createHorizontalGlue());
      // --------------Establish Central Column Layout-------------


		pack(); // Sizes window to fit its components
		setLocationRelativeTo(null); // center this window on the screen
		setDefaultCloseOperation(EXIT_ON_CLOSE);
      setJMenuBar(makeMenu());
		setVisible(true);
   }


   //JPanel that displays all the decks and shadows
   public class CardsPanel extends JPanel{
      private BufferedImage back;

      CardsPanel(){
         setLayout(null);
         try {
            back = ImageIO.read(new File("back.png"));
         } catch (IOException e) {
            e.printStackTrace();
         }
      }

      public void paintComponent(Graphics g){
         super.paintComponent(g);
         int h = getWidth()/2-71;

         //displays the cpu card pile until it has no more cards
         int numCpuCards = cpu.getHand().size(); //get number of cpu cards
         if(numCpuCards > 0) {
            g.drawImage(back, h, 100, 143, 200, this); //display the back of cpu card
            Polygon cpuShadow = new Polygon();
            cpuShadow.addPoint(h+ 143, 100);
            cpuShadow.addPoint(h+ 143, 300);
            cpuShadow.addPoint(h, 300);
            cpuShadow.addPoint(h+10 + numCpuCards/5, 300 + numCpuCards/3);
            cpuShadow.addPoint(h+143 + numCpuCards/3, 300 + numCpuCards/3);
            cpuShadow.addPoint(h+143 + numCpuCards/3, 110 + numCpuCards/5);
            g.setColor(Color.BLACK);
            g.fillPolygon(cpuShadow);//draw shadow
         }

         //displays the middle deck until there is no more cards
         int numDeckCards = cards.size(); //get number of deck cards
         if(numDeckCards > 0) {
            int y = (getHeight() - 250)/2; //where card is located on JPanel

            //displays the most recently flipped card
            g.drawImage(cards.get(cards.size() - 1).getImage(), h, y, 143, 200, this);
            Polygon deckShadow = new Polygon();
            deckShadow.addPoint(h+143, y);
            deckShadow.addPoint(h+143, y +192);
            deckShadow.addPoint(h+135, y+200);
            deckShadow.addPoint(h, y + 200);
            deckShadow.addPoint(h+10 + numDeckCards / 5, y + 200 + numDeckCards / 3);
            deckShadow.addPoint(h+143 + numDeckCards / 3, y + 200 + numDeckCards / 3);
            deckShadow.addPoint(h+143 + numDeckCards / 3, y + 10 + numDeckCards / 5);
            g.setColor(Color.BLACK);
            g.fillPolygon(deckShadow); //draw shadow
         }


         //displays the player card pile until there is no more cards
         int numPlayerCards = player.getHand().size(); //get number of player cards
         if(numPlayerCards > 0) {
            int y = getHeight()-350; //find where card is located on JPanel
            g.drawImage(back, h, y, 143, 200, this); //display back of player card
            Polygon playerShadow = new Polygon();
            playerShadow.addPoint(h+143, y);
            playerShadow.addPoint(h+143, y + 200);
            playerShadow.addPoint(h, y + 200);
            playerShadow.addPoint(h+10 + numPlayerCards / 5, y + 200 + numPlayerCards / 3);
            playerShadow.addPoint(h+143 + numPlayerCards / 3, y + 200 + numPlayerCards / 3);
            playerShadow.addPoint(h+143 + numPlayerCards / 3, y + 10 + numPlayerCards / 5);
            g.setColor(Color.BLACK);
            g.fillPolygon(playerShadow); //draw shadow
         }

         g.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
         g.drawString(Integer.toString(cpu.getHand().size()), getWidth()/2-5, 90);
         g.drawString(Integer.toString(player.getHand().size()), getWidth()/2-5, getHeight()-115);
      }

   }
   
   public JMenuBar makeMenu(){
      JMenuBar	menuBar = new JMenuBar();
   
      
      //-------------------Game Menu-----------------------------------
      JMenu gameMenu = new JMenu("Game");
      gameMenu.setMnemonic('G');
      menuBar.add(gameMenu);
      
      JMenuItem playItem = new JMenuItem("Play");
      playItem.setToolTipText("Play a new random game");
      playItem.setAccelerator(KeyStroke.getKeyStroke("ctrl P"));
      playItem.setMnemonic('P');
      gameMenu.add(playItem);
      playItem.addActionListener(new ActionListener(){
               public void actionPerformed(ActionEvent e){
                  play(); }});
   
      JMenuItem restartItem = new JMenuItem("Restart");
      restartItem.setToolTipText("Restart this game");
      restartItem.setAccelerator(KeyStroke.getKeyStroke("ctrl R"));
      restartItem.setMnemonic('R');
      gameMenu.add(restartItem);
      restartItem.addActionListener(new ActionListener(){
               public void actionPerformed(ActionEvent e){
                  restart(); }});
      return menuBar;
   }
   
   public void play(){
   
   }
   
   public void restart(){
      this.setVisible(false);
      this.dispose();
      new Board(new File("feltTable.jpg"));
   }
   
   public void slap(){
      if(!cards.isEmpty()){
   	   if(cards.get(0).rank().equals("JACK")) {
   		  player.addCardToHand(cards);
   	   } else {
   		   cpu.addCardToHand(cards);
   	   }
      }   
   }
   
	public static void main(String[] args){
		Board bob = new Board(new File("feltTable.jpg"));
	}
}
