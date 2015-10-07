import java.awt.image.*;

public class Card {

   public enum Rank {ACE, DEUCE, THREE, FOUR, FIVE,
		SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN,
		KING
	}
   
   public enum Suit { CLUB, SPADE, HEART, DIAMOND }
   
   
   public Card(Rank r, Suit s, BufferedImage img) {
        rank = r;
        suit = s;
        image = img;
    }
   
    
   
   private Rank rank;
	private Suit suit;
   private final BufferedImage image;

   public Rank rank() { return rank; }
   public Suit suit() { return suit; }
   public BufferedImage image() { return image; }
   public String toString() { return rank + " of " + suit; }
    
}