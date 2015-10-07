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
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

// Does not inherit from BaseActivity since the actionbar in this activity
// does not need to contain a menuitem to go to the settings.
public class GhostSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost_settings);
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
}
