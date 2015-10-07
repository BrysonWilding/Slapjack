public class Card {

    public enum Rank {ACE, DEUCE, THREE, FOUR, FIVE,
		SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN,
		KING
	}
   public enum Suit { CLUB, SPADE, HEART, DIAMOND }
   
   
   public Card(int suit, int rank, image i) {
		this.image = i;
		this.rank = Rank.values()[rank];
		this.suit = Suit.values()[suit];
   }
   
    
   
   private Rank rank;
	private Suit suit;
   private final BufferedImage image;
   
   public int value() { return rank.value; }
   public Rank rank() { return rank; }
   public Suit suit() { return suit; }
   public BufferedImage image() { return image; }
   public String toString() { return rank + " of " + suit; }
    
}