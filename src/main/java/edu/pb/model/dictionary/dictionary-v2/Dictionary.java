package edu.pb.model.dictionary; //????

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import edu.pb.model.WordAdapter;
import edu.pb.model.words.EnglishWord;
import edu.pb.model.words.Word;
import edu.pb.model.factories.EnglishWordFactory;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Dictionary implements IDictionaryComponent{
    //out
    private Map<String, Word> words = new HashMap<>();

    private List<Language> supportedLanguages = new ArrayList<Language>();

    public Dictionary(){}

    public String getJsonRepresentation() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Word.class, new WordAdapter())
                .setPrettyPrinting()
                .create();

        //return gson.toJson(words.values()); // Zmieniono na values()
        //czy dziala po czystkach
        return null;
    }

    public Word getRandomWord() {
        List<String> wordKeys = new ArrayList<>(words.keySet());

        if (!wordKeys.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(wordKeys.size());
            String randomKey = wordKeys.get(randomIndex);
            return words.get(randomKey);
        }
        return null;
    }


    public Map<String, Word> getAllWords() {
        return new HashMap<>(words);
    }

    public List<Word> getAllWordsByDifficulty(String difficulty) {
        List<Word> filteredWords = new ArrayList<>();
        for (Word word : words.values()) {
            if (word.getDifficulty() != null && word.getDifficulty().equals(difficulty)) {
                filteredWords.add(word);
            }
        }
        return filteredWords;
    }

    public void add(Language lang) {
        supportedLanguages.add(lang);
    }

    @Override
//    public List<Word> getDetails(){
//        //
//        for (Language lang : supportedLanguages) {
//            lang.getDetails();
//        }
//        return null;
//    }

    public List<Word> getDetails(){
        List<Word> allWords = new ArrayList<>();
        for (Language lang : supportedLanguages) {
            allWords.addAll(lang.getDetails());
        }
        return allWords;
    }


    public List<Word> getDetails(String language) {
        //
        for (Language lang : supportedLanguages) {
            if (lang.getName().equals(language))
                lang.getDetails();
        }
        return null;
    }
}
