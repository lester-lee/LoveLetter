/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loveletter;

import java.util.ArrayList;
import java.util.Scanner;
import static loveletter.LoveLetter.pr;

/**
 *
 * @author Lester Lee
 */
public class Table {
    private final Deck deck;
    private final ArrayList<Player> players;
    private int numPlayers;
    private ArrayList<Integer> defeated;
    public int curPlayer;
    public boolean gameOver;
        
    public Table(Deck d, int n){
        deck = d;
        players = new ArrayList<Player>(n);
        defeated = new ArrayList<Integer>(n);
        curPlayer = 0;
        gameOver = false;
        numPlayers = 0;
        for (int i = 0; i < n; i++){
            players.add(new Player(i, deck.draw()));
            numPlayers++;
        }
    }
    
    public void takeTurn(){   
        pr(this);                                       // print the table
        Player p = players.get(curPlayer);              // get current player
        Card draw = deck.draw();                        // draw card
        if (draw == null){                              // if last card, game is over
            gameOver = true;
            return;
        }              
        p.draw(draw);
        pr("Which card would Player #" + p.number + " like to play? Your options are:");
        Card playedCard = askPlayer(p);
        takeAction(playedCard);
        if (defeated.size() == numPlayers - 1){         // last one standing
            gameOver = true;
            return;
        }
        curPlayer++;                                    // cycle to next undefeated player
        if (curPlayer >= players.size()) curPlayer = 0;
        while (defeated.contains(curPlayer)){
            curPlayer++;
        } 
    }
    
    private Card askPlayer(Player p){
        p.printOptions();
        Scanner scan = new Scanner(System.in);    
        int action = scan.nextInt();
        // action should be either 0 (hand) or 1 (drawn card) or 2 (get info)
        while (action < 0 || action > 2){
            p.printOptions();
            action = scan.nextInt();
        }
        Card playedCard = p.play(action);
        if (playedCard == null) return askPlayer(p);   // player asked for info
        return playedCard;
    }
    
    private void takeAction(Card c){
        Player curP = players.get(curPlayer);
        switch (c.getType()){
            case 1:
                guardAction(curP);
                break;
            case 2:
                priestAction(curP);
                break;
            case 3:
                baronAction(curP);
                break;
            case 4:
                pr("Player #" + curP.number + " is immune until their next turn.");
                break;
            case 5:
                princeAction(curP);
                break;
            case 6:
                kingAction(curP);
                break;
            case 8:
                defeated.add(curP.number - 1);
                break;
            default:
                break;
        }
    }
    
    /* Card Actions */
    
    private boolean playedMaiden(Player p){
        Card c = p.lastPlayed;
        return (c == null) ? false : c.getType() == 4;
    }
    
    private Player validCheck(Player p){
        int pnum = p.whichPlayer(players.size());
        boolean isDefeated = defeated.contains(pnum);        // checking if defeated
        while (isDefeated){
            pr("That player is already out of the running!");
            pnum = p.whichPlayer(players.size());
            isDefeated = defeated.contains(pnum);
        }
        Player target = players.get(pnum);
        boolean hasMaiden = playedMaiden(target);               // checking for HANDMAIDEN
        while (hasMaiden){
            pr("That player just played HANDMAIDEN and is immune!");
            pnum = p.whichPlayer(players.size());
            hasMaiden = playedMaiden(players.get(pnum));
            target = players.get(pnum);
        }
        if (defeated.contains(pnum) || playedMaiden(target)) target=validCheck(p);
        if (pnum == p.number-1) return null;                    // choosing yourself
        return target;
    }  
    
    private void guardAction(Player p){
        Player target = validCheck(p);
        if (target == null) return;                             // choosing yourself
        int guess = p.guardGuess();
        Card hand = target.hand();
        if (hand.getType() == guess){
            pr("Your guess was correct! Player #" + target.number + " no longer has a chance.");
            defeated.add(target.number-1);
        }else{
            pr("Your guess was incorrect...\n");
        }
    }
    
    private void priestAction(Player p){
        Player target = validCheck(p);
        if (target == null) return;
        Card hand = target.hand();
        pr("Their card is: " + hand + "\n");
    }
    
    private void baronAction(Player p){
        Player target = validCheck(p);
        if (target == null) return;
        int p1 = p.hand().getType();
        int p2 = target.hand().getType();
        if (p1 > p2){
            pr("You were victorious! Player #" + target.number + " no longer has a chance.");
            defeated.add(target.number-1);
            target.play(0);
        }else{
            pr("You were defeated and no longer have a chance...");
            defeated.add(p.number-1);
            p.play(0);
        }        
    }
    
    private void princeAction(Player p){
        Player target = validCheck(p);
        if (target == null) target = p;
        Card c = deck.draw();
        if (c == null) c = deck.missingCard();
        target.discard(c);
    }
    
    private void kingAction(Player p){
        Player target = validCheck(p);
        if (target == null) return;
        Card temp = p.hand();
        p.setHand(target.hand());
        target.setHand(temp);
    }
    
    public void endGame(){
        int winner = 0;
        int max = 0;
        Card c = null;
        for (Player p : players) {
            int hand = p.hand().getType();
            if (hand > max && !defeated.contains(p.number-1)){
                max = hand;
                winner = p.number;
                c = p.hand();
            }
        }
        pr("The winner is Player #" + winner + "!");
        pr("They had " + c + "!");
    }
    
    @Override
    public String toString(){
        Player pl = players.get(curPlayer);
        String res = "Table: Currently Player #" + pl.number + "'s turn \n";
        for (Player p : players){
            res += p + "\n";
        }
        return res;
    }
}
