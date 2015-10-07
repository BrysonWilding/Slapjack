import java.util.ArrayList;
import java.util.List;

public class Player {
	private List<Card> hand = new ArrayList<>();
	
	public void addCardToHand(List<Card> card) {
		hand.addAll(card);
	}
	
	public Card drawCard() {
		Card cardToPlay = hand.get(0);
		hand.remove(0);
		return cardToPlay;
	}
}
