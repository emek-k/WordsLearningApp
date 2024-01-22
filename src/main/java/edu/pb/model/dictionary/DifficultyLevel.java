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
            if (word.getDifficulty().equals(level)) {
                word.printDetails(wordName);
            }
        }
    }

    public String getName() {
        return level;
    }
}