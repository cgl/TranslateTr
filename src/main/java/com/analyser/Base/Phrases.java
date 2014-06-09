package com.analyser.Base;

import edu.stanford.nlp.trees.Tree;

import java.util.ArrayList;

/**
 * Created by cagil on 08/06/14.
 */
public class Phrases {
    private Tree[] children;
    final private String label;
    private ArrayList<Phrases> phrases;
    private String lemma;

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    private String translation = null;
    public Phrases(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "Phrases{" +
                "label='" + label + '\'' +
                '}';
    }
    public void translate() {

    }

    public ArrayList<Phrases> getPhrases() {
        return phrases;
    }

    public Tree[] getChildren() {
        return children;
    }

    public String getLemma() {
        return lemma;
    }
}
