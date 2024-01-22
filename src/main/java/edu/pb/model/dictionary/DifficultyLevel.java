package edu.pb.model.dictionary;

import java.util.ArrayList;
import java.util.List;

public class DifficultyLevel implements IDictionaryComponent {
    private String level;
    private List<IDictionaryComponent> words = new ArrayList<>();

    public DifficultyLevel(String level) {
        this.level = level;
    }

    public void addWords(IDictionaryComponent word) {
        words.add(word);
    }

    @Override
    public void printDetails() {
        System.out.println("Poziom trudności: " + level);
        for (IDictionaryComponent word : words) {
            word.printDetails();
        }
    }
}