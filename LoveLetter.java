/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loveletter;

import java.util.ArrayList;

/**
 *
 * @author Lesterr
 */
public class LoveLetter {
    
    public static void pr(Object line){
        System.out.println(line);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Table t = new Table(new Deck(), 2);
        while (!t.gameOver){
            t.takeTurn();
        }        
        t.endGame();
    }
    
}
