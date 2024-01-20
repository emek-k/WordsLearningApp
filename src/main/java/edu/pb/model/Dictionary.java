package edu.pb.model;

import edu.pb.model.words.Word;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
}