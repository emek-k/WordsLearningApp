package edu.pb;

import edu.pb.model.dictionary.Dictionary;
import edu.pb.model.Test;
import edu.pb.model.dictionary.DifficultyLevel;
import edu.pb.model.dictionary.IDictionaryComponent;
import edu.pb.model.dictionary.Language;
import edu.pb.model.factories.EnglishWordFactory;
import edu.pb.model.factories.WordsFactory;
import edu.pb.model.words.Word;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ModelMain {
    public static void main(String[] args) {

        Set<String> supportedLanguages = new HashSet<>(Arrays.asList("English", "Polish", "German"));

        Dictionary dictionary = new Dictionary(supportedLanguages);
        String language = "English";
        WordsFactory factory = null;
        if (language == "English") {
            factory = new EnglishWordFactory();
        }

        dictionary.addWord("apple", factory.createWord("apple", "A fruit", "jablko"));
        //dictionary.addWord("jabłko", factory.createWord("jabłko", "Owoc", "apple"));

        Word word = dictionary.findWord("apple");
        if (word != null) {
            System.out.println("Found word: " + word.name + " meaning " + word.translation);
        }

        dictionary.populateDictionary("src/main/java/edu/pb/model/english_polish.txt");
        Test test = new Test(dictionary);
        test.generateTest("easy");
        test.displayTest();

        //IDictionaryComponent dict = (IDictionaryComponent) new Object();
        //Language langEN = new Language("English");
        //dict.add(langEN);
        //DifficultyLevel easy = new DifficultyLevel("easy");
        //easy.addWords();
        //dict.printDetails();
    }

    //if language == english:
    //WordsFactory factory= new EnglishWordFactory

    //else if lang==german:
    //factory = new german...

    //factory.createWord()

    //jak chcemy dodac nowa klase to latwo
}
