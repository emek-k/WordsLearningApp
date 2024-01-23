package edu.pb.model.dictionary;

import edu.pb.model.words.Word;

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
//    public List<Word> getDetails() {
//        //List<Word> =
//        System.out.println("Język: " + name);
//        for (DifficultyLevel level : levels) {
//            level.getDetails();
//        }
//        return null;
//    }

    public List<Word> getDetails() {
        System.out.println("Język: " + name);
        List<Word> allWords = new ArrayList<>();
        for (DifficultyLevel level : levels) {
            allWords.addAll(level.getDetails());
        }
        return allWords;
    }


    public List<Word> getDetails(String difficulty) {
        System.out.println("Język: " + name);
        for (DifficultyLevel level : levels) {
            if (level.getName().equals(difficulty)) {
                level.getDetails();
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }
}