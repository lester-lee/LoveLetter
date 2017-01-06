#Love Letter!
###A terminal-based simulation of the card game [LoveLetter](https://boardgamegeek.com/boardgame/129622/love-letter).  

To run the game:   
`
java LoveLetter [numgames] [type of player 1] [type of player 2] [type ...]
`   

`
java LoveLetter 10 h s s
`
will play 10 rounds of LoveLetter between a HumanPlayer and two SmartPlayers.

---
There are two different types of computers:  
`RandomPlayer` (_r_) will randomly select a card to play and a player to target.  
`SmartPlayer` (_s_) is currently my best attempt at a smart player! Look at the source code for more details :)  

In tests of 100,000 games:  
_s r r r_ : _s_ won 47.1% of the time  
 _s r r_  : _s_ won 58.6% of the time  
  _s r_   : _s_ won 74.9% of the time

---
Licensed under [CC-BY-SA](https://creativecommons.org/licenses/by-sa/4.0/)
