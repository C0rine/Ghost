// Corine Jacobs
// 10001326
// Corine_J@MSN.com
// Minor Programmeren 2015/2016 - Universiteit van Amsterdam

package nl.mprog.ghost;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GhostInGame extends BaseActivity {

    public Player player1;
    public String player1name;

    public Player player2;
    public String player2name;

    public Lexicon lexicon;
    public String dict;

    public Game game;

    public TextView currentfragment;
    public EditText guessinput;
    public TextView turnindicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost_in_game);

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
        player1 = new Player(player1name, 0);
        player2 = new Player(player2name, 0);

        // create new lexicon instance with selected dictionary
        lexicon = new Lexicon(this, dict);

        // create the game
        game = new Game(player1, player2, lexicon);

        // get the player who gets the first turn and display this
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

    // for debugging purposes. clicking on ghost image will take you immediately to winscreen
    public void debugBackdoor(View view) {

        Intent debugWin = new Intent(this, GhostWinScreen.class);

        startActivityForResult(debugWin, 1);

    }

    public void makeGuess(View view) {

        // add new letter to fragment and update this to the screen
        String newfragment = currentfragment.getText().toString() + guessinput.getText().toString();
        currentfragment.setText(newfragment);

        // process the guess in the game
        // filters lexicon and changes the player turn
        game.guess(newfragment);

        // check if game has not ended
        if (game.ended()){
            Intent uponWin = new Intent(this, GhostWinScreen.class);
            if (game.getTurn()){
                uponWin.putExtra("Winner", player1.getName());
            }
            else{
                uponWin.putExtra("Winner", player2.getName());
            }
            startActivityForResult(uponWin, 1);
        }

        // update text to indicate who's turn it is
        if (game.getTurn()){
            turnindicator.setText(player1.getName());
        }
        else{
            turnindicator.setText(player2.getName());
        }
    }
}
