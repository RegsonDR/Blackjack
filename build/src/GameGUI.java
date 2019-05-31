/**
 *@author Regson Do Rego
 *@version v2
 *Date: 27/04/2016
 *Description: This class is the game's GUI. It creates the whole user interface.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GameGUI extends GameLogic{

	private JFrame frame ;
	private JPanel panel, leftPanel, rightPanel, resultPanel, betPanel;
	private JLabel userHandTotalLabel, dealerHandTotalLabel, lblPicture, hidePicture, result, userScore;
	private JButton drawCardButton, standMoveButton,newGameButton;
	private JRadioButton betOn;
	private ImageIcon image;
	private JSpinner userBet;
	private Image rescaleImage;
	private int userScoreValue;

	/**
	 *Puts the whole GUI together, runs all the indiviviual methods.
	 */
	private GameGUI() {

		createForm();
		betGame();
		addButtons();
		setBackground();
		drawUserStartHand();
		drawDealerStartHand();
		addLabels();
		drawStartHands();

		frame.add(panel);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/**
	 *Sets the background for the application
	 */
	private void setBackground() {
		Color bgLeft = new Color(46, 204, 113);
		Color bgRight = new Color(41, 128, 185);
		Color buttons = new Color(44, 62, 80);
		Color bgResult = new Color(192, 57, 43, 230);
		Color bgBetPanel = new Color(149, 165, 166);

		leftPanel.setBackground(bgLeft);
		rightPanel.setBackground(bgRight);
		rightPanel.setBorder(BorderFactory.createLineBorder(buttons));
		drawCardButton.setForeground(buttons);
		standMoveButton.setForeground(buttons);
		resultPanel.setBackground(bgResult);
		betPanel.setBackground(bgBetPanel);
		betPanel.setBorder(BorderFactory.createLineBorder(buttons));
	}

	/**
	 * Creates the betting part of the game. Actionlisteners are used to check when the radio buttons have been clicked on.
	 */
	private void betGame(){
		betPanel = new JPanel();
		betPanel.setLayout(null);
		betPanel.setBounds(75, 235, 150, 170);

		JLabel bettingLabel = new JLabel("Betting: ");
		bettingLabel.setBounds(35,10,100,25);
		bettingLabel.setFont(bettingLabel.getFont().deriveFont(20.0f));
		Color labelText = new Color(44, 62, 80);
		bettingLabel.setForeground(labelText);

		JLabel yourBetLabel = new JLabel("Your bet: ");
		yourBetLabel.setBounds(40,100,100,25);
		yourBetLabel.setFont(yourBetLabel.getFont().deriveFont(15.0f));
		yourBetLabel.setForeground(labelText);
		betPanel.add(bettingLabel);

		betOn = new JRadioButton("ON", false);
		JRadioButton betOff = new JRadioButton("OFF",true);
		betOn.setBounds(20, 40, 50, 20);
		betOff.setBounds(80, 40, 50, 20);
		betOn.setOpaque(false);
		betOff.setOpaque(false);
		ButtonGroup bettingOption = new ButtonGroup();
		bettingOption.add(betOn);
		bettingOption.add(betOff);
		betPanel.add(betOn);
		betPanel.add(betOff);

		userScore = new JLabel("Bank: £"+userScoreValue,SwingConstants.CENTER);
		userScore.setForeground(Color.red);
		userScore.setBounds(0, 0, 150, 170);
		userScore.setFont(userScore.getFont().deriveFont(15.0f));
		userScoreValue=1000;

		SpinnerModel model = new SpinnerNumberModel(500,1,userScoreValue,1);
		userBet = new JSpinner(model);
		userBet.setBounds(10,130,130,30);
		userBet.setFont(userBet.getFont().deriveFont(14.0f));
		userBet.setVisible(false);

		betOn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JRadioButton source =(JRadioButton)e.getSource();
				if (source.isSelected()){
					betPanel.add(userScore, BorderLayout.CENTER);
					betPanel.add(userBet);
					betPanel.add(yourBetLabel);
					newGameButton.setEnabled(false);
					userBet.setVisible(true);
				}
				panel.repaint();
			}

		});

		betOff.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JRadioButton source =(JRadioButton)e.getSource();
				if (source.isSelected()){
					betPanel.remove(userScore);
					betPanel.remove(userBet);
					betPanel.remove(yourBetLabel);
					userBet.setVisible(false);
				}
				panel.repaint();
			}

		});
		rightPanel.add(betPanel);

	}

	/**
	 *Creates the main form which everything will run on. Panels are also created here.
	 */
	private void createForm() {
		frame = new JFrame();
		frame.setTitle("Blackjack");
		frame.setSize(1100, 700);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);

		ImageIcon titleBarIcon = new ImageIcon(getClass().getResource("CardImages/gameIcon.png"));
		frame.setIconImage(titleBarIcon.getImage());

		panel = new JPanel();
		panel.setLayout(null);

		leftPanel = new JPanel();
		leftPanel.setLayout(null);
		leftPanel.setBounds(0, 0, 800, 700);
		panel.add(leftPanel);

		rightPanel = new JPanel();
		rightPanel.setLayout(null);
		rightPanel.setBounds(800, 0, 300, 700);
		panel.add(rightPanel);

		resultPanel = new JPanel();
		resultPanel.setLayout(new BorderLayout());
		resultPanel.setBounds(0, 218, 1100, 200);
	}

	/**
	 * Creates the rules form
	 */
	private void createRulesForm(){
		JFrame rulesFrame = new JFrame();
		rulesFrame.setTitle("Blackjack - Rules");
		rulesFrame.setSize(600, 500);
		rulesFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		rulesFrame.setResizable(false);

		ImageIcon titleBarIcon = new ImageIcon(getClass().getResource("CardImages/gameIcon.png"));
		rulesFrame.setIconImage(titleBarIcon.getImage());

		JPanel rulesPanel= new JPanel();
		rulesPanel.setLayout(null);

		rulesFrame.add(rulesPanel);
		rulesFrame.setLocationRelativeTo(null);
		rulesFrame.setVisible(true);

		Color bgRules = new Color(155, 89, 182);
		rulesPanel.setBackground(bgRules);

		JLabel rulesLabel = new JLabel("<html><p style='font-size:10px'>You must try to beat the dealer by getting as close to 21 as possible, without going over 21. An ace is worth 1, face cards are 10 and any other card is it's stated value. <br><br>If you choose to 'Hit' move, another card will be added into your hand. If you choose to 'stand', the dealer must keep drawing cards till his hand is over 17. You may generate a new hand as many times as you want by clicking on the 'New Hand' button to practice easier or harder hands.<br><br> If you feel confident with your skills, you can turn on 'betting' mode. When you turn this mode on, you won't be able to generate a new hand till the current hand is played, the dealer will bet the same amount of cash as you and you will receive it each time you win. The game ends once he takes all your cash (but don't worry you can start a new game and try again), try to get a bigger bank than your friends! You can turn this mode off and when you turn it on again it will continue from the previous bank amount.</p></html>");
		rulesLabel.setBounds(275,0,305,470);
		rulesPanel.add(rulesLabel);
		setCard();
		image = new ImageIcon(getClass().getResource("CardImages/" + getCard() + ".png"));
		rescaleImage = image.getImage().getScaledInstance(230, 320, Image.SCALE_DEFAULT);
		image = new ImageIcon(rescaleImage);
		lblPicture = new JLabel(image);

		lblPicture.setBounds(20, 60, 230, 320);
		rulesPanel.add(lblPicture);
	}

	/**
	 * Adds the labels
	 */
	private void addLabels() {

		userHandTotalLabel = new JLabel("Your total hand value: " + getTotalUserHandValue());
		userHandTotalLabel.setBounds(280, 350, 400, 50);
		userHandTotalLabel.setFont(userHandTotalLabel.getFont().deriveFont(30.0f));
		leftPanel.add(userHandTotalLabel);

		dealerHandTotalLabel = new JLabel("Dealer total hand value: " + dealerHandValue.get(0) + " + ??");
		dealerHandTotalLabel.setBounds(270, 230, 450, 50);
		dealerHandTotalLabel.setFont(userHandTotalLabel.getFont().deriveFont(30.0f));
		leftPanel.add(dealerHandTotalLabel);

		result = new JLabel("", SwingConstants.CENTER);
		result.setBounds(50, 50, 100, 100);
		resultPanel.add(result, BorderLayout.CENTER);

	}

	/**
	 * Adds the buttons and actionlisteners for them
	 */
	private void addButtons() {
		drawCardButton = new JButton("Hit");
		drawCardButton.setBounds(50, 450, 200, 70);
		drawCardButton.setFont(drawCardButton.getFont().deriveFont(25.0f));
		drawCardButton.addActionListener(new drawCardHandler());
		rightPanel.add(drawCardButton);

		standMoveButton = new JButton("Stand");
		standMoveButton.setBounds(50, 540, 200, 70);
		standMoveButton.setFont(standMoveButton.getFont().deriveFont(25.0f));
		standMoveButton.addActionListener(new standMoveHandler());
		rightPanel.add(standMoveButton);

		newGameButton = new JButton("New Hand");
		newGameButton.setBounds(50, 120, 200, 70);
		newGameButton.setFont(newGameButton.getFont().deriveFont(25.0f));
		newGameButton.addActionListener(new newGameHandler());
		rightPanel.add(newGameButton);

		JButton rulesButton = new JButton("Rules");
		rulesButton.setBounds(50, 30, 200, 70);
		rulesButton.setFont(rulesButton.getFont().deriveFont(25.0f));
		rulesButton.addActionListener(new rulesHandler());
		rightPanel.add(rulesButton);
	}

	/**
	 *Draws the dealer's and the user's start hand. 1 of the Dealers card must be hidden.
	 */
	private void drawStartHands() {
		hideCard();
		for (int i = 0; i < userHand.size(); i++) {
			image = new ImageIcon(getClass().getResource("CardImages/" + userHand.get(i) + ".png"));
			rescaleImage = image.getImage().getScaledInstance(120, 170, Image.SCALE_DEFAULT);
			image = new ImageIcon(rescaleImage);
			lblPicture = new JLabel(image);

			lblPicture.setBounds(25 + (i * 125), 430, 120, 170);
			leftPanel.add(lblPicture);
		}
		for (int i = 0; i < dealerHand.size(); i++) {
			image = new ImageIcon(getClass().getResource("CardImages/" + dealerHand.get(i) + ".png"));
			rescaleImage = image.getImage().getScaledInstance(120, 170, Image.SCALE_DEFAULT);
			image = new ImageIcon(rescaleImage);
			lblPicture = new JLabel(image);

			lblPicture.setBounds(25 + (i * 125), 20, 120, 170);
			leftPanel.add(lblPicture);
		}
	}

	/**
	 *Updates the users hand, adds another card and places the image of cards in the correct place on the panel. A formula is used to find the location.
	 */
	private void updateUserHand() {
		userTurn = true;
		setCard();
		checkDouble();
		addToHand();

		int current = userHand.size() - 1;
		int yPlacer = 0;
		int xPlacer =0;
		if (current>=6){
			yPlacer=1;
			xPlacer=750;
		}

		image = new ImageIcon(getClass().getResource("CardImages/" + userHand.get(current) + ".png"));
		rescaleImage = image.getImage().getScaledInstance(120, 170, Image.SCALE_DEFAULT);
		image = new ImageIcon(rescaleImage);
		lblPicture = new JLabel(image);

		lblPicture.setBounds(25 + (current * 125)-xPlacer, 430+(yPlacer*50), 120, 170);
		leftPanel.add(lblPicture);
		if (current>=6){
			leftPanel.setComponentZOrder(lblPicture,0);
		}



	}

	/**
	 *Updates the Dealers hand, places the image of cards in the correct place on the panel. A formula is used to find the location.
	 */
	private void updateDealerHand() {
		int yPlacer = 0;
		int xPlacer =0;

		for (int i = 0; i < dealerHand.size(); i++) {
			image = new ImageIcon(getClass().getResource("CardImages/" + dealerHand.get(i) + ".png"));
			rescaleImage = image.getImage().getScaledInstance(120, 170, Image.SCALE_DEFAULT);
			image = new ImageIcon(rescaleImage);
			lblPicture = new JLabel(image);
			if (i>=6){
				yPlacer=1;
				xPlacer=750;
			}
			lblPicture.setBounds(25 + (i * 125)-xPlacer, 20+(yPlacer*50), 120, 170);
			leftPanel.add(lblPicture);
			if (i>=6){
				leftPanel.setComponentZOrder(lblPicture,0);
			}
		}


	}

	/**
	 * Hides 1 of the dealer's card
	 */
	private void hideCard() {
		image = new ImageIcon(getClass().getResource("CardImages/card_back.png"));
		rescaleImage = image.getImage().getScaledInstance(120, 170, Image.SCALE_DEFAULT);
		image = new ImageIcon(rescaleImage);
		hidePicture = new JLabel(image);

		hidePicture.setBounds(150, 20, 120, 170);
		leftPanel.add(hidePicture);
	}

	/**
	 *When the hit button is pressed, these actions are done.
	 */
	private class drawCardHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			updateUserHand();
			userHandTotalLabel.setText("Your total hand value: " + getTotalUserHandValue());
			if (checkBJorBust().equals("Bj")){
				leftPanel.remove(hidePicture);
				dealerStand();
				updateDealerHand();
				dealerHandTotalLabel.setText("Dealer total hand value: "+getTotalDealerHandValue());
				checkPush();
				if (dealerWin){
					result.setText("Push - Both players achieved blackjack");
					result.setFont(result.getFont().deriveFont(50.0f));
					drawCardButton.setEnabled(false);
					standMoveButton.setEnabled(false);
					panel.add(resultPanel);
					panel.setComponentZOrder(resultPanel,0);
					newGameButton.setEnabled(true);
				} else {
					result.setText("You win! - You have achieved blackjack");
					drawCardButton.setEnabled(false);
					standMoveButton.setEnabled(false);
					result.setFont(result.getFont().deriveFont(48.0f));
					panel.add(resultPanel);
					panel.setComponentZOrder(resultPanel,0);
					newGameButton.setEnabled(true);
					if (newGameButton.isSelected()==false){
						userScoreValue=userScoreValue+Integer.parseInt(userBet.getValue().toString());
						userScore.setText("Bank: £"+userScoreValue);
					}
				}
			} else if (checkBJorBust().equals("bust")){
				result.setText("You Lose! - You went over 21");
				result.setFont(result.getFont().deriveFont(50.0f));
				drawCardButton.setEnabled(false);
				standMoveButton.setEnabled(false);
				leftPanel.remove(hidePicture);
				dealerHandTotalLabel.setText("Dealer total hand value: "+getTotalDealerHandValue());
				panel.add(resultPanel);
				panel.setComponentZOrder(resultPanel,0);
				newGameButton.setEnabled(true);
				if (newGameButton.isSelected()==false){
					userScoreValue=userScoreValue-Integer.parseInt(userBet.getValue().toString());
					userScore.setText("Bank: £"+userScoreValue);
					if(userScoreValue<=0){
						result.setText("Game over! You lost all cash!");
						result.setFont(result.getFont().deriveFont(50.0f));
						drawCardButton.setEnabled(false);
						standMoveButton.setEnabled(false);
						leftPanel.remove(hidePicture);
						dealerHandTotalLabel.setText("Dealer total hand value: "+getTotalDealerHandValue());
						panel.add(resultPanel);
						panel.setComponentZOrder(resultPanel,0);
						newGameButton.setText("New Game");
						userScoreValue=1000;
					}
				}
			}

			panel.repaint();
		}
	}

	/**
	 * When the stand button is pressed, these actions are done.
	 */
	private class standMoveHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			leftPanel.remove(hidePicture);
			dealerStand();
			updateDealerHand();
			dealerHandTotalLabel.setText("Dealer total hand value: " + getTotalDealerHandValue());
			checkWin();
			if (dealerWin&&userWin){
				result.setText("Push - Both players achieved same score");
				result.setFont(result.getFont().deriveFont(50.0f));
				drawCardButton.setEnabled(false);
				standMoveButton.setEnabled(false);
				newGameButton.setEnabled(true);
				panel.add(resultPanel);
				panel.setComponentZOrder(resultPanel,0);
			}  else if (dealerWin){
				result.setText("You lose! - Dealer has higher hand");
				result.setFont(result.getFont().deriveFont(50.0f));
				drawCardButton.setEnabled(false);
				standMoveButton.setEnabled(false);
				newGameButton.setEnabled(true);
				panel.add(resultPanel);
				panel.setComponentZOrder(resultPanel,0);
				if (newGameButton.isSelected()==false){
					userScoreValue=userScoreValue-Integer.parseInt(userBet.getValue().toString());
					userScore.setText("Bank: £"+userScoreValue);
					if(userScoreValue<=0){
						result.setText("Game over! You lost all cash!");
						result.setFont(result.getFont().deriveFont(50.0f));
						drawCardButton.setEnabled(false);
						standMoveButton.setEnabled(false);
						leftPanel.remove(hidePicture);
						dealerHandTotalLabel.setText("Dealer total hand value: "+getTotalDealerHandValue());
						panel.add(resultPanel);
						panel.setComponentZOrder(resultPanel,0);
						newGameButton.setText("New Game");
						userScoreValue=1000;
					}
				}
			}else if (dealerBJ){
				result.setText("You lose! - Dealer has blackjack");
				result.setFont(result.getFont().deriveFont(50.0f));
				drawCardButton.setEnabled(false);
				standMoveButton.setEnabled(false);
				panel.add(resultPanel);
				newGameButton.setEnabled(true);
				panel.setComponentZOrder(resultPanel,0);
				if (newGameButton.isSelected()==false){
					userScoreValue=userScoreValue-Integer.parseInt(userBet.getValue().toString());
					userScore.setText("Bank: £"+userScoreValue);
					if(userScoreValue<=0){
						result.setText("Game over! You lost all cash!");
						result.setFont(result.getFont().deriveFont(50.0f));
						drawCardButton.setEnabled(false);
						standMoveButton.setEnabled(false);
						leftPanel.remove(hidePicture);
						dealerHandTotalLabel.setText("Dealer total hand value: "+getTotalDealerHandValue());
						panel.add(resultPanel);
						panel.setComponentZOrder(resultPanel,0);
						newGameButton.setText("New Game");
						userScoreValue=1000;
					}
				}
			}else if (dealerLower){
				result.setText("You Win! - You have the higher hand");
				result.setFont(result.getFont().deriveFont(50.0f));
				drawCardButton.setEnabled(false);
				standMoveButton.setEnabled(false);
				newGameButton.setEnabled(true);
				panel.add(resultPanel);
				panel.setComponentZOrder(resultPanel,0);
				if (newGameButton.isSelected()==false){
					userScoreValue=userScoreValue+Integer.parseInt(userBet.getValue().toString());
					userScore.setText("Bank: £"+userScoreValue);
				}
			} else {
				result.setText("You Win! - Dealer has gone bust");
				result.setFont(result.getFont().deriveFont(50.0f));
				drawCardButton.setEnabled(false);
				standMoveButton.setEnabled(false);
				newGameButton.setEnabled(true);
				panel.add(resultPanel);
				panel.setComponentZOrder(resultPanel,0);
				if (newGameButton.isSelected()==false){
					userScoreValue=userScoreValue+Integer.parseInt(userBet.getValue().toString());
					userScore.setText("Bank: £"+userScoreValue);
				}
			}

			panel.repaint();
		}
	}

	/**
	 *When the new hand/new game button is pressed, everything that was altered in the program is back to it's default.
	 */
	private class newGameHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			resetGame();
			userScore.setText("Bank: £"+userScoreValue);
			newGameButton.setText("New Hand");
			userWin =false;
			dealerWin =false;
			dealerBJ=false;
			userTurn = true;
			leftPanel.removeAll();
			panel.remove(resultPanel);
			resultPanel.removeAll();
			drawCardButton.setEnabled(true);
			standMoveButton.setEnabled(true);
			drawUserStartHand();
			drawDealerStartHand();
			addLabels();
			drawStartHands();
			panel.repaint();
		}
	}

	/**
	 * Creates the rules form once the button is pressed
	 */
	private class rulesHandler implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			createRulesForm();
		}
	}

	/**
	 * Runs the whole program
	 */
	public static void main(String[] args) {
		new GameGUI();
	}
}