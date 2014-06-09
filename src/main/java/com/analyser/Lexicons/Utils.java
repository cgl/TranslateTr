package com.analyser.Lexicons;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cagil on 09/06/14.
 */
public class Utils {
    public static void translateNouns(String lemma) {
        return;
    }

    public final static List<String> ambigious_verbs =  new ArrayList<String>();
    static {
        ambigious_verbs.add("lock");
        ambigious_verbs.add("space");
        ambigious_verbs.add("close");
    }

    public final static List<String> ambigious_nouns =  new ArrayList<String>();
    static {
        ambigious_nouns.add("covers");
        ambigious_nouns.add("guides");
    }

    public static boolean isAmbigiousVerb(String lemma) {
        return ambigious_verbs.contains(lemma.toLowerCase());
    }


    public static String [] haveAmbigiousNoun(String text) {
        List<String> nouns = new ArrayList<String>();
        for (String ambigious_noun : ambigious_nouns) {
             if(text.contains(ambigious_noun))
                 nouns.add(ambigious_noun);
        }
        return nouns.toArray(new String [nouns.size()]);
        //return (nouns.size() > 0) ? ((String[]) nouns.toArray()) : null;
    }
}
