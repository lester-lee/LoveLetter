/*
 * This code is available under the CC BY-SA 4.0 License
 * (Creative Commons Attribution-ShareAlike 4.0 License)
 * More information can be found at:
 * https://creativecommons.org/licenses/by-sa/4.0/
 */
package loveletter;

import java.util.Random;
import static loveletter.LoveLetter.pr;

/**
 *
 * @author Lester Lee
 * A RandomPlayer randomly chooses:
 * which player to use a card on
 * which Card a player has when Guarding
 * whether to play Hand or NewCard
 */
public class RandomPlayer extends Player {

    public RandomPlayer(int i, Card c) {
        super(i, c);
        type = "random";
    }

    public int whichPlayer(int numPlayers) {
        Random r = new Random();
        return r.nextInt(numPlayers);
    }

    public int guardGuess() {
        Random r = new Random();
        return 2 + r.nextInt(7);
    }

    public Card play(int action, Table t) {
        Random r = new Random();
        int choice = r.nextInt(2);
        // check for countess
        if ((hand.type == 7 && (newCard.type == 5 || newCard.type == 6))
                || (newCard.type == 7 && (hand.type == 5 || hand.type == 6))) {
            choice = hand.type == 7 ? HAND : NEWCARD;
        }
        if (action == 0) {
            choice = action; // forced to play hand         
        }
        switch (choice) {
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
            default:
                return null;
        }
    }
}
