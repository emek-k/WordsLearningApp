package edu.pb.model.dictionary;

import edu.pb.model.words.Word;

import java.util.ArrayList;
import java.util.List;

public class DifficultyLevel implements IDictionaryComponent {
    private String level;
    private List<Word> words = new ArrayList<Word>();

    public DifficultyLevel(String level) {
        this.level = level;
    }

    public void addWords(Word word) {
        words.add(word);
    }

    public void removeWord(String name) {
        Word wordToRemove = findWord(name);
        if (wordToRemove != null) {
            words.remove(wordToRemove);
            System.out.println("Word removed: " + name);
        } else {
            System.out.println("Word not found: " + name);
        }
    }

    public void updateWord(String name, Word newWord) {
        Word existingWord = findWord(name);
        if (existingWord != null) {
            int index = words.indexOf(existingWord);
            words.set(index, newWord);
            System.out.println("Word updated: " + name);
        } else {
            System.out.println("Word not found: " + name);
        }
    }

    public Word findWord(String name) {
        for (Word word : words) {
            if (word.getName().equals(name)) {
                return word;
            }
        }
        return null;
    }


    @Override
//    public List<Word> getDetails() {
//        System.out.println("Poziom trudności: " + level);
//        for (Word word : words) {
//            words.add((Word) word.getDetails());
//        }
//        return words;
//    }

    public List<Word> getDetails() {
        System.out.println("Poziom trudności: " + level);
        List<Word> allWords = new ArrayList<>();
        for (Word word : words) {
            allWords.addAll(word.getDetails());
        }
        return allWords;
    }


    public List<Word> getDetails(String wordName) {
        for (Word word : words) {
            if (word.getName().equals(wordName)) {
                words.add((Word) word.getDetails());
            }
        }
        return words;
    }

    public String getName() {
        return level;
    }
}