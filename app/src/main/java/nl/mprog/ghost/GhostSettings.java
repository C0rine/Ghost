// Corine Jacobs
// 10001326
// Corine_J@MSN.com
// Minor Programmeren 2015/2016 - Universiteit van Amsterdam

package nl.mprog.ghost;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Objects;

// Does not inherit from BaseActivity since the actionbar in this activity
// does not need to contain a menuitem to go to the settings.
public class GhostSettings extends AppCompatActivity {

    private Highscores highscore;
    //private SharedPreferences preferences;
    //private String json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost_settings);

        // retrieve Highscores from sharedPreferences if there are any
        SharedPreferences prefs = this.getSharedPreferences("settings", this.MODE_PRIVATE);
        String sPHighscores = prefs.getString("HIGH", "EMPTY");

        if(!sPHighscores.equals("EMPTY")){

            // there were shared preferences
            // load this in
            Gson gsonsp = new Gson();
            String json = sPHighscores;
            highscore = gsonsp.fromJson(json, Highscores.class);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ghost_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        // close settings activity (not the whole app)
        if (id == R.id.exit){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void goBack(View view) {
        onBackPressed();
    }

    public void dictprefChosen(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.dictionarypref_nl_radiobutton:
                if (checked)
                    //Toast.makeText(this, "dict NL chosen", Toast.LENGTH_SHORT).show();
                    break;
            case R.id.dictionarypref_en_radiobutton:
                if (checked)
                    // Toast.makeText(this, "dict EN chosen", Toast.LENGTH_SHORT).show();
                    break;
        }

    }

    public void openInstructions(View view) {

        Intent openinstructions = new Intent(this, GhostInstructions.class);

        startActivityForResult(openinstructions, 1);
    }


    public void clearHighscores(View view) {

        highscore.clearScores();

        // save the clearing of the highscores to shared preferences
        SharedPreferences prefs = this.getSharedPreferences("settings", this.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        Gson gson = new Gson();
        String jsonhighscore = gson.toJson(highscore);
        editor.putString("HIGH", jsonhighscore);

        editor.commit();

        Toast.makeText(this, R.string.cleared_highscores, Toast.LENGTH_SHORT).show();
    }


    public void startNewGame(View view) {

        Intent newGameStart = new Intent(this, GhostPlayerInput.class);

        startActivityForResult(newGameStart, 1);

    }
}
