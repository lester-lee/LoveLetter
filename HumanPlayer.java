/*
 * This code is available under the CC BY-SA 4.0 License
 * (Creative Commons Attribution-ShareAlike 4.0 License)
 * More information can be found at:
 * https://creativecommons.org/licenses/by-sa/4.0/
 */
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Lester Lee
 * A HumanPlayer simply asks System.in for input.
 * The game will prompt the player for an action.
 */
public class HumanPlayer extends Player {

public HumanPlayer(int i, Card c) {
        super(i, c);
        type = "human";
}

public int whichPlayer(int numPlayers) {
        System.out.println("Which player # would you like to use your card on? Input their #:");
        Scanner scan = new Scanner(System.in);
        int res = scan.nextInt();
        while (res < 1 || res > numPlayers) {
                System.out.println("Invalid input. Please try again:");
                res = scan.nextInt();
        }
        return --res;
}

public int guardGuess() {
        System.out.println("What card do you think they have? Input a # from 2-8:");
        System.out.println("(2)PRIEST (3)BARON (4)HANDMAIDEN (5)PRINCE (6)KING (7)COUNTESS (8)PRINCESS");
        Scanner scan = new Scanner(System.in);
        int res = scan.nextInt();
        while (res < 2 || res > 8) {
                System.out.println("Invalid input. Please try again:");
                res = scan.nextInt();
        }
        return res;
}

public Card play(int action, Table t) {
        // check for countess
        if ((hand.type == 7 && (newCard.type == 5 || newCard.type == 6))
            || (newCard.type == 7 && (hand.type == 5 || hand.type == 6))) {
                action = hand.type == 7 ? HAND : NEWCARD;
        }
        switch (action) {
        case HAND:
                playedCards.add(hand);
                Card temp = hand;
                hand = newCard;
                lastPlayed = temp;
                return temp;
        case NEWCARD:
                playedCards.add(newCard);
                lastPlayed = newCard;
                return newCard;
        case GETINFO:
                System.out.println(hand + ": " + hand.info + "\n" + newCard + ": " + newCard.info);
        default:
                return null;
        }
}
}
