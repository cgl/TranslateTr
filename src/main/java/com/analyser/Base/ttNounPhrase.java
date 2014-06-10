package com.analyser.Base;

import edu.stanford.nlp.trees.Tree;

import java.util.ArrayList;

/**
 * Created by cagil on 08/06/14.
 */
public class ttNounPhrase extends Phrases{
    final Tree[] children;
    Tree Noun;
    String det = null;
    String NN = null;
    private ArrayList<Phrases> phrases;

    public ttNounPhrase(Tree tree) {
        super("NP");
        this.children = tree.children();
        this.phrases = new ArrayList<Phrases>();
    }

    public void process() {
        //System.out.println("Noun Phrase processor");
        for (Tree child : children) {
            if (child.label().value().equals("PP")) {
                ttPrepPhrase PP = new ttPrepPhrase(child);
                PP.process();
                phrases.add(PP);
                //System.out.println("PP Phrase child: "+child.label()+" "+child.toString()+" "+child.yieldWords().get(0).word());
            } else if (child.label().value().equals("NP")) {
                ttNounPhrase NP = new ttNounPhrase(child);
                NP.process();
                phrases.add(NP);
            } else if (child.label().value().startsWith("VB")) {
                phrases.add(new ttPos(child.label().value(), child.yieldWords().get(0).word()));
            } else if (child.label().value().equals("DT")) {
                det = child.yieldWords().get(0).word();
                phrases.add(new ttPos(child.label().value(), det));
            } else if (child.label().value().startsWith("NN")) {
                NN = child.yieldWords().get(0).word();
                phrases.add(new ttPos(child.label().value(), NN));
            //} else if (child.label().value().startsWith("S")) { //equals("SBAR")) { // equals("S")) {
            } else { // (JJ other) (PRP it) (CD two)
                //System.out.println("Noun Phrase child: "+child.label()+" "+child.toString()+" "+child.yieldWords().get(0).word());
                phrases.add(new ttPos(child.label().value(), child.yieldWords().get(0).word()));
            }
        }
        //System.out.println(this.toString());

    }

    public ArrayList<Phrases> getPhrases() {
        return phrases;
    }

    @Override
    public void translate() {
        String translation = "";
        ArrayList<Phrases> npPhrase = this.getPhrases();

        if(npPhrase.get(0).getLabel().startsWith("DT")){
            translateDT(npPhrase);
            return;
        }
        else if(npPhrase.get(0).getLabel().equals("NP") | npPhrase.get(0).getLabel().equals("PP")) {
            translateNPPP(npPhrase);
            return;
            //System.out.println(this.getPhrases().size() + " " + this.toString());
        }else if(npPhrase.get(0).getLabel().equals("JJ") | npPhrase.get(0).getLabel().startsWith("NN")) {
            npPhrase.get(0).translate();
            npPhrase.get(1).translate();
            setTranslation(npPhrase.get(0).getTranslation()+" "+npPhrase.get(1).getTranslation());
            return;
        }

        //System.out.println(translation+" "+this.toString());
        for (Phrases phrase : phrases) {
            //System.out.println(" " + phrase.toString());
            phrase.translate();
            setTranslation(phrase.getTranslation());
        }
    }

    private void translateNPPP(ArrayList<Phrases> npPhrase) {
        npPhrase.get(0).translate();
        npPhrase.get(1).translate();
        setTranslation(npPhrase.get(1).getTranslation()+" "+npPhrase.get(0).getTranslation());
    }

    private void translateDT(ArrayList<Phrases> npPhrase) {
        String aralik1,aralik2 = "";
        if(npPhrase.size() == 2) { // DT NNS  Phrases{label='NNS'lemma='rollers'}
            npPhrase.get(1).translate();
            setTranslation(npPhrase.get(1).getTranslation()+"+I");
            return;
        }else if (npPhrase.get(0).getLabel().startsWith("NN")){
            aralik1 = "In ";
            aralik2 = "nI";
        }else{ //  DT JJ NN
            aralik1 = " ";
        }
        npPhrase.get(1).translate();
        npPhrase.get(2).translate();
        setTranslation(npPhrase.get(1).getTranslation()+aralik1+npPhrase.get(2).getTranslation()+"+I"+aralik2);
    }

    @Override
    public String toString() {
        StringBuilder st = new StringBuilder();
        for (Phrases phrase : phrases) {
            st.append(phrase.getLabel()+" ");
        }
        return st.toString();
        //return "ttSentence{" + "phrases=" + phrases + '}';
    }

    public String toString2() {
        return "ttNP{" +
                "phrases=" + phrases +
                '}';
    }

}
