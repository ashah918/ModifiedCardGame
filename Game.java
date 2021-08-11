import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * This class creates a new game that runs with various inner classes to Start,
 * Replace and show Result.
 *
 * The game stimulates the player to input a bet amount and click start upon
 * which the dealer deals 3 cards from the shuffled deck. The player has a
 * chance to at most replace 2 of his cards, where each card can only be
 * replaced once. If the dealer has better cards, they win (player loses bet
 * amount) and if the player has better cards, the player wins (receives bet
 * amount).
 *
 * @author aashanashah
 * @version 1.1
 */

public class Game {

	JLabel label_Image1 = new JLabel();
	JLabel label_Image2 = new JLabel();
	JLabel label_Image3 = new JLabel();
	JLabel label_Image4 = new JLabel();
	JLabel label_Image5 = new JLabel();
	JLabel label_Image6 = new JLabel();

	ImageIcon Image1 = new ImageIcon("card_back.gif");
	ImageIcon Image2 = new ImageIcon("card_back.gif");
	ImageIcon Image3 = new ImageIcon("card_back.gif");
	ImageIcon Image4 = new ImageIcon("card_back.gif");
	ImageIcon Image5 = new ImageIcon("card_back.gif");
	ImageIcon Image6 = new ImageIcon("card_back.gif");

	JTextField txt_inputbet = new JTextField(10);

	JButton btn_rpcard1 = new JButton("Replace Card 1");
	JButton btn_rpcard2 = new JButton("Replace Card 2");
	JButton btn_rpcard3 = new JButton("Replace Card 3");
	JButton btn_start = new JButton("Start");
	JButton btn_result = new JButton("Result");

	int count = 0;
	int total = 100;

	JLabel label_bet = new JLabel("Bet: $");
	JLabel label_info = new JLabel("Amount of money you have: $" + total);
	JLabel label_money = new JLabel("Please place your bet!");

	ArrayList<ImageIcon> Cards = new ArrayList<ImageIcon>();

	/**
	 * Create and initialize a new game g
	 *
	 * @param args Unused.
	 */
	public static void main(String[] args) {
		Game g = new Game();
		g.go();
	}

