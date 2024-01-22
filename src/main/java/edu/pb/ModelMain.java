package edu.pb;

import edu.pb.model.FileLineIterator;
import edu.pb.model.dictionary.Dictionary;
import edu.pb.model.Test;
import edu.pb.model.dictionary.DifficultyLevel;
import edu.pb.model.dictionary.IDictionaryComponent;
import edu.pb.model.dictionary.Language;
import edu.pb.model.factories.EnglishWordFactory;
import edu.pb.model.factories.WordsFactory;
import edu.pb.model.words.Word;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ModelMain {
    public static void main(String[] args) {

        //Set<String> supportedLanguages = new HashSet<>(Arrays.asList("English", "Polish", "German"));

//        Dictionary dictionary = new Dictionary();
        String language = "English";
        WordsFactory factory = null;
        if (language == "English") {
            factory = new EnglishWordFactory();
        }
//
//        dictionary.addWord("apple", factory.createWord("apple", "A fruit", "jablko"));
//        //dictionary.addWord("jabłko", factory.createWord("jabłko", "Owoc", "apple"));
//
//        Word word = dictionary.findWord("apple");
//        if (word != null) {
//            System.out.println("Found word: " + word.name + " meaning " + word.translation);
//        }
//
//        dictionary.populateDictionary("src/main/java/edu/pb/model/english_polish.txt");
//        Test test = new Test(dictionary);
//        test.generateTest("easy");
        //test.displayTest();

        Dictionary dict = new Dictionary();
        Language langEN = new Language("English");
        dict.add(langEN);
        DifficultyLevel easy = new DifficultyLevel("easy");
        DifficultyLevel medium = new DifficultyLevel("medium");
        DifficultyLevel hard = new DifficultyLevel("hard");
        langEN.addLevel(easy);
        langEN.addLevel(medium);
        langEN.addLevel(hard);
//        for(Word element : dictionary.getAllWords().values()){
//            easy.addWords(element);
//        }

        String path = "src/main/java/edu/pb/model/english_polish.txt";

        try (FileLineIterator it = new FileLineIterator(path)) {
            while (it.hasNext()) {
                String line = it.next();
                String[] parts = line.split(" - ");
                if (parts.length == 2) {
                    String name = parts[0];
                    String translations = parts[1];
                    Word word = factory.createWord(name, "definition here", translations);
                    if (word.getDifficulty().equals("easy")) {
                        easy.addWords(word);
                    }
                    else if (word.getDifficulty().equals("medium")) {
                        medium.addWords(word);
                    }
                    else if (word.getDifficulty().equals("hard")) {
                        hard.addWords(word);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        dict.printDetails();

        //langEN.printDetails("easy");
    }
}
