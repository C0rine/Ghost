# Ghost - Android App

Corine Jacobs   
10001326   
Corine_J [at] MSN [dot] com   

To run: open the project in Android Studio and run it on your own Android Device or in the emulator.  

### Summary of the project
Ghost is a wordgame that is played by two players. The players form a word by alternating turns saying a letter, which is added on the end of the word fragment. A player loses when he/she forms a word longer than 3 letters or creates a fragment which cannot become a word by adding more letters.
   
*(Ghost will be a native app for Android. Min. SDK: API 19, Android 4.4 (KitKat))*  
*This app was tested on a Samsung SM-G361F and HTC ONE mini*

### Issue disclaimer
All features below have been implemented, but are not all bugfree.
Due to shortage of time, the decision was made to improve code instead fix these bugs.
All bugs have been reviewed, but were not easily fixed at the time.

Bugs:  
- Starting a new game does not clear memory, which might make the app crash after multiple plays
- Changing the language preference does not immediately take effect, activities first need to be restarted
- When a player that already is in the highscores gets added again his/her scores are overwritten instead of new and previous scores are added
- Clearing highscores also does not take effect immediately
- Showing the reason for winning in WinScreen is not implemented
- The game state does get saved in SharedPreferences, but when the app gets killed it does not yet provide a way to resume this match. (The functionality is there, the userinterface to control this is not).

### Features
- Write a custom username or pick a previously used one.  
- ~~Pick a player avatar from a library.~~ (not enough time to implement)  
- Pick between Dutch or English dictionaries for the game.
- Guess a letter via an on-screen keyboard.
- Get notifications when you win/lose.
- Use a menu to restart game play or set language preferences.
- See your place in a high score list.
- Game state and preferences survive even when the app gets closed.

### Sketches of user experience

Updates on UI (differences between actual app and images below):  
- Removed special activity for settings in game. There is just one settings activity now.
- Changed settings to also have an option to clear highscores and start a new game
- Removed change of player name in settings 
- Removed option to restart a game in InGame Activity (this was moved to settings)

##### All activities  
![AllActivities](/doc/AllActivities.jpg)
##### Paths  
![Paths](/doc/Paths.jpg)
