/* Corine Jacobs
   10001326
   Corine_J@MSN.com
   Minor Programmeren 2015/2016 - Universiteit van Amsterdam  */

package nl.mprog.ghost;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Locale;
import java.util.Objects;

/* Does not inherit from BaseActivity since the actionbar in this activity
   does not need to contain a menuitem to go to the settings  */
public class GhostSettings extends AppCompatActivity {

    private Highscores highscore;
    private SharedPreferences prefs;

    private RadioButton languagenl;
    private RadioButton languageen;
    private RadioButton dictionarynl;
    private RadioButton dictionaryen;

    private String languagepref;
    private String dictionarypref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost_settings);

        languagenl = (RadioButton) findViewById(R.id.languagepref_nl_radiobutton);
        languageen = (RadioButton) findViewById(R.id.languagepref_en_radiobutton);
        dictionarynl = (RadioButton) findViewById(R.id.dictionarypref_nl_radiobutton);
        dictionaryen = (RadioButton) findViewById(R.id.dictionarypref_en_radiobutton);

        // retrieve sharedpreferences
        prefs = this.getSharedPreferences("settings", this.MODE_PRIVATE);


        // HIGHSCORES

        // retrieve Highscores from sharedPreferences if there are any
        String sPHighscores = prefs.getString("HIGH", "EMPTY");

        if(!sPHighscores.equals("EMPTY")){

            // there were shared preferences
            // load this in
            Gson gsonsp = new Gson();
            String json = sPHighscores;
            highscore = gsonsp.fromJson(json, Highscores.class);

        }



        // LANGUAGE PREFERENCES

        // retrieve language preference from sharedpreferences
        String sPLanguage = prefs.getString("LANG", "NONE");

        // check if there is data in savedInstanceState
        if (savedInstanceState != null){
            // there is something saved in savedInstanceState
            languagepref = savedInstanceState.getString("LANG", "");

            if (Objects.equals(languagepref, "NL")){
                // check NL radiobutton
                languagenl.setChecked(true);
            }
            else{
                // the preference must be EN so check EN radiobutton
                languageen.setChecked(true);
            }
        }
        // check if there are language selections in sharedpreferences
        else if(!sPLanguage.equals("NONE")){
            // there is a language preference
            if (sPLanguage.equals("NL")){
                // check NL radiobutton
                languagepref = "NL";
                languagenl.setChecked(true);
            }
            else{
                // the preference must be EN so check EN radiobutton
                languagepref = "EN";
                languageen.setChecked(true);
            }
        }



        // DICTIONARY PREFERENCES

        // retrieve dictionary preference from sharedpreferences
        String sPDictionary = prefs.getString("DICT", "NONE");

        // check if there is data in savedInstanceState
        if (savedInstanceState != null){
            // there is something saved in savedInstanceState
            dictionarypref = savedInstanceState.getString("DICT", "");

            if (Objects.equals(dictionarypref, "NL")){
                // check NL radiobutton
                dictionarynl.setChecked(true);
            }
            else{
                // the preference must be EN so check EN radiobutton
                dictionaryen.setChecked(true);
            }
        }
        // check if there are dictionary preferences in sharedpreferences
        else if(!sPDictionary.equals("NONE")){
            // there is a dictionary preference set
            if (sPDictionary.equals("NL")){
                // check NL radiobutton
                dictionarypref = "NL";
                dictionarynl.setChecked(true);
            }
            else{
                // the preference must be EN so check that radiobutton
                dictionarypref = "EN";
                dictionaryen.setChecked(true);
            }
        }

    }

    // saves data when operating system tries to kill the app/activity
    @Override
    protected void onSaveInstanceState(Bundle outState) {

        // save the language and dictionary preferences to outState
        if(languagepref != null){
            outState.putString("LANG", languagepref);
        }
        if(dictionarypref != null){
            outState.putString("DICT", dictionarypref);
        }

        super.onSaveInstanceState(outState);
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


    public void goBack(View view) {
        onBackPressed();
    }

    // gets run when either one of the language preference radiobuttons get pressed
    public void langprefChosen(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.languagepref_nl_radiobutton:
                if (checked){
                    // save language preference NL to sharedPreferences
                    languagepref = "NL";
                    Toast.makeText(this, "Language preference set to Dutch", Toast.LENGTH_SHORT).show();}
                break;

            case R.id.languagepref_en_radiobutton:
                if (checked) {
                    // save language preference EN to sharedPreferences
                    languagepref = "EN";
                    Toast.makeText(this, "Language preference set to English", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }


    // gets run when either one of the dictionary preference radiobuttons get pressed
    public void dictprefChosen(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.dictionarypref_nl_radiobutton:
                if (checked){
                    // save dictionary preference NL to sharedPreferences
                    dictionarypref = "NL";
                    Toast.makeText(this, "Dictionary preference set to Dutch", Toast.LENGTH_SHORT).show();}
                break;

            case R.id.dictionarypref_en_radiobutton:
                if (checked) {
                    // save dictionary preference EN to sharedPreferences
                    dictionarypref = "EN";
                    Toast.makeText(this, "Dictionary preference set to English", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }


    public void openInstructions(View view) {

        saveSettings();
        Intent openinstructions = new Intent(this, GhostInstructions.class);
        startActivityForResult(openinstructions, 1);
    }


    // gets run when 'clear highscores'-button gets pressed
    public void clearHighscores(View view) {

        highscore.clearScores();

        // save the clearing of the highscores to shared preferences
        prefs = this.getSharedPreferences("settings", this.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String jsonhighscore = gson.toJson(highscore);
        editor.putString("HIGH", jsonhighscore);
        editor.commit();

        Toast.makeText(this, R.string.cleared_highscores, Toast.LENGTH_SHORT).show();
    }


    // gets run 'new-game'-button gets pressed
    public void startNewGame(View view) {

        saveSettings();
        Intent newGameStart = new Intent(this, GhostPlayerInput.class);
        startActivityForResult(newGameStart, 1);

    }


    public void saveSettings() {

        // save the settings to sharedpreferences
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("LANG", languagepref);
        editor.putString("DICT", dictionarypref);
        editor.commit();

        // set locale to make sure language changes appropriately
        if (Objects.equals(languagepref, "NL")){
            // language preference is NL, change locale to this
            Resources res = getApplicationContext().getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            android.content.res.Configuration conf = res.getConfiguration();
            conf.locale = new Locale("nl");
            res.updateConfiguration(conf, dm);
        }
        else if (Objects.equals(languagepref, "EN")){
            // language preference is EN, change locale to this
            Resources res = getApplicationContext().getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            android.content.res.Configuration conf = res.getConfiguration();
            conf.locale = new Locale("en");
            res.updateConfiguration(conf, dm);
        }

    }


    @Override
    protected void onStop() {
        saveSettings();
        super.onStop();
    }


    @Override
    public void onBackPressed(){
        saveSettings();
        super.onBackPressed();
    }
}
