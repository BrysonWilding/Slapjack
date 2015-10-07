import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.*;
import java.awt.image.*;
import java.net.*;
import java.io.IOException;
import java.util.List;
import java.io.File;
import javax.imageio.*;

public class Deck {
    private List<Card> cards = new ArrayList<>();

    public Deck() {
      try {
            cards = createDeck();
         } catch (MalformedURLException e) {
            e.printStackTrace();
            System.exit(-1);
         } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
         }
      this.shuffle();   
	 }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    
    static public List<Card> createDeck() throws MalformedURLException, IOException {
            BufferedImage spriteSheet = ImageIO.read(new File("spritesheet.png"));
            int width = spriteSheet.getWidth();
            int height = spriteSheet.getHeight();
            List<Card> deck = new ArrayList<>();
            int suit = 0;
            int rank = 0;
            for (Card.Suit _suit : Card.Suit.values()) {
               for (Card.Rank _rank : Card.Rank.values()) {
                  int x = (rank * width) / 13;
                  int y = (suit * height) / 4;
                  int w = width / 13;
                  int h = height / 4;
                  
                  BufferedImage cardImg = spriteSheet.getSubimage(x, y, w, h);
                  deck.add(new Card(_rank, _suit, cardImg));
                  rank++;
               }
               suit++;
               rank = 0;
            }
            Collections.shuffle(deck);
            return deck;
         }
}