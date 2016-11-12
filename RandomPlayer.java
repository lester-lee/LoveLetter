/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loveletter;

import java.util.Random;
import static loveletter.LoveLetter.pr;

/**
 *
 * @author Lesterr
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
        int res = 2 + r.nextInt(7);
        //pr("Their guess is " + res);
        return 2 + r.nextInt(7);
    }

    public Card play(int action, Table t) {
        Random r = new Random();
        int choice = r.nextInt(2);
        // check for countess
        if ((hand.getType() == 7 && (newCard.getType() == 5 || newCard.getType() == 6))
                || (newCard.getType() == 7 && (hand.getType() == 5 || hand.getType() == 6))) {
            choice = hand.getType() == 7 ? HAND : NEWCARD;
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

    public void addInfo(Player p, Card c) {
        return;
    }
}
