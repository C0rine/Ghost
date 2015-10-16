/* Corine Jacobs
   10001326
   Corine_J@MSN.com
   Minor Programmeren 2015/2016 - Universiteit van Amsterdam */


/* Base activity contains all information that needs to be the same
   across (almost) all activities (e.g. the actionbarmenu). */

package nl.mprog.ghost;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_base, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        // implement menu options:

        //  1: open settings
        if (id == R.id.settings) {
            Intent openSettings = new Intent(this, GhostSettings.class);
            startActivityForResult(openSettings, 1);
            return true;
        }
        // 2: open instructions
        else if (id == R.id.help){
            Intent openinstructions = new Intent(this, GhostInstructions.class);
            startActivityForResult(openinstructions, 1);
            return true;
        }
        // 3: close app
        else if (id == R.id.exit){
            // exit the application
            Intent exiting = new Intent(Intent.ACTION_MAIN);
            exiting.addCategory(Intent.CATEGORY_HOME);
            exiting.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(exiting);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
