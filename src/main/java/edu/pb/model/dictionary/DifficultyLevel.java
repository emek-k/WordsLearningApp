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
    public void printDetails() {
        System.out.println("Poziom trudności: " + level);
        for (Word word : words) {
            word.printDetails();
        }
    }

    public void printDetails(String wordName) {
        System.out.println("Poziom trudności: " + level);
        for (Word word : words) {
            if (word.getName().equals(wordName)) {
                word.printDetails();
            }
        }
    }

    public String getName() {
        return level;
    }
}