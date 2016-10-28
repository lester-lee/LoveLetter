/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loveletter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import static loveletter.LoveLetter.pr;

/**
 *
 * @author Lester Lee
 */
public class Table {
    private Deck deck;
    private ArrayList<Player> players;
    public int curPlayer;
    public boolean gameOver;
        
    public Table(Deck d, int numPlayers){
        deck = d;
        players = new ArrayList<Player>(numPlayers);
        curPlayer = 0;
        gameOver = false;
        for (int i = 0; i < numPlayers; i++){
            players.add(new Player(i, deck.draw()));
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
        if (players.size() == 1){                       // last one standing
            gameOver = true;
            return;
        }
        curPlayer++;
        if (curPlayer >= players.size()) curPlayer = 0;
    }
    
    private Card askPlayer(Player p){
        p.printOptions();
        Scanner scan = new Scanner(System.in);    
        int action = scan.nextInt();
        // action should be either 0 (hand) or 1 (drawn card)
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
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            default:
                break;
        }
    }
    
    /* Card Actions */
    
    private boolean playedMaiden(Player p){
        Card c = p.lastPlayed;
        return (c == null) ? false : c.getType() == 4;
    }
    
    private Player maidenCheck(Player p){
        int pnum = p.whichPlayer(players.size());
        Player target = players.get(pnum);
        boolean hasMaiden = playedMaiden(target);               // checking for HANDMAIDEN
        while (hasMaiden){
            pr("That player just played HANDMAIDEN and is immune!");
            pnum = p.whichPlayer(players.size());
            hasMaiden = playedMaiden(players.get(pnum));
            target = players.get(pnum);
        }
        if (pnum == p.number-1) return null;                    // choosing yourself
        return target;
    }
    
    private void guardAction(Player p){
        Player target = maidenCheck(p);
        if (target == null) return;                             // choosing yourself
        int guess = p.guardGuess();
        Card hand = target.hand();
        if (hand.getType() == guess){
            pr("Your guess was correct! Player #" + target.number + " no longer has a chance.");
            players.remove(target.number-1);
        }else{
            pr("Your guess was incorrect...\n");
        }
    }
    
    private void priestAction(Player p){
        Player target = maidenCheck(p);
        if (target == null) return;
        Card hand = target.hand();
        pr("Their card is: " + hand);
    }
    
    public void endGame(){
        int winner = 0;
        int max = 0;
        Card c = null;
        for (int i = 0; i < players.size(); i++){
            Player p = players.get(i);
            int hand = p.hand().getType();
            if (hand > max){
                max = hand;
                winner = p.number;
                c = p.hand();
            }
        }
        pr("The winner is Player #" + winner + "!");
        pr("They had " + c + "!");
    }
    
    public String toString(){
        Player pl = players.get(curPlayer);
        String res = "Table: Currently Player #" + pl.number + "'s turn \n";
        for (Player p : players){
            res += p + "\n";
        }
        return res;
    }
}