	/**
	 * Initializes panel and frame. Adds different components to the panel. Add
	 * menubar to frame.
	 */
	public void go() {

		btn_start.setEnabled(true);
		btn_result.setEnabled(false);
		btn_rpcard1.setEnabled(false);
		btn_rpcard2.setEnabled(false);
		btn_rpcard3.setEnabled(false);

		label_Image1.setIcon(Image1);
		label_Image2.setIcon(Image2);
		label_Image3.setIcon(Image3);
		label_Image4.setIcon(Image4);
		label_Image5.setIcon(Image5);
		label_Image6.setIcon(Image6);

		// Initialise panels
		JPanel MainPanel = new JPanel();
		JPanel DealerPanel = new JPanel();
		JPanel PlayerPanel = new JPanel();
		JPanel RpCardBtnPanel = new JPanel();
		JPanel ButtonPanel = new JPanel();
		JPanel InfoPanel = new JPanel();

		// Add button, labels and textFields to panels
		DealerPanel.add(label_Image1);
		DealerPanel.add(label_Image2);
		DealerPanel.add(label_Image3);
		PlayerPanel.add(label_Image4);
		PlayerPanel.add(label_Image5);
		PlayerPanel.add(label_Image6);
		RpCardBtnPanel.add(btn_rpcard1);
		RpCardBtnPanel.add(btn_rpcard2);
		RpCardBtnPanel.add(btn_rpcard3);
		ButtonPanel.add(label_bet);
		ButtonPanel.add(txt_inputbet);
		ButtonPanel.add(btn_start);
		ButtonPanel.add(btn_result);
		InfoPanel.add(label_money);
		InfoPanel.add(label_info);

		// Add panels to main panel
		MainPanel.setLayout(new GridLayout(5, 1));
		MainPanel.add(DealerPanel);
		MainPanel.add(PlayerPanel);
		MainPanel.add(RpCardBtnPanel);
		MainPanel.add(ButtonPanel);
		MainPanel.add(InfoPanel);

		DealerPanel.setBackground(Color.green);
		PlayerPanel.setBackground(Color.green);
		RpCardBtnPanel.setBackground(Color.green);

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(MainPanel);

		// Menubar
		JMenuBar menuBar = new JMenuBar();
		JMenu menuC = new JMenu("Control");
		JMenu menuH = new JMenu("Help");
		JMenuItem mC = new JMenuItem("Exit");
		JMenuItem mH = new JMenuItem("Instruction");
		mC.addActionListener(new Quit());
		mH.addActionListener(new Help());
		menuC.add(mC);
		menuH.add(mH);
		menuBar.add(menuC);
		menuBar.add(menuH);
		frame.setJMenuBar(menuBar);

		frame.setTitle("A Simple Card Game");
		frame.setSize(450, 700);
		frame.setVisible(true);

		// Add all cards to deck
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 13; j++) {
				Cards.add(new ImageIcon("card_" + i + j + ".gif"));
			}
		}

		// addActionListener to button
		btn_start.addActionListener(new Start());
		btn_result.addActionListener(new Results());
		btn_rpcard1.addActionListener(new Replace1());
		btn_rpcard2.addActionListener(new Replace2());
		btn_rpcard3.addActionListener(new Replace3());

	}

	/**
	 * Shuffles deck and deals cards, gives Warning message when bet is too high or
	 * not positive integer.
	 *
	 * @author aashanashah
	 */
	class Start implements ActionListener {
		/**
		 * @param e.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			double d = Double.parseDouble(txt_inputbet.getText());
			int in = (int) d;
			count = 0;
			// Check if player has bet amount
			if (total - in < 0) {
				String s = "WARNING: You only have $" + total;
				JOptionPane.showMessageDialog(new JFrame(), s, "Message", JOptionPane.INFORMATION_MESSAGE);
			} else {
				// Check that bet is positive integer
				if (!(txt_inputbet.getText().isEmpty()) && in > 0 && d == in) {
					// Shuffle cards
					for (int i = 0; i < Cards.size(); i++) {
						int index = (int) (Math.random() * Cards.size());
						ImageIcon temp = Cards.get(i);
						Cards.set(i, Cards.get(index));
						Cards.set(index, temp);
					}

					Image4 = Cards.get(0);
					Image5 = Cards.get(1);
					Image6 = Cards.get(2);
					label_Image4.setIcon(Image4);
					label_Image5.setIcon(Image5);
					label_Image6.setIcon(Image6);
					Image1 = Cards.get(3);
					Image2 = Cards.get(4);
					Image3 = Cards.get(5);

					label_money.setText("Your current bet is: $" + txt_inputbet.getText());

					btn_start.setEnabled(false);
					btn_result.setEnabled(true);
					btn_rpcard1.setEnabled(true);
					btn_rpcard2.setEnabled(true);
					btn_rpcard3.setEnabled(true);
				} else {
					String s = "WARNING: The bet you placed must be a positive integer!";
					JOptionPane.showMessageDialog(new JFrame(), s, "Message", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}

	/**
	 * Checks who wins by looking at special cards, sum value % 10
	 *
	 * @author aashanashah
	 */
	class Results implements ActionListener {
		/**
		 * @param e.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			int lenofBet = txt_inputbet.getText().length();
			int sum = 0;
			int specialP = 0;
			int specialD = 0;
			int sumP = 0;
			int sumD = 0;
			JLabel playercards[] = { label_Image4, label_Image5, label_Image6 };
			label_Image1.setIcon(Image1);
			label_Image2.setIcon(Image2);
			label_Image3.setIcon(Image3);
			JLabel dealercards[] = { label_Image1, label_Image2, label_Image3 };
			// If special card, add to specialP/specialD, else value to sumP/sumD
			for (int i = 0; i < 3; i++) {
				String x = (playercards[i].getIcon()).toString();
				if (x.charAt(7) == '1' || x.charAt(7) == '2' || x.charAt(7) == '3') {
					specialP += 1;
				} else {
					if (x.charAt(7) == '.') {
						sumP += (int) x.charAt(6) - 48;
					} else {
						sumP += 10;
					}
				}
			}
			for (int i = 0; i < 3; i++) {
				String x = (dealercards[i].getIcon()).toString();
				if (x.charAt(7) == '1' || x.charAt(7) == '2' || x.charAt(7) == '3') {
					specialD += 1;
				} else {
					if (x.charAt(7) == '.') {
						sumD += (int) x.charAt(6) - 48;
					} else {
						sumD += 10;
					}
				}
			}

			for (int i = 0; i < lenofBet; ++i) {
				sum *= 10;
				sum += (int) txt_inputbet.getText().charAt(i) - 48;
			}

			if (specialP > specialD) {
				String s = "Congratulations! You win this round!";
				JOptionPane.showMessageDialog(new JFrame(), s, "Message", JOptionPane.INFORMATION_MESSAGE);
				total += sum;
				label_info.setText("Amount of money you have: $" + total);
			} else if (specialP == specialD) {
				if (sumP % 10 > sumD % 10) {
					String s = "Congratulations! You win this round!";
					JOptionPane.showMessageDialog(new JFrame(), s, "Message", JOptionPane.INFORMATION_MESSAGE);
					total += sum;
					label_info.setText("Amount of money you have: $" + total);
				} else if (sumP % 10 < sumD % 10) {
					String s = "Sorry! The Dealer wins this round!";
					JOptionPane.showMessageDialog(new JFrame(), s, "Message", JOptionPane.INFORMATION_MESSAGE);
					total -= sum;
					label_info.setText("Amount of money you have: $" + total);
				}
			} else {
				String s = "Sorry! The Dealer wins this round!";
				JOptionPane.showMessageDialog(new JFrame(), s, "Message", JOptionPane.INFORMATION_MESSAGE);
				total -= sum;
				label_info.setText("Amount of money you have: $" + total);
			}

			if (total == 0) {
				String s = "Game over!\nYou have no more money!\nPlease start a new game!";
				JOptionPane.showMessageDialog(new JFrame(), s, "Message", JOptionPane.INFORMATION_MESSAGE);
				btn_start.setEnabled(false);
				btn_result.setEnabled(false);
				btn_rpcard1.setEnabled(false);
				btn_rpcard2.setEnabled(false);
				btn_rpcard3.setEnabled(false);
				label_info.setText("Please start a new game!");
				label_money.setText("You have no more money!");
			} else {
				btn_start.setEnabled(true);
				btn_result.setEnabled(false);
				btn_rpcard1.setEnabled(false);
				btn_rpcard2.setEnabled(false);
				btn_rpcard3.setEnabled(false);
				ImageIcon Image1 = new ImageIcon("card_back.gif");
				ImageIcon Image2 = new ImageIcon("card_back.gif");
				ImageIcon Image3 = new ImageIcon("card_back.gif");
				ImageIcon Image4 = new ImageIcon("card_back.gif");
				ImageIcon Image5 = new ImageIcon("card_back.gif");
				ImageIcon Image6 = new ImageIcon("card_back.gif");
				label_Image1.setIcon(Image1);
				label_Image2.setIcon(Image2);
				label_Image3.setIcon(Image3);
				label_Image4.setIcon(Image4);
				label_Image5.setIcon(Image5);
				label_Image6.setIcon(Image6);
				label_money.setText("Please place your bet!");
			}
		}
	}

	/**
	 * Replaces player's card 1 with another card from deck
	 *
	 * @author aashanashah
	 */
	class Replace1 implements ActionListener {
		/**
		 * @param e.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (btn_rpcard2.isEnabled() == false && btn_rpcard3.isEnabled() == false) {
				btn_rpcard1.setEnabled(false);
			} else {
				Image4 = Cards.get(6);
				label_Image4.setIcon(Image4);
				btn_rpcard1.setEnabled(false);
				count += 1;
				if (count == 2) {
					btn_rpcard2.setEnabled(false);
					btn_rpcard3.setEnabled(false);
				}
			}
		}
	}

	/**
	 * Replaces player's card 2 with another card from deck
	 *
	 * @author aashanashah
	 */
	class Replace2 implements ActionListener {
		/**
		 * @param e.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (btn_rpcard1.isEnabled() == false && btn_rpcard3.isEnabled() == false) {
				btn_rpcard2.setEnabled(false);
			} else {
				Image5 = Cards.get(7);
				label_Image5.setIcon(Image5);
				btn_rpcard2.setEnabled(false);
				count += 1;
				if (count == 2) {
					btn_rpcard1.setEnabled(false);
					btn_rpcard3.setEnabled(false);
				}
			}
		}
	}

	/**
	 * Replaces player's card 3 with another card from deck
	 *
	 * @author aashanashah
	 */
	class Replace3 implements ActionListener {
		/**
		 * @param e.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (btn_rpcard1.isEnabled() == false && btn_rpcard2.isEnabled() == false) {
				btn_rpcard3.setEnabled(false);
			} else {
				Image6 = Cards.get(8);
				label_Image6.setIcon(Image6);
				btn_rpcard3.setEnabled(false);
				count += 1;
				if (count == 2) {
					btn_rpcard1.setEnabled(false);
					btn_rpcard2.setEnabled(false);
				}
			}
		}
	}

	/**
	 * Quits system when exit is clicked
	 *
	 * @author aashanashah
	 */
	class Quit implements ActionListener {
		/**
		 * @param e.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	/**
	 * Displays instructions when instructions is clicked
	 *
	 * @author aashanashah
	 */
	class Help implements ActionListener {
		/**
		 * @param e.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String s = "Rules to determine who has better cards:" + "\n" + " J, Q, K are regarded as special cards."
					+ "\n" + "Rule 1: The one with more special cards wins." + "\n"
					+ "Rule 2: If both have the same number of special cards, add the face values of the other card(s)  and  take  the  remainder  after  dividing  the  sum  by  10.  The  one  with  a  bigger remainder wins. (Note: Ace = 1)."
					+ "\n" + "Rule 3: The dealer wins if both rule 1 and rule 2 cannot distinguish the winner.";
			JOptionPane.showMessageDialog(new JFrame(), s, "Message", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
