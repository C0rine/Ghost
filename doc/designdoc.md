# Design Doc  

### Use Case 

##### Description  

One of two users starts a new game. Both users provide their name and choose an avatar. A dictionary to use within the game is chosen. Players form a word by alternating turns saying a letter, which is added on to the end of a word fragment. One of the users loses if they form a complete word longer than 3 letters or create a word fragment that cannot become a word by adding more letters. The winning user is displayed on the screen. Points for both users get calculated. Users are displaying within a highscore. Users get the option to play another game.  

##### Triggers  
-	A player starts a new game
-	A player inputs a letter to append at the end of the word fragment

##### Actors  
-	Player 1
-	Player 2
-	Word fragment
-	Ghost Game

##### Preconditions  
-	2 players are available
-	Dictionary to check word fragment against

##### Goals  
-	One players wins, the other loses
-	Both players get ranked in the highscores

##### Failed Conclusion  
Cases in which the system will fail or will stop processing before going through the entire use case.
-	Player changes dictionary mid game (will cause game to restart)
-	Player restarts match
-	Player exits match/app (onDestroy of Activity)

##### Steps of execution  
1.	User taps to start a new game
2.	User 1 inputs name and chooses avatar
3.	If name of user 1 was not used before it is saved 
4.	User 2 inputs name and chooses avatar
5.	If name of user 2 was not used before it is saved
6.	Users choose language for the dictionary they want to use for the game
7.	The users take turns creating a word fragment. Each turn:  
  a.	User inputs one letter  
  b.	The letter gets appended to end of the word fragment  
  c.	The updated word fragment gets checked against the chosen dictionary    
  The steps above are repeated until:
    1.	The wordfragment is longer than 3 letters AND is a word that is found in the dictionary
    2.	The wordfragment cannot become a word that is found in the dictionary by adding more letters to the end of the fragment.
8.	Winning user and losing user are decided
9.	Winning user gets displayed along with reason for winning
10.	Points achieved by each user are calculated
11.	Both users are placed within the highscore list according to the amount of points they achieved
12.	Updated Highscore list is saved
13.	User gets the option to play another game  

### Activity Diagrams  

To do  

### Class Diagrams  

To do  

### Consolidated sketches of each screen  

To do




