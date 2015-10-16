/* Corine Jacobs
   10001326
   Corine_J@MSN.com
   Minor Programmeren 2015/2016 - Universiteit van Amsterdam */

/* used to sort highscoresmap into sortedmap */

package nl.mprog.ghost;

import java.util.Comparator;
import java.util.Map;

class ValueComparator implements Comparator<String> {

    Map<String, String> map;

    public ValueComparator(Map<String, String> base) {
        this.map = base;
    }

    public int compare(String a, String b) {
        if (Integer.parseInt(map.get(a)) >= Integer.parseInt(map.get(b))) {
            return -1;
        } else {
            return 1;
        }
    }
}
