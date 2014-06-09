package com.analyser.Base;

import com.analyser.Lexicons.Utils;
import com.analyser.Parse.Parser;
import edu.stanford.nlp.trees.Tree;

import java.util.List;

/**
 * Created by cagil on 22/05/14.
 */
public class ttProcessor {
    Tree root;
    ttSentence sentence;
    String text;
    String [] plurals = null;
    public ttProcessor(Tree tree) {
        this.root = tree;
    }

    public ttProcessor(String text, Parser parser) {
        setText(text);
        this.root = parser.parseST(this.text);
        //root.firstChild().pennPrint();
    }

    public void process(){
        List<Tree> childrenAsList = this.root.getChildrenAsList();
        for (Tree tree : childrenAsList) {
            if(tree.label().value().equals("S")) {
                //System.out.println(  tree.toString());
                this.sentence = new ttSentence(tree,text);
                this.sentence.process();
                //System.out.println(text);
                //System.out.println(this.sentence.toString());
                this.sentence.translate();
                System.out.println(this.sentence.getTranslation()+": "+text);
            }
            else if(tree.label().value().equals("FRAG")) {
                System.out.println("TO DO[1]:"+tree.toString());
            }else if(tree.label().value().equals("NP")) {
                System.out.println("Error[NP not S]:"+tree.toString());
            }
            else
                System.out.println("Error[3]:"+tree.toString());
        }

    }


    public void setText(String text) {
        String firstWord = text.split(" ")[0];
        if(Utils.isAmbigiousVerb(firstWord))
            text = "Please ".concat(text);
        plurals = Utils.haveAmbigiousNoun(text);

        for (String plural : plurals) {
            text = text.replace(plural,plural.substring(0,plural.length()-1));
            //System.out.println("Plural "+plural+" : "+text);
        }

        this.text = text;
    }
}
