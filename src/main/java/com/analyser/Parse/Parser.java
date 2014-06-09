package com.analyser.Parse;

/**
 * Created by cagil on 21/05/14.
 */

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.parser.lexparser.ParserQuery;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.ScoredObject;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    private final static String PCG_MODEL = "edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz";

    private final TokenizerFactory<CoreLabel> tokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(), "invertible=true");

    private final LexicalizedParser engParser = LexicalizedParser.loadModel(PCG_MODEL);

    public void parseSTMulti(String str) {
        List<CoreLabel> tokens = tokenize(str);
        List<List<CoreLabel>> sentences = new ArrayList<List<CoreLabel>>();
        sentences.add(tokens);
        List<Tree> results = engParser.parseMultiple(sentences,3);
        System.out.println(tokens.get(0));
        //if (results.size() == 1) return;
        //for (Tree result : results) { result.pennPrint(); }
        ParserQuery pq = engParser.parserQuery();
        pq.parse(tokens);
        System.out.println(pq.getKBestPCFGParses(4).size());
        for (ScoredObject<Tree> treeScoredObject : pq.getBestPCFGParses()) {
            //System.out.println(treeScoredObject.toString());
            //System.out.println(treeScoredObject.object().pennString());
            treeScoredObject.object().pennPrint();

        }


    }

    public Tree parseST(String str) {
        List<CoreLabel> tokens = tokenize(str);
        Tree tree = engParser.apply(tokens);
        return tree;
    }


    private List<CoreLabel> tokenize(String str) {
        Tokenizer<CoreLabel> tokenizer =
                tokenizerFactory.getTokenizer(
                        new StringReader(str));
        return tokenizer.tokenize();
    }

    public void parse(String str) {
        List<CoreLabel> tokens = tokenize(str);
        for (CoreLabel token : tokens) {
            System.out.println(token.get(CoreAnnotations.PartOfSpeechAnnotation.class));
            System.out.println(token.tag() + " , " + token.word());
        }

        return;
    }
}
