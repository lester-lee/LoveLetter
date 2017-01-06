/*
 * This code is available under the CC BY-SA 4.0 License
 * (Creative Commons Attribution-ShareAlike 4.0 License)
 * More information can be found at:
 * https://creativecommons.org/licenses/by-sa/4.0/
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author Lester Lee
 * A SmartPlayer will keep track of which cards have been played,
 * and make the best move according to available information.
 * SmartPlayer prioritizes eliminating other players if possible.
 */
public class SmartPlayer extends Player {

// keep count of cards & who has what
HashMap<Integer, Integer> cardCount;
HashMap<Integer, Integer> playerCards;
int playerGuess;
int guardGuess;

public SmartPlayer(int i, Card c) {
        super(i, c);
        type = "smart";
        playerGuess = -1;
        guardGuess = -1;
        playerCards = new HashMap<Integer, Integer>();
}

boolean hasCard(int c) {
        return hand.type == c || newCard.type == c;
}

public int whichPlayer(int numPlayers) {
        if (playerGuess == -1) {
                Random r = new Random();
                return r.nextInt(numPlayers);
        }else{
                int temp = playerGuess;
                playerGuess = -1;
                return temp;
        }
}

public int guardGuess() {
        int temp = guardGuess;
        guardGuess = -1;
        return temp;
}

public void addInfo(Player p, Card c){
        playerCards.put(p.number-1, c.type);
}

int getHighestPlayer() {
        int max = Integer.MIN_VALUE;
        int pnum = -1;
        for (int p : playerCards.keySet()) {
                if (playerCards.get(p) > max) {
                        max = playerCards.get(p);
                        pnum = p;
                }
        }
        return pnum;
}

int getLowestPlayer() {
        int min = Math.min(hand.type,newCard.type);
        int pnum = -1;
        for (int p : playerCards.keySet()) {
                if (playerCards.get(p) < min) {
                        min = playerCards.get(p);
                        pnum = p;
                }
        }
        return pnum;
}

void swapCards() {
        Card temp = hand;
        hand = newCard;
        newCard = temp;
}

Card playCard(int c) {
        if (hand.type != c) {
                swapCards();
        }
        playedCards.add(hand);
        Card temp = hand;
        hand = newCard;
        lastPlayed = temp;
        return temp;
}

int findChanceCard(){
        Random r = new Random();
        int counter = 0;
        ArrayList<Integer> al = new ArrayList<Integer>();
        for (int c : cardCount.keySet()) {
                for (int i = 0; i < cardCount.get(c); i++) {
                        al.add(c);
                        counter++;
                }
        }
        int rand = r.nextInt(counter);
        return al.get(rand);
}

boolean hasPrincess(){
        for (int p : playerCards.keySet()) {
                if (playerCards.get(p) == 8) {
                        playerGuess = p;
                        return true;
                }
        }
        return false;
}

int getUnknownPlayer(int max){
        ArrayList<Integer> present = new ArrayList<Integer>(playerCards.keySet());
        ArrayList<Integer> all = new ArrayList<Integer>();
        for (int i = 0; i < max; i++)
                all.add(i);
        all.removeAll(present);
        Random r = new Random();
        return all.get(r.nextInt(all.size()));
}

void checkCards(Table t){
        ArrayList<Integer> toRemove = new ArrayList<Integer>();
        for (int p : playerCards.keySet()) {
                if (t.players.get(p).lastPlayed.type == playerCards.get(p))
                        toRemove.add(p);
        }
        for (int i : toRemove)
                playerCards.remove(i);
}

public Card play(int action, Table t) {
        // check for countess
        if (newCard != null) {
                if ((hand.type == 7 && (newCard.type == 5 || newCard.type == 6)) ||
                    (newCard.type == 7 && (hand.type == 5 || hand.type == 6))) {
                        return playCard(7);
                }
        }

        cardCount = t.cardCount;
        checkCards(t);
        if (!playerCards.isEmpty()) {
                playerGuess = getHighestPlayer();
                guardGuess = playerCards.get(playerGuess);
        }

        // PlayerGuess default highest player if info
        // GuardGuess default -1 unless info

        // Try to eliminate other players if possible
        // Options sorted by highest chance to eliminate other player

        // Prince someone with the princess
        if (hasCard(5) && hasPrincess()) {
                return playCard(5);
        }
        // Guard person for sure
        if (hasCard(1) && guardGuess != -1) {
                return playCard(1);
        }
        // Baron someone with lower card
        if (hasCard(3) && !playerCards.isEmpty()) {
                playerGuess = getLowestPlayer();
                if (playerGuess != -1) return playCard(3);
        }
        // Prince person w/ highest card
        if (hasCard(5) && !playerCards.isEmpty()) {
                return playCard(5);
        }
        // Guard someone based on probability
        if (hasCard(1) && guardGuess == -1) {
                guardGuess = findChanceCard();
                return playCard(1);
        }

        // Get more information
        if (hasCard(2)) {
                playerGuess = getUnknownPlayer(t.numPlayers);
                return playCard(2);
        }

        // Handmaid
        if (hasCard(4))
                return playCard(4);

        // Trade w/ person w/ highest card
        if (hasCard(6) && !playerCards.isEmpty())
                return playCard(6);

        // If none of the options above have been taken,
        // then no information & make random but informed choice

        // if Princess, must not play
        if (hasCard(8)) {
                if (hand.type != 8) swapCards();
                return playCard(newCard.type);
        }

        // Only play Baron if chance of winning is likely
        if (hasCard(3)) {
                if (hand.type != 3) swapCards();
                if (newCard.type >= 4)
                        return playCard(newCard.type);
        }

        // Play the lower card
        if (hand.type > newCard.type) swapCards();

        return playCard(hand.type);

}

}
