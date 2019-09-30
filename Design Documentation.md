#### Design Documentation

Author: Lijun Chen, Jiatong Hao, Xiankang Wu

Date: 9/30/2019

Part I: General design

Part II: OOP principles in the design



I. General design

![](/Users/lenkahao/Desktop/Fall 2019/CS591 OOD/Assignment3 - Blackjack/Blackjack - OO Design/UML.jpeg)

Class GameLauncher
A generic entrance class that initiates a card game and starts it

Class Game
A generic class that represents a card game

Interface BlackjackActions
An interface that defines behaviors of Blackjack game participants (dealer and player)

Class BlackjackGame extends Game implements BlackjackActions
A class that represents a Blackjack game: parameters, participants, game process and game behaviors

Class Card
A generic class to represent a poker card in any card game

Class BlackjackCard extends Card
A class to represent a poker card in a Blackjack game

Class BlackjackAceCard extends BlackjackCard
A class to represent an Ace card in a Blackjack game

Class BlackjackFaceCard extends BlackjackCard
A class to represent a face card in a Blackjack game

Class Deck
A generic class to represent a deck of poker cards

Class BlackjackDeck extends Deck
A class to represent a deck of Blackjack cards

Class Hand
A generic class to represent a hand of poker cards in all card games

Class BlackjackHand extends Hand
A class to represent a hand of Blackjack cards in a Blackjack game

Class Player
A generic class that represent a player in a card game

Class BlackjackPlayer extends Player
A class that represents a player in a Blackjack game

Class BlackjackDealer extends Player
A class that represents a dealer in a Blackjack game

Class Judge
A generic class that checks winning and initiate winning/losing actions in a card game

Class BlackjackJudge extends Judge
A class that checks winning hand and reset players’ balance in a Blackjack game

Class GameLogger
A generic class that logs information for a card game

Class BlackjackGameLogger extends GameLogger
A class to log information that displays a Blackjack game situation and asks for user input



II. OO Design principles

**1. Abstraction**

Examples: A BlackjackGame uses interfaces provided by BlackjackPlayer, BlackjackHand, etc.

Our abstraction hide implementation details from a higher-level object. A blackjack game doesn’t need to know how to count the value of a hand. It gets the value directly from a Blackjack hand object.More importantly, **for an application that uses our class to play the game, it won’t be impacted if we made changes to our implementation.**

**2. Encapsulation**

Examples: A Blackjack player has all the data and behaviors encapsulated in a player object. Similarly, a Blackjack hand has all its data and methods in a hand object.

This provides information hiding and control. For instance, a player’s balance cannot be changed arbitrarily without using the setter method, and a player cannot make a bet without accessing their hand first, and set with the setter method.Besides, each game unit is in its own object, so the game can operate on separate units without changing other units.

**3. Inheritance**

Examples: BlackjackGame extends Game, BlackjackCard extends Card, BlackjackHand extends Hand, BlackjackPlayer extends Player, etc.

This increases reusability: **attributes and methods in generic classes can be used for a different card game and don’t require to be implemented again.**It also allows extensibility because a subclass (a particular card game) can add extra attributes and methods specific to the game.

**4. Polymorphism**

Examples: BlackjackAceCard and BlackjackFaceCard inherits BlackjackCard. 

They both override the get soft/hard value method in the superclass.This allows us to store different types of cards in a BlackjackCard list. More importantly, for various types we can call the same method but get behaviors specific to that type.

**5. Other design considerations**

Our Blackjack game allows flexibility in the following aspects:Number of playersInitial balanceDealer stopping value