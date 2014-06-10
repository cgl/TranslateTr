package com.analyser.Base;

import com.analyser.Lexicons.Lexicon;

/**
 * Created by cagil on 08/06/14.
 */
public class ttPos extends Phrases {

    private String lemma;
    boolean plural = false;
    String tense = null;
    public ttPos(String label, String value) {
        super(label);
        this.lemma = value;
    }

    public String getLemma() {
        return lemma;
    }

    @Override
    public void translate() {
        if(getLabel().startsWith("NN")) {
            String noun = getLemma();
            if(getLabel().equals("NNS") | getLabel().equals("NNPS")) {
                noun = noun.substring(0, noun.length() - 1);
            }
            if(Lexicon.getNoun(noun) == null) System.out.println("[ttpos] No noun with lemma: "+noun);

            setTranslation(Lexicon.getNoun(noun)+(plural ? "+lAr" : ""));
            return;
        } else if(getLabel().startsWith("VB")) {
            String verb = getLemma();
            if(getLabel().equals("VBD") | getLabel().equals("VBN")) {
                verb = verb.substring(0, verb.length() - 2);
                tense = "past";
            }
            setTranslation(Lexicon.getVerbs(verb)+(tense !=null ? "+"+tense : ""));
            if(Lexicon.getVerbs(verb) == null) System.out.println("[ttpos] No verb with lemma: "+verb);
            return;
        } else if(getLabel().equals("JJ")) {
            setTranslation(Lexicon.getJJ(lemma));
            return;
        } else if(getLabel().equals("PRP")) {
            setTranslation(Lexicon.getPRP(lemma));
            return;
        } else if(getLabel().equals("CD")) {
            setTranslation(Lexicon.getNum(lemma));
            return;
        }
        if(getPhrases() == null)
            return;

        StringBuilder trans = new StringBuilder();
        for (Phrases phrases : getPhrases()) {
            phrases.translate();
            trans.append(phrases.getTranslation());
        }
        setTranslation(trans.toString());
    }

    @Override
    public String toString() {
        return "Phrases{" +
                "label='" + super.getLabel() + '\'' +
                "lemma='" + lemma + '\'' +
                '}';
    }
}
