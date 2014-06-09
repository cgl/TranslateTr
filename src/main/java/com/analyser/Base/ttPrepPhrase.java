package com.analyser.Base;

import edu.stanford.nlp.trees.Tree;

import java.util.ArrayList;

/**
 * Created by cagil on 08/06/14.
 */
public class ttPrepPhrase extends Phrases {
    final Tree[] children;
    private ArrayList<Phrases> phrases;
    String prep = null;
    public ttPrepPhrase(Tree tree) {
        super("PP");
        this.children = tree.children();
        this.phrases = new ArrayList<Phrases>();
    }
    public void process(){
        //System.out.println("Verb Phrase processor");
        for (Tree child : children) {
            if (child.label().value().equals("NP")) {
                ttNounPhrase NP = new ttNounPhrase(child);
                NP.process();
                phrases.add(NP);
                //System.out.println("Noun Phrase child: "+child.label()+" "+child.toString()+" "+child.yieldWords().get(0).word());
            } else if (child.label().value().equals("IN") | child.label().value().equals("TO") ) {
                prep = child.yieldWords().get(0).word();
                phrases.add(new ttPos(child.label().value(), prep));
                //System.out.println("PP child: "+child.label()+" "+child.toString()+" "+child.yieldWords().get(0).word());
            } else if (child.label().value().equals("S")) { //(S (VP (VBG lifting) (NP (PRP it)) (ADVP (RB firmly))))
            } else {
                //System.out.println("Child: "+child.label()+" "+child.toString()+" "+child.yieldWords().get(0).word());
                phrases.add(new ttPos(child.label().value(), child.yieldWords().get(0).word()));
            }
        }

    }

    @Override
    public void translate() {
        //System.out.println(phrases.toString());
    }

    @Override
    public String toString() {
        return "ttPP{" +
                "p=" + prep +
                "phrases=" + phrases +
                '}';
    }

}
