#Love Letter! 
###A simulation of the card game [LoveLetter](https://boardgamegeek.com/boardgame/129622/love-letter).  

The main class is `LoveLetter`  

---
There are two different types of computers:  
`RandomPlayer` (_r_) will randomly select a card to play and a player to target.  
`SmartPlayer` (_s_) is currently my best attempt at a smart player! Look at the source code for more details :)  

In tests of 100,000 games:  
_s r r r_ : _s_ won 49.2% of the time  
 _s r r_  : _s_ won 62.9% of the time  
  _s r_   : _s_ won 78.5% of the time

---
Licensed under [CC-BY-SA](https://creativecommons.org/licenses/by-sa/4.0/)
