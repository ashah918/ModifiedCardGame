# ModifiedCardGame
This is a simple card game that implements the graphical user interface. The computer starts by asking the user how much they want to bet. The user initially starts off with $100 in their bank. 

When the game begins, 52 cards in the deck are shuffled and the player and dealer are given 3 cards each from the top of the deck. When the player places their bet, they will then be allowed to replace at most 2 cards in their hand from the top of the deck. Replacements of a individual card can only happen once. The dealer then reveals his cards. If the dealer has a better hand, the player loses the bet and the money. If the player has a better hand, they win the same amount of money as their bet. 

To win the game:
1) The player with the most number of special cards wins (special cards are J, Q, K)
2) If both players have the same number of special cars, the winner would be the player with the highest value after taking the sum of the face values of the remaining cards mod 10/
3) If the winner cannot be distinguished by rule 1 or 2, the dealer wins.
