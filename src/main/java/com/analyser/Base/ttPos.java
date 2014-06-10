package com.analyser.Base;

import com.analyser.Lexicons.Lexicon;

/**
 * Created by cagil on 08/06/14.
 */
public class ttPos extends Phrases {

    private String lemma;
    boolean plural = false;
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
            if(Lexicon.getNoun(noun) == null) System.out.println(noun);

            setTranslation(Lexicon.getNoun(noun)+(plural ? "+lAr" : ""));
            return;
        } else if(getLabel().equals("JJ")) {
            setTranslation(Lexicon.getJJ(lemma));
            return;
        } else if(getLabel().equals("PRP")) {
            setTranslation(Lexicon.getPRP(lemma));
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
