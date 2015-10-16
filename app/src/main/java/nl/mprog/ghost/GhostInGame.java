/* Corine Jacobs
   10001326
   Corine_J@MSN.com
   Minor Programmeren 2015/2016 - Universiteit van Amsterdam */

/* In Game Activity, where the whole game will be played */

package nl.mprog.ghost;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Objects;

public class GhostInGame extends BaseActivity {

    private Player player1;
    private String player1name;

    private Player player2;
    private String player2name;

    private Lexicon lexicon;
    private String dict;
    private Game game;

    public TextView currentfragment;
    public EditText guessinput;
    public TextView turnindicator;
    public LinearLayout toplinearlayout;
    public ImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost_in_game);

        // make sure the keyboard does not automatically pop-up
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        currentfragment = (TextView) findViewById(R.id.current_fragment_text);
        guessinput = (EditText) findViewById(R.id.userinput_editText);
        turnindicator = (TextView) findViewById(R.id.playerturn_text);
        toplinearlayout = (LinearLayout) findViewById(R.id.toplinearlayout);
        avatar = (ImageView) findViewById(R.id.char_imageView);

        // game starts with a empty wordfragment
        currentfragment.setText("");

        SharedPreferences prefs = this.getSharedPreferences("settings", this.MODE_PRIVATE);
        String prefgame = prefs.getString("GAME", "NONE");

        // check first if this game is a new game
        if (Objects.equals(prefgame, "NEW")){
            // new game gets started via GhostPlayerInput.java so open info from intent
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

            // get the player who gets the first turn and display this
            // the first turn gets decided at random in Game.java
            // also set the appropriate background and avatar image
            if (game.getTurn()){
                turnindicator.setText(player1.getName());
                toplinearlayout.setBackgroundResource(R.drawable.user1turn_bg);
                avatar.setImageResource(R.mipmap.charc_no1);
            }
            else{
                turnindicator.setText(player2.getName());
                toplinearlayout.setBackgroundResource(R.drawable.user2turn_bg);
                avatar.setImageResource(R.mipmap.charc_no2);
            }
        }
        // check if there is a game in savedInstanceState
        else if (savedInstanceState != null){
            // there is something saved in savedInstanceState
            // load this in
            Gson gsonis = new Gson();
            String jsongame = savedInstanceState.getString("GAME", "");
            game = gsonis.fromJson(jsongame, Game.class);
        }
        // check if there is game in sharedpreferences
        else if (!prefgame.equals("NONE")){
            //there is a game in shared preferences. load it in
            Gson gsonsp = new Gson();
            String json = prefgame;
            game = gsonsp.fromJson(json, Game.class);
        }
        else{
            // should actually never happen
            Log.e("GAME", "Game could not be started");
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

        saveGame();
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
            // make guess case-insensitive
            toadd = toadd.toLowerCase();

            // add new letter to fragment and update this to the screen
            String newfragment = currentfragment.getText().toString() + toadd;
            currentfragment.setText(newfragment);

            // process the guess in the game
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

            // update text to indicate who's turn it is and change background and avatar
            if (game.getTurn()) {
                turnindicator.setText(player1.getName());
                toplinearlayout.setBackgroundResource(R.drawable.user1turn_bg);
                avatar.setImageResource(R.mipmap.charc_no1);

            } else {
                turnindicator.setText(player2.getName());
                toplinearlayout.setBackgroundResource(R.drawable.user2turn_bg);
                avatar.setImageResource(R.mipmap.charc_no2);
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


    // saves data when operating system tries to kill the app/activity
    @Override
    protected void onSaveInstanceState(Bundle outState) {

        // save complete game
        Gson gson = new Gson();
        String jsongame = gson.toJson(game);
        outState.putString("GAME", jsongame);

        super.onSaveInstanceState(outState);
    }


    // saves data when user kills application
    private void saveGame(){

        SharedPreferences prefs = this.getSharedPreferences("settings", this.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        // save complete game
        Gson gson = new Gson();
        String jsongame = gson.toJson(game);
        editor.putString("GAME", jsongame);

        editor.commit();

    }

    @Override
    protected void onStop() {

        saveGame();
        super.onStop();

    }
}
