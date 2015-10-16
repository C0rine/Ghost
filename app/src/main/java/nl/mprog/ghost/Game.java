/* Corine Jacobs
   10001326
   Corine_J@MSN.com
   Minor Programmeren 2015/2016 - Universiteit van Amsterdam */


/* An instance of this Game class represents a single game. It implements the gamerules.
   It takes two players and one lexicon as arguments to start up a game. Also keeps track of the turn,
   processes the guesses made by the players and decides who wins the game. */

package nl.mprog.ghost;

import java.util.Random;

public class Game{

    private Player player1;
    private Player player2;
    private Lexicon lexicon;

    private boolean turn;
    private String theword;

    public Game(Player player1st, Player player2nd, Lexicon dict){

        player1 = player1st;
        player2 = player2nd;
        lexicon = dict;

        /* choose the player who gets the first turn at random
           player 1's turn: true | player 2's turn: false */
        Random random = new Random();
        turn = random.nextBoolean();

    }


    /* processes the guess a user has made
       input should be the complete current wordfragment in the game */
    public void guess(String wordfragment){

        theword = wordfragment;

        // filter the lexicon on the current wordfragment
        lexicon.filter(theword);

        // Assign the player one point for each time he/she guesses a letter
        if(getTurn()){
            player1.setPoints(player1.getPoints() + 1);
        }
        else{
            player2.setPoints(player2.getPoints() + 1);
        }

        changeTurn();

    }


    /* returns a boolean indicating which player is up for guessing
       player 1's turn: true | player 2's turn: false */
    public boolean getTurn(){

        return turn;

    }


    // changes player turn to opposite player
    public void changeTurn(){

        turn = !turn;

    }


    // returns a boolean indicating if the game has ended
    public boolean ended(){

        // the current wordfragment is longer than 3 letters and is a word in lexicon: game ends
        if ((theword.length() > 3) && lexicon.getFiltereddict().contains(theword)){
            return true;
        }
        // the current wordfragment cannot become a word -> filtered list is empty: game ends
        else if (lexicon.count() < 1){
            return true;
        }
        else{
            // the game has not ended yet
            return false;
        }

    }


    /* returns a boolean indicating which player has won the game
       when game ends this class looks at the player who had the last turn. The winner gets
       based of this (player 1 wins: true | player 2 wins: false)  */
    public boolean winner(){

            return turn;
    }

}
