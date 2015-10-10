// Corine Jacobs
// 10001326
// Corine_J@MSN.com
// Minor Programmeren 2015/2016 - Universiteit van Amsterdam

package nl.mprog.ghost;

import android.util.Log;

import java.util.Random;

public class Game{

    Player player1;
    Player player2;
    Lexicon lexicon;
    boolean turn;
    boolean ended;
    String theword;

    // constructor
    public Game(Player player1st, Player player2nd, Lexicon dict){

        player1 = player1st;
        player2 = player2nd;
        lexicon = dict;

        // choose the player who gets the first turn at random
        // player 1's turn: true | player 2's turn: false
        Random random = new Random();
        turn = random.nextBoolean();

    }


    // processes the guess a user has made
    // input should be the complete current wordfragment in the game
    public void guess(String wordfragment){

        theword = wordfragment;
        lexicon.filter(theword);

        // gives one point for each time a letter gets guessed
        if(getTurn()){
            player1.setPoints(player1.getPoints() + 1);
        }
        else{
            player2.setPoints(player2.getPoints() + 1);
        }

        changeTurn();

    }


    // returns a boolean indicating which player is up for guessing
    public boolean getTurn(){

        return turn;

    }

    // changes turns
    public void changeTurn(){

        turn = !turn;

    }


    // returns a boolean indicating if the game has ended
    public boolean ended(){

        // the current wordfragment is longer than 3 letters and is a word in lexicon
        if ((theword.length() > 3) && lexicon.filtereddict.contains(theword)){
            return true;
        }
        // the current wordfragment cannot become a word -> filtered list is empty
        else if (lexicon.count() < 1){
            return true;
        }
        else{
            return false;
        }

    }


    // returns a boolean indicating which player has won the game
    // when game ends this class looks at the player who had the last turn. The winner gets
    // based of this.
    // player 1 wins: true | player 2 wins: false
    public boolean winner(){

        // only makes sense when ended is true
        //if(ended){
            return turn;
        //}
        //else{
          //  Log.e("GAME", "Game did not end yet, so winner could not be decided");
            //return false;
        //}

    }

}
