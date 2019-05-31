/**
 *@author Regson Do Rego
 *@version v2
 *Date: 27/04/2016;
 *Description: This class is where the cards and suits gets put together, this information then makes up the user's hand.
 */
import java.util.ArrayList;

public class Hands {

	ArrayList<String> userHand = new ArrayList<>();
	ArrayList<String> dealerHand = new ArrayList<>();
	ArrayList<Integer> userHandValue = new ArrayList<>();
	ArrayList<Integer> dealerHandValue = new ArrayList<>();
	ArrayList<String> cardUsed = new ArrayList<>();
	Boolean userTurn = true;
	private String card;
	private Cards genCards = new Cards();



	/**
	 *Combines the random suit and the random card number to create the indiviviual card. 
	 */
	public void setCard() {
		genCards.setSuit();
		genCards.setNumber();
		card = genCards.getNumber() + "_of_" + genCards.getSuit();
	}

	/**
	 * @return The card that has been randomised
	 */
	public String getCard(){
		return card;
	}

	/**
	 * Checks if the card already has been played, if so, requests another card to be randomised until one that hasn't been used is randomised.
	 */
	public void checkDouble() {

		for (String search : cardUsed) {
			while (search.equals(card)) {
				setCard();
			}
		}
		cardUsed.add(card);
	}

	/**
	 * 
	 * @return Gets the total hand value for the user
	 */
	public Integer getTotalUserHandValue() {
		int totalHand = 0;
		for (Integer i : userHandValue) {
			totalHand = totalHand + i;
		}
		return totalHand;
	}

	/**
	 * @return Gets the total hand value for the dealer
	 */
	public Integer getTotalDealerHandValue() {
		int totalHand = 0;
		for (Integer i : dealerHandValue) {
			totalHand = totalHand + i;
		}
		return totalHand;
	}



	/**
	 * Adds the cards to the user's or dealer's hand and also add's the value of the card into another array, so the total can be calculated easier.
	 */
	public void addToHand() {
		if (userTurn) {
			userHand.add(card);
			if (genCards.getNumber().equals("King") || genCards.getNumber().equals("Queen")||genCards.getNumber().equals("Jack")) {
				userHandValue.add(10);
			} else {
				if (genCards.getNumber().equals("Ace")) {
					userHandValue.add(1);
				} else {
					userHandValue.add(Integer.parseInt(genCards.getNumber()));
				}
			}

		} else {
			dealerHand.add(card);
			if (genCards.getNumber().equals("King") || genCards.getNumber().equals("Queen") || genCards.getNumber().equals("Jack")) {
				dealerHandValue.add(10);
			} else if (genCards.getNumber().equals("Ace")) {
				dealerHandValue.add(1);
			} else {
				dealerHandValue.add(Integer.parseInt(genCards.getNumber()));
			}
		}
	}
}

