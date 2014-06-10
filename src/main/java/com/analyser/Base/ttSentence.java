package com.analyser.Base;

import edu.stanford.nlp.trees.Tree;

import java.util.ArrayList;

/**
 * Created by cagil on 22/05/14.
 */
public class ttSentence extends Phrases{
    final Tree[] children;
    private String text;
    private ArrayList<Phrases> phrases;

    private Phrases first = null;
    private Phrases second;
    ttVerbPhrase VB ;

    public ttSentence(Tree tree) {
        super("S");

         this.children = tree.children();
         this.phrases = new ArrayList<Phrases>();
    }

    public ttSentence(Tree tree, String text) {
        super("S");
        this.children = tree.children();
        this.phrases = new ArrayList<Phrases>();
        this.text = text;
    }

    public void process(){
        //System.out.println("Sentence processor");
        for (Tree child : children) {
            if (child.label().value().equals("VP")) {
                //System.out.println("VP: " + child.label().value());
                VB = new ttVerbPhrase(child);
                VB.process();
                phrases.add(VB);
                //System.out.println(VB.getPhrases().size()+" "+VB.toString());
                //System.out.println(VB.getPhrases().size()+" "+child.toString());
            }
            else if(child.label().value().equals("NP")) {
                //child.pennPrint();
                ttNounPhrase NP = new ttNounPhrase(child);
                NP.process();
                phrases.add(NP);
                first = NP;
                //System.out.println("NP child: "+NP.getPhrases().size()+" "+NP.toString());
            }
            else if(child.label().value().equals("INTJ")) {
            }
            else if(child.label().value().equals(".")) {
                phrases.add(new Phrases(child.label().value()));
            }else  {
                first = new Phrases(child.label().value());
                phrases.add(first);
                // SBAR and ADJP phrase
                //System.out.println("Else: " + child.label().value()+" "+child.toString());
                //child.pennPrint();
            }
        }
    }

    public void translate() {
        if(first != null) {
            first.translate();
            if(first.getLabel().equals("ADVP")) {
                VB.translate(first.getTranslation());
            }else if(first.getLabel().equals("NP")) {
                //System.out.println("[Sentence] NP child: " + first.toString() + first.getTranslation());
                VB.translate();
                setTranslation(first.getTranslation()+" "+VB.getTranslation()+phrases.get(phrases.size()-1).getLabel());
                return;
            }else {
                VB.translate();
                setTranslation(first.getTranslation()+" "+VB.getTranslation()+phrases.get(phrases.size()-1).getLabel());
                return;
            }
        }
        else {
            VB.translate();
        }
        super.setTranslation(VB.getTranslation());
    }

    @Override
    public String toString() {
        StringBuilder st = new StringBuilder();
        for (Phrases phrase : phrases) {
            st.append(phrase.getLabel()+" ");
        }
        return st.toString() + ": "+ this.getText();
        //return "ttSentence{" + "phrases=" + phrases + '}';
    }

    public String getText() {
        return text;
    }
}
