package edu.pb;

import edu.pb.model.Dictionary;
import edu.pb.model.Test;
import edu.pb.model.factories.EnglishWordFactory;
import edu.pb.model.factories.IWordFactory;
import edu.pb.model.factories.PolishWordFactory;
import edu.pb.model.words.Word;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

//        Set<String> set = new HashSet<>(Arrays.asList("pociag", "zug", "costam"));
//        IWordFactory factory = new EnglishWordFactory();
//        Word word = factory.createWord("train", "srodek lokomocji", "pociag");
//
//        System.out.println(word.name);

        Set<String> supportedLanguages = new HashSet<>(Arrays.asList("English", "Polish", "German"));

        Dictionary dictionary = new Dictionary(supportedLanguages);
        IWordFactory englishFactory = new EnglishWordFactory();
        IWordFactory polishFactory = new PolishWordFactory(); // Zakładając, że istnieje taka klasa

        dictionary.addWord("apple", englishFactory.createWord("apple", "A fruit", "jablko"));
        dictionary.addWord("jabłko", polishFactory.createWord("jabłko", "Owoc", "apple"));

        Word word = dictionary.findWord("apple");
        if (word != null) {
            System.out.println("Found word: " + word.name + " meaning " + word.translation);
        }

        dictionary.populateDictionary();
        Test test = new Test(dictionary);
        test.generateTest("easy");
        test.displayTest();
    }

    //if language == english:
    //WordsFactory factory= new EnglishWordFactory

    //else if lang==german:
    //factory = new german...

    //factory.createWord()

    //jak chcemy dodac nowa klase to latwo
}
