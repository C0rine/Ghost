/* Corine Jacobs
   10001326
   Corine_J@MSN.com
   Minor Programmeren 2015/2016 - Universiteit van Amsterdam */

/* custom arrayadapter to build the listview in the highscores window from a Map */

package nl.mprog.ghost;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Map;


class HighscoresAdapter extends BaseAdapter {

    // make arraylist to hold all data
    private final ArrayList thedata;

    public HighscoresAdapter(Map<String, String> map) {
        // add all data from the map to the arraylist
        thedata = new ArrayList();
        thedata.addAll(map.entrySet());
    }

    @Override
    public int getCount() {
        return thedata.size();
    }

    @Override
    public Map.Entry<String, String> getItem(int position) {
        return (Map.Entry) thedata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View result;

        if (convertView == null) {
            result = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_highscores_layout, parent, false);
        } else {
            result = convertView;
        }

        Map.Entry<String, String> item = getItem(position);

        // fill the textviews in each rows with playername (key) and his/her score (value)
        ((TextView) result.findViewById(R.id.name_textview)).setText(item.getKey());
        ((TextView) result.findViewById(R.id.score_textview)).setText(item.getValue());

        return result;
    }
}