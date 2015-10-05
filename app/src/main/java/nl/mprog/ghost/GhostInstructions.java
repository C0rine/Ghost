// Corine Jacobs
// 10001326
// Corine_J@MSN.com
// Minor Programmeren 2015/2016 - Universiteit van Amsterdam

package nl.mprog.ghost;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class GhostInstructions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost_instructions);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ghost_instructions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        // open settings
        if (id == R.id.settings) {
            Intent openSettings = new Intent(this, GhostSettings.class);
            startActivityForResult(openSettings, 1);
        }
        // close app
        else if (id == R.id.exit){
            System.exit(0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
