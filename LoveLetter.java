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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Deck d = new Deck();
        ArrayList<Player> p = new ArrayList<Player>(2);
        Table t = new Table(d,p);
        
    }
    
}
