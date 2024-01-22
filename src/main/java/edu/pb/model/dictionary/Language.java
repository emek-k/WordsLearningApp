package edu.pb.model.dictionary;

import java.util.ArrayList;
import java.util.List;

public class Language implements IDictionaryComponent {
    private String name;
    private List<DifficultyLevel> levels = new ArrayList<DifficultyLevel>();

    public Language(String name) {
        this.name = name;
    }

    public void addLevel(DifficultyLevel level) {
        levels.add(level);
    }

    @Override
    public void printDetails() {
        System.out.println("Język: " + name);
        for (DifficultyLevel level : levels) {
            level.printDetails();
        }
    }

    public void printDetails(String difficulty) {
        System.out.println("Język: " + name);
        for (DifficultyLevel level : levels) {
            if (level.getName().equals(difficulty)) {
                level.printDetails();
            }
        }
    }

    public String getName() {
        return name;
    }
}