// Corine Jacobs
// 10001326
// Corine_J@MSN.com
// Minor Programmeren 2015/2016 - Universiteit van Amsterdam

package nl.mprog.ghost;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

public class GhostPlayerInput extends BaseActivity {

    AutoCompleteTextView player1_input;
    AutoCompleteTextView player2_input;

    RadioGroup dictionaryoptions;
    RadioButton radiobuttonnl;
    RadioButton radiobuttonen;

    String player1name;
    String player2name;
    Integer selectedIdRadio;
    String dict;
    Highscores highscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost_player_input);

        dictionaryoptions = (RadioGroup) findViewById(R.id.radiogroup_dictionary);
        radiobuttonen = (RadioButton) findViewById(R.id.radiobutton_en);
        radiobuttonnl = (RadioButton) findViewById(R.id.radiobutton_nl);

        player1_input = (AutoCompleteTextView) findViewById(R.id.player1_edittext);
        player2_input = (AutoCompleteTextView) findViewById(R.id.player2_edittext);


        // Make a string array to be used to autocomplete the edittext for username input
        // We will get this from the highscores in sharedpreferences
        SharedPreferences prefs = this.getSharedPreferences("settings", this.MODE_PRIVATE);
        String sPHighscores = prefs.getString("HIGH", "EMPTY");
        if(!sPHighscores.equals("EMPTY")){
            // there were shared preferences
            // load this in
            Gson gsonsp = new Gson();
            String json = sPHighscores;
            highscore = gsonsp.fromJson(json, Highscores.class);
        }

        // get the highscore hashmap and retrieve its keys (playernames) in a string array
        HashMap<String, String> map = highscore.getUnsortedMap();
        Set<String> keys = map.keySet();
        String[] plnames = keys.toArray(new String[keys.size()]);

        // use array adapter to implement the autocomplete for username input
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, plnames);
        player1_input.setAdapter(adapter);
        player2_input.setAdapter(adapter);

        // immediatly show previously used names on onfocus of playername input
        // instead of having to first type two characters
        player1_input.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View view, boolean hasFocus){
                if(hasFocus){
                    player1_input.showDropDown();
                }
            }
        });
        player2_input.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View view, boolean hasFocus){
                if(hasFocus){
                    player2_input.showDropDown();
                }
            }
        });

        // make sure the keyboard does not automatically pop-up
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        // retrieve prefered dictionary from sharedpreferences and check corresponding radiobutton
        String dictpref = prefs.getString("DICT", "EN");

        if (Objects.equals(dictpref, "EN")){
            radiobuttonen.setChecked(true);
        }
        else if (Objects.equals(dictpref, "NL")){
            radiobuttonnl.setChecked(true);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ghost_player_input, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // noinspection SimplifiableIfStatement

        // Gets inherited from BaseActivity

        return super.onOptionsItemSelected(item);
    }

    // after completing userinput open new activity to actually start playing the game
    public void startPlaying(View view) {

        // get player names from edit texts
        player1name = player1_input.getText().toString();
        player2name = player2_input.getText().toString();

        // get selected language from radiobuttons
        selectedIdRadio = dictionaryoptions.getCheckedRadioButtonId();
        RadioButton radioselected = (RadioButton) findViewById(selectedIdRadio);
        String selection = radioselected.getText().toString();

        if (Objects.equals(selection, "Nederlands")){
            dict = "Dutch";
        }
        else if (Objects.equals(selection, "English")){
            dict = "English";
        }
        else{
            Log.e("LEXICON", "Something went wrong with getting selection from radiobuttons");
        }

        // make sure the user cant use a username longer than 10 characters
        if (player1name.length() > 10 || player2name.length() > 10){
            Toast.makeText(this, getString(R.string.lenghtusername_toast), Toast.LENGTH_LONG).show();
        }
        // make sure the players do not pick the same name
        else if (Objects.equals(player1name, player2name)){
            Toast.makeText(this, R.string.sameplayername_warningtext, Toast.LENGTH_SHORT).show();
        }
        // else we can simply start the ingame activity to start the gameplay
        else{
            // Intent to start the next activity (ingame mode)
            Intent startPlaying = new Intent(this, GhostInGame.class);

            // send player names and selected dictionary with the intent
            startPlaying.putExtra("player 1 name", player1name);
            startPlaying.putExtra("player 2 name", player2name);
            startPlaying.putExtra("dictionary", "Dutch");

            startActivityForResult(startPlaying, 1);
        }
    }
}
