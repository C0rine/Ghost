// Corine Jacobs
// 10001326
// Corine_J@MSN.com
// Minor Programmeren 2015/2016 - Universiteit van Amsterdam

package nl.mprog.ghost;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class GhostInGame extends BaseActivity {

    public Player player1;
    public String player1name;

    public Player player2;
    public String player2name;

    public Lexicon lexicon;
    public String dict;

    public Highscores highscore;

    public Game game;

    public TextView currentfragment;
    public EditText guessinput;
    public TextView turnindicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost_in_game);

        // make sure the keyboard does not automatically pop-up
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        currentfragment = (TextView) findViewById(R.id.current_fragment_text);
        guessinput = (EditText) findViewById(R.id.userinput_editText);
        turnindicator = (TextView) findViewById(R.id.playerturn_text);

        currentfragment.setText("");

        // open extras from intent
        Bundle recvIntent = getIntent().getExtras();
        player1name = recvIntent.getString("player 1 name");
        player2name = recvIntent.getString("player 2 name");
        dict = recvIntent.getString("dictionary");

        // create two new instances of player class based on user input from previous activity
        // set their initial score to 0
        player1 = new Player(player1name, 0);
        player2 = new Player(player2name, 0);

        // create new lexicon instance with selected dictionary
        lexicon = new Lexicon(this, dict);

        // create the game
        game = new Game(player1, player2, lexicon);

        // create highscores
        // highscore = new Highscores();

        // get the player who gets the first turn and display this
        // the first turn gets decided at random in Game.java
        if (game.getTurn()){
            turnindicator.setText(player1.getName());
        }
        else{
            turnindicator.setText(player2.getName());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ghost_in_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        // gets inherited from BaseActivity

        return super.onOptionsItemSelected(item);
    }

    // When a user is in game and presses back the running game will be canceled
    // before that happens though the user gets asked to confirm this in a dialogbox.
    public void onBackPressed(){

        DialogFragment exitGameDialog = new ExitGameWarningDialog();

        exitGameDialog.show(getFragmentManager(), "warning");

    }

    public void makeGuess(View view) {

        // retrieve the string the user want to input as guess
        String toadd = guessinput.getText().toString();

        // check if the user has made a legitimate guess (only single alphabetical characters are allowed)
        if (toadd.length() > 1 || !toadd.matches("[a-zA-Z]+")){
            Toast.makeText(this, "Only single alphabetical characters are allowed", Toast.LENGTH_LONG).show();
        }
        else {
            // convert the single alphabetical input to lowercase (if this is not already the case)
            // to make guess case-insensitive
            toadd = toadd.toLowerCase();

            // add new letter to fragment and update this to the screen
            String newfragment = currentfragment.getText().toString() + toadd;
            currentfragment.setText(newfragment);

            // process the guess in the game
            // filters lexicon and changes the player turn
            game.guess(newfragment);

            // check if game has not ended
            if (game.ended()) {

                // game has ended winner needs to be determined

                Intent uponWin = new Intent(this, GhostWinScreen.class);

                // prepare to send Player object by converting them with Gson
                Gson gson1 = new Gson();
                String jsonplayer1 = gson1.toJson(player1);
                Gson gson2 = new Gson();
                String jsonplayer2 = gson2.toJson(player2);

                if (game.winner()) {

                    // player 1 won
                    // put player objects in intent with key assigning who won
                    uponWin.putExtra("WINNER", jsonplayer1);
                    uponWin.putExtra("LOSER", jsonplayer2);

                } else {

                    // player 2 won
                    // put player objects in intent with key assigning who won
                    uponWin.putExtra("LOSER", jsonplayer1);
                    uponWin.putExtra("WINNER", jsonplayer2);

                }
                startActivityForResult(uponWin, 1);
            }

            // update text to indicate who's turn it is
            if (game.getTurn()) {
                turnindicator.setText(player1.getName());
            } else {
                turnindicator.setText(player2.getName());
            }

            // empty edittext
            guessinput.setText("");

            // hide keyboard to keep screen clear and show who's turn it is
            View it = this.getCurrentFocus();
            guessinput.clearFocus();
            if (it != null) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(it.getWindowToken(), 0);
            }
        }
    }
}
