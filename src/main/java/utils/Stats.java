package utils;

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
        return String.format("%20s %10s %5s %5s %20s %10s %20s %10s %20s",
                dictionary.get("name").toString(), "|",
                dictionary.get("N").toString(), "|",
                dictionary.get("time").toString(), "|",
                dictionary.get("solutions").toString(),"|",
                dictionary.get("backtrack").toString(),"|",
                dictionary.get("fails").toString());
    }

}
