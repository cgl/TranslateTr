package com.analyser.Base;

import com.analyser.Lexicons.Lexicon;
import edu.stanford.nlp.trees.Tree;

import java.util.ArrayList;

/**
 * Created by cagil on 22/05/14.
 */
public class ttVerbPhrase extends Phrases {
    final Tree[] children;


    //VB or VBG or VBZ(clips)
    Tree Verb;
    String verb;
    String aux = null;
    String md = null;
    String rb_adverb = null;
    String tense = null;
    boolean plural = false;

    private ArrayList<Phrases> phrases;
    //ADVP
    ttVerbPhrase VB = null;
    public ttVerbPhrase(Tree tree) {
        super("VP");
        this.children = tree.children();
        this.phrases = new ArrayList<Phrases>();
        //tree.pennPrint();
    }
    public void process(){
        //System.out.println("Verb Phrase processor");
        int count = 0;
        for (Tree child : children) {
            count++;
            if (child.label().value().equals("VP")) {
                if(child.getChild(1).label().value().equals("VP")){
                    aux = child.getChild(0).yieldWords().get(0).word();
                    System.out.println(aux);
                    setVerb(child.getChild(1).firstChild());
                    phrases.add(new ttPos(Verb.label().value(), Verb.yieldWords().get(0).word()));
                    ttPrepPhrase PP = new ttPrepPhrase(child.getChild(1).getChild(1));
                    PP.process();
                    phrases.add(PP);
                }
                else{
                    setVerb(child.firstChild());
                    phrases.add(new ttPos(Verb.label().value(), Verb.yieldWords().get(0).word()));
                    ttNounPhrase NP = new ttNounPhrase(child.getChild(1));
                    NP.process();
                    phrases.add(NP);
                    ttPrepPhrase PP = new ttPrepPhrase(child.getChild(2));
                    PP.process();
                    phrases.add(PP);
                }
            } else if (child.label().value().startsWith("VB")) {
                setVerb(child);
                phrases.add(new ttPos(child.label().value(), child.yieldWords().get(0).word()));
            } else if (child.label().value().equals("NP")) {
                ttNounPhrase NP = new ttNounPhrase(child);
                NP.process();
                phrases.add(NP);
                //System.out.println(child.toString());
            } else if (child.label().value().equals("PP")) {
                ttPrepPhrase PP = new ttPrepPhrase(child);
                PP.process();
                phrases.add(PP);
                //System.out.println("PP Phrase child: "+child.label()+" "+child.toString()+" "+child.yieldWords().get(0).word());
            }
            else if (child.label().value().startsWith("S")){ //equals("SBAR")) { // equals("S")) {

            } else if (child.label().value().equals("MD")) {
                md = child.yieldWords().get(0).word();
                //System.out.println("Hello "+count+": " + Arrays.toString(child.yieldWords().toArray()));
                //System.out.println("MD Phrase child: "+child.label()+" "+child.toString()+" "+child.yieldWords().get(0).word());
            } else { // (ADJP (JJ sure)) (ADVP (RB firmly)) (ADVP (RB evenly)) (ADJP (JJ available))
                //System.out.println("Verb Phrase child: "+child.label()+" "+child.toString()+" "+child.yieldWords().get(0).word());
                if(!child.isLeaf())
                    child = child.getChild(0);
                if(child.label().value().equals("RB")){
                    //System.out.println("Else Phrase child: "+child.label()+" "+child.toString()+" "+child.yieldWords().get(0).word());
                    rb_adverb = child.yieldWords().get(0).word();
                    continue;
                }
                phrases.add(new ttPos(child.label().value(), child.yieldWords().get(0).word()));
            }
        }
        //System.out.println(this.toString());
    }

    public void setVerb(Tree verb_tree) {
        this.Verb = verb_tree;
        verb = Verb.yieldWords().get(0).word();
        if (Verb.label().value().equals("VBZ")) {
            verb = verb.substring(0, verb.length() - 1);
            plural = true;
        }
        if (Verb.label().value().equals("VBD") | Verb.label().value().equals("VBN")) {
            verb = verb.substring(0, verb.length() - 2);
            System.out.println(verb);
            tense = "past";
        }
    }

    @Override
    public void translate() {
        translate(rb_adverb);
    }

    private String tVerb(){
        return (md !=null ? md+"+" : "" )+Lexicon.getVerbs(verb)+ (plural ? "+(3rd)": "")+ (tense !=null ? "+"+tense : "" );
    }
    private String translateVerb(String advp) {
        //System.out.println("[ttverb translate single] "+verb+": "+Lexicon.getVerbs(verb)+" "+Verb.label()+" trnas:"+tVerb());
        String trans = (advp !=null ? Lexicon.getRB(advp) : "" ) + " " + tVerb();
        setTranslation(trans);
        return trans;
    }
    private void translateVerb(String advp, String jj) {
        //System.out.println(verb+": "+Lexicon.getVerbs(verb)+" "+Verb.label());
        String trans = (advp !=null ? Lexicon.getRB(advp) : "" ) +" "+ Lexicon.getJJ(jj)+" "+ tVerb();
        setTranslation(trans);
    }

    public String toString2() {
        return "ttVP{" +
                "phrases=" + phrases +
                '}';
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

    public ArrayList<Phrases> getPhrases() {
        return this.phrases;
    }

    public void translate(String advp) {
        if(phrases.size() < 2) {
            translateVerb(advp);
            return;
        }
        String pp_translate = null;
        String np_translate = null;
        String vb_translate = null;
        for (Phrases phrase : phrases) {
            if(phrase.getLabel().equals("NP")) {
                phrase.translate();
                np_translate = phrase.getTranslation();
            }else if(phrase.getLabel().startsWith("VB")){
                vb_translate = translateVerb(advp);
            }else if(phrase.getLabel().equals("PP")){
                 phrase.translate();
                pp_translate = phrase.getTranslation();
            }else if(phrase.getLabel().equals("JJ")){
                translateVerb(advp, phrase.getLemma());
            }else {
                System.out.println(phrase.getLabel()+" "+((ttPos)phrase).getLemma());
            }
        }
        this.setTranslation(((pp_translate != null) ? pp_translate+" ":"") + ((vb_translate != null) ? vb_translate+" ":"") +((np_translate != null) ? np_translate+" ":"")+getTranslation());
        //System.out.println("***********************");
    }
}
