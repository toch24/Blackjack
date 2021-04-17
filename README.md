# BLACKJACK

Blackjack is a card game that pits player versus dealer. It is played with one or more decks of cards. Cards are counted as their respective numbers, face cards as ten, and ace as either eleven or one (in our game the ace will count as an 11 if the total in the hand is less than 21). The objective of Blackjack is to the beat the dealer. This can be accomplished by getting Blackjack (first two cards equal 21) without the dealer having Blackjack as well, having your final card count be higher than the dealers without exceeding 21, or by not exceeding 21 and dealer busting by exceeding their card count of 21.

# HOW TO PLAY
The game starts when a player determines a buy in value. When a play button is clicked, a pop up will show up asking the user to determine the buy in value. The buy in value must be equal to or greater than 100. This will be the starting value for all players except the dealer. The house, or the dealer in this case will have a starting pot value of 3 times the buy in value.
Once a buy in is successfully established, the dealer gives two cards to each player and all players have their cards facing up. The dealer has also two cards, difference is that the dealer's second card is facing down. To start the game, press the "Start Game" button. This will start the game, first turn is player 1, which is the player on the left of the table. Player 1 sets a bet and it will decide whether to hit or hold. Once player 1 has played, its the user's turn. A pop up will show up asking for the user's bet, once a bet is established, the user can then decide whether to hit or hold. Once the user has either played or busted, its player 3 turn. Player 3 will first bet according to how much it believes in its cards. Then, player 3 can either hit or hold. Once player 3 has played, the dealer plays. The dealer decides whether to hit or not, either way the dealer is going to show all his cards. If the dealer busts, everyone wins the round and the correct payout is given. If the dealer beats a player, it will take the player's bet. Else if a player beats the dealer, the player will get its original bet back plus the correct payout. Once the round is finished, the user has the option to click on next round or exit the game. If clicked on next round, a new round is going to start and the game will continue. The game can continue as long as the user wants or until the user's wallet reaches 0.

# PAYOUTS
This is standard blackjack with 3-2 payout
- If player gets natural blackjack, they will be paid $3 for every $2 they bet
- If player does not have natural black jack, and busts, they lose their bet
- If player does not have natural black jack, and does not bust, if they have a higher hand than the dealer at the end of round, dealer pays 2-1
- If player does not have natural blackjack, and does not bust,if they have a lower hand than dealer, they lose their bet
- If player does not have natural black jack,and does not bust, if they have an equal hand as the dealer, it is a push, and dealer pays even (they get their bet back and nothing else)

# IMPLEMENTATION
Code is implemented in an object oriented manner. For the backend of our game, we have the Deck, Hand, Card, Bot, Player and Pot. These objects represent the items we need for our Blackjack game. The main function is found in HomePage.java, this creates a JFrame with HomeJPanel embedded. The panel for home then has its respectives buttons, "Play Basic, "Rules", and "Play Casino". When clicked on "Play Basic", it opens a new JFrame with basicsPanel. When "Play Casino" is clicked, it opens a new JFrame with casinoPanel.
Blackjack.java is where the magic happens. The game is started whenever a new instance of Blackjack is called. A new instance of Blackjack is called in basicsPanel and casinoPanel.

- Bot:
Every player implemented is a bot except player 2, which is the user. Player 1 and Player 3 bet an amount according to how much money they have in their wallet. Both players are implemented in a way that they play it safe, that is, they will rather not risk betting a lot of money. They prefer to win slowly and steady rather than putting a high risk bet and risking losing it all.
Even though every player is a bot except player 2, the dealer is a bot but he is different than player 1 and player 3. The dealer does not have to bet, the dealer only decides whether to hit or hold. The dealer is implemented such that it must hit until the cards total up to the value 17. At 17 or higher, the dealer holds. 

- Card:
The card class has a default constructor that initializes the variables that hold the values for a card. It also has a constructor with parameters which sets the card values to the values given in the parameters. This class holds a single card.

- Deck:
The deck class initializes an array of cards with a size of 52. The constructor creates 52 cards of values from 1 to 13 with specs "Heart", "Spade", "Club", "Diamond". Class also has a shuffle function which shuffles the entire deck, this simulates the shuffling of cards in a real game.

- Hand:
The hand class creates an array of cards with size of 14, which is the maximum cards a hand can hold in the game. The hand class holds the cards for each player.

- Player:
The player class creates a new hand for the player, it holds the bets done by the player which is the user. It also holds every other information needed for the player.

- Pot:
The pot class sets the entire pot for the game. It has functions such as addToPot which adds bets to the total pot. The pot is the total amount of money that the house (the dealer) holds.

- Blackjack:
The Blackjack class initializes the game. This is where a simple algorithm is implemented for the bots and the payout system is implemented. This is the whole backend of our game.

- basicsPanel:
This class sets up the entire panel for the basic gameplay. This implementation does not include the casino rules and it utilizes the Blackjack class as the backend.

- casinoPanel:
This class sets up the entire panel for the casino gameplay. This implementation includes the casino rules insurance, double down, and surrender. It utilizes the Blackjack class as the backend.

- HomePage:
This is the main class where it creates a JFrame for the main program and the HomeJPanel is created and embedded on the frame.

- HomeJPanel:
This class creates the home panel which has the "Play Basic", "Rules", and "Play Casino" buttons. This is the main page for our game.

# CASINO RULES (This is our extra feature, we have a basic gameplay mode and casino gameplay mode)
Rules are the same as basics with added features of Insurance, Double-Down, and Surrender

Insurance: If Dealer has an Ace for face up card, player can choose to make a side bet, which is half of the original bet. Player must place a bet BEFORE choosing insurance option
-If dealer has natural black jack, player is paid 2 to 1 on insurance, and breaks even
-If dealer does not have natural black jack, player loses insurance bet, and hand continues as normal

Double-Down: The player is allowed to increase the initial bet up to 100% in exchange for committing to stand after receiving exactly one more card. The player can only double down when it’s their turn to bet and they only have their 2 initial cards. Payouts determined if a player wins or loses the round.

Surrender: If player surrenders the round they get half their bet back

# SEPARATION OF WORK BETWEEN PARTNERS
Overall we collectively worked on every part of the program together, once each class had been implemented. 

- Tommy: Initiated the backend of blackjack- classes Card, Deck, Hand

- Andrew: Initiated the backend of blackjack- classes Bot, Blackjack, Player, Pot

Both Andrew and Tommy worked on initiating all the proper methods and data needed for the classes mentioned above

- Oksana: Initiated the fronted of blackjack- welcome page(HomePage and HomeJPanel) and basicsPanel, where the play of basics happens

From there, we all worked as a team to implement all features from each class, pushing our changes to the GitHub repository, and eventually implementing the casino version together.



