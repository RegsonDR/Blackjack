/**
 *@author Regson Do Rego
 *@version v2
 *Date: 27/04/2016;
 *Description: This class is the game's logic. It checks win and loses after pressing hit or stand buttons.
 */

public class GameLogic extends Hands{

    boolean userWin = false;
    boolean dealerWin = false;
    boolean dealerBJ=false;
    boolean dealerLower=false;


    /**
     *Check if the dealer has a score of 21, if the dealer does then it is a draw (push). This method is only called once the player has 21 to confirm if he has won or a draw has occured.
     */
    public void checkPush(){
        if (getTotalDealerHandValue()==21){
            dealerWin=true;
        }
    }

    /**
     *Checks if the dealer has won or lost. The boolean changes will effect how the program will output the results.
     */
    public void checkWin() {
        if (getTotalDealerHandValue()==21){
            dealerBJ=true;
        }
        else if (getTotalDealerHandValue()>21){
            dealerWin=false;
        }
        else if (getTotalDealerHandValue().equals(getTotalUserHandValue())){
            userWin=true;
            dealerWin=true;
        }
        else if (getTotalDealerHandValue() >getTotalUserHandValue() && getTotalDealerHandValue()<21){
            dealerWin = true;
        } else if (getTotalDealerHandValue()<getTotalUserHandValue() && getTotalUserHandValue()<21){
            dealerLower=true;
        }
    }

    /**
     * This checks the user's hand to see if he has won or lost.
     * @return The current state of the game to the game gui.
     */
    public String checkBJorBust() {
        if (21 > getTotalUserHandValue()) {
            userWin = false;
            return "notOver";
        } else if (21 < getTotalUserHandValue()) {
            dealerWin = true;
            return "bust";
        } else if (getTotalUserHandValue() == 21) {
            dealerStand();
            return "Bj";
        }
        return null;
    }

    /**
     *When the stand button is pressed, this method will call the remaining cards that the dealer must have.
     */
    public void dealerStand() {
        while (getTotalDealerHandValue() <= 17) {
            userTurn = false;
            setCard();
            checkDouble();
            addToHand();
        }
    }

    /**
     * Resets game back to start, clears arrays.
     */
    public void resetGame(){
        userHand.clear();
        dealerHand.clear();
        userHandValue.clear();
        dealerHandValue.clear();
        cardUsed.clear();
    }

    /**
     *Draws the dealers 2 start cards
     */
    public void drawDealerStartHand() {
        userTurn = false;
        setCard();
        checkDouble();
        addToHand();
        setCard();
        checkDouble();
        addToHand();
    }

    /**
     * Draws the user's 2 start cards
     */
    public void drawUserStartHand() {
        userTurn = true;
        setCard();
        checkDouble();
        addToHand();
        setCard();
        checkDouble();
        addToHand();
    }

}

