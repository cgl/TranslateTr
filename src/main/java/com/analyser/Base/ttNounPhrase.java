package com.analyser.Base;

import com.analyser.Lexicons.Lexicon;
import edu.stanford.nlp.trees.Tree;

import java.util.ArrayList;
import java.util.List;

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
        if(npPhrase.get(0).getLabel().equals("DT")) {
            translation = translateDT(npPhrase);
            //System.out.println(phrase.getPhrases().size() + " " + phrase.toString());
        }
        else if(npPhrase.get(0).getLabel().equals("NP") | npPhrase.get(0).getLabel().equals("PP")) {
            translation = translateDTPP(npPhrase);
            //System.out.println(this.getPhrases().size() + " " + this.toString());
        }
        //System.out.println(translation+" "+this.toString());
    }

    private String translateDTPP(ArrayList<Phrases> npPhrase) {
        //return "";
        //System.out.println(npPhrase.toString());
        return translatePP(npPhrase.get(1)) + " " + translateDT(npPhrase.get(0).getPhrases());

    }

    private String translatePP(Phrases phrase) {
        phrase.translate();
        return  "";
        //System.out.println(phrase.toString());
    }

    private String translateDT(ArrayList<Phrases> npPhrase) {
        if(npPhrase.size() < 3){
            return translateNP(npPhrase.subList(1, npPhrase.size()));
        }
        return null;
    }

    private String translateNN(ttPos nn) {
        String noun = nn.getLemma();
        if(nn.getLabel().equals("NNS") | nn.getLabel().equals("NNPS"))
            noun =  noun.substring(0,noun.length()-1);
        //System.out.println(noun+": "+ Lexicon.getNoun(noun)+" "+nn.getLabel());
        return Lexicon.getNoun(noun);
    }

    private String translateNP(List<Phrases> npPhrase) {
        String res = "";
        for (Phrases phrase : npPhrase) {
            res += translateNN((ttPos) phrase)+ " ";
        }
        return res;
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
