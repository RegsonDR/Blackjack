/**
 *@author Regson Do Rego
 *@version v2
 *Date: 27/04/2016;
 *Description: This class randomises the cards and the suits.
 *
 * cards from http://opengameart.org/content/playing-cards-vector-png
 * gameicon from http://www.veryicon.com/
 * card back from http://www.dreamstime.com
 */
import java.util.Random;

public class Cards {
	
	private String[] cardNumberArray = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
	private int currentNumberIndex;
	private String[] suitsArray = {"clubs", "diamonds", "hearts", "spades"};
	private int currentSuitIndex;

	/**
	 * Needs this method into order to random the cards and the suits
	 */
	private Random randomise = new Random();

	/**
	 * This chooses a location number from the cardNumberArray.
	 */
	public void setNumber() {
		currentNumberIndex = randomise.nextInt(cardNumberArray.length);
	}

	/**
	 * Using the setNumber() method, the random location is used to determine the card number that will be used.
	 * @return A random card number
	 */
	public String getNumber(){
		return cardNumberArray[currentNumberIndex];
	}

	/**
	 *This chooses a location number from the suitsArray.
	 */
	public void setSuit() {
		currentSuitIndex = randomise.nextInt(suitsArray.length);
	}

	/**
	 * Using the setSuit() method, the random location is used to determine the card number that will be used.
	 * @return A random suit
	 */
	public String getSuit(){
		return suitsArray[currentSuitIndex];
	}

}
