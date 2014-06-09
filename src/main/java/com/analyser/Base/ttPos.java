package com.analyser.Base;

import com.analyser.Lexicons.Lexicon;

/**
 * Created by cagil on 08/06/14.
 */
public class ttPos extends Phrases {

    private String lemma;
    public ttPos(String label, String value) {
        super(label);
        this.lemma = value;
    }

    public String getLemma() {
        return lemma;
    }

    @Override
    public void translate() {
        if(getLabel().equals("NN"))
            setTranslation(Lexicon.getNoun(lemma));
    }

    @Override
    public String toString() {
        return "Phrases{" +
                "label='" + super.getLabel() + '\'' +
                "lemma='" + lemma + '\'' +
                '}';
    }
}
