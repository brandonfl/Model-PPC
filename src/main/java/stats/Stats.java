package stats;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class Stats {
    private Dictionary dictionary;

    public Stats(Dictionary<String, ?> dictionary) {
        this.dictionary = dictionary;
    }

    public Stats(String name,int N, Long solutions, Float time, Long backtrack,Long fails) {
        this.dictionary = new Hashtable();

        dictionary.put("name",name);
        dictionary.put("N",N);
        dictionary.put("solutions",solutions);
        dictionary.put("time",time);
        dictionary.put("backtrack",backtrack);
        dictionary.put("fails",fails);
    }

    @Override
    public String toString() {
        return "Stats" + dictionary.toString();
    }

    public String toTable(){
        return dictionary.get("name").toString() + "\t\t" +
                dictionary.get("N").toString() + "\t\t" +
                dictionary.get("time").toString() + "s\t\t" +
                dictionary.get("solutions").toString() + "\t\t\t" +
                dictionary.get("backtrack").toString() + "\t\t\t" +
                dictionary.get("fails").toString();
    }

}
