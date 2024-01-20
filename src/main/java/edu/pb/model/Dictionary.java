package edu.pb.model;

import edu.pb.model.words.EnglishWord;
import edu.pb.model.words.Word;

import java.io.*;
import java.util.*;

public class Dictionary {
    private Map<String, Word> words;
    private Set<String> supportedLanguages;

    public Dictionary(Set<String> supportedLanguages) {
        this.words = new HashMap<>();
        this.supportedLanguages = supportedLanguages;
    }

    public void addWord(String key, Word word) {
        words.put(key, word);
    }

    public void removeWord(String key) {
        words.remove(key);
    }

    public Word findWord(String key) {
        return words.get(key);
    }

    public void updateWord(String key, Word newWord) {
        words.put(key, newWord);
    }

    public Map<String, Word> getAllWords() {
        return new HashMap<>(words);
    }

//    public void add(Word word){
//        words.put(word);
//    }
    public List<Word> getAllWordsByDifficulty(String difficulty) {
        List<Word> filteredWords = new ArrayList<>();
        for (Word word : words.values()) {
            if (word.getDifficulty()!=null && word.getDifficulty().equals(difficulty)) {
                filteredWords.add(word);
            }
        }
        return filteredWords;
    }

    public void populateDictionary(){
        String path = "src/main/java/edu/pb/model/english_polish.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" - ");
                if (parts.length == 2) {
                    String name = parts[0];
                    String translations = parts[1];
                    words.put(name, new EnglishWord(name, "definition here", translations));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}