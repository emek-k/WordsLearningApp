package edu.pb.LearningTemplate;

import edu.pb.model.Dictionary;
import edu.pb.model.words.Word;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class HardLearningSession extends LearningSessionTemplate {

    public HardLearningSession(Dictionary dictionary, String difficulty) {
        super(dictionary, difficulty);
    }

    @Override
    protected void performLearning(Word word) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Translate the word: " + word.getTranslation());
        String userAnswer = scanner.nextLine();

        if (userAnswer.equalsIgnoreCase(word.getName())) {
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect. The correct translation is: " + word.getName());
        }

        saveProgress(); // Zapisz postęp po każdym pytaniu
    }

    @Override
    protected List<String> generateOptions(Word word, String difficulty) {
        List<Word> allWords = dictionary.getAllWordsByDifficulty(difficulty);
        List<String> options = new ArrayList<>();

        // Add the correct answer
        options.add(word.getName());

        // Shuffle options to avoid always having the correct one in the same position
        Collections.shuffle(options);

        return options;
    }

    @Override
    protected void displayQuestion(Word word) {
        System.out.println("Translate the word: " + word.getTranslation());
    }

    @Override
    protected void displayResult(boolean isCorrect, Word word) {
        if (isCorrect) {
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect. The correct translation is: " + word.getName());
        }
    }
}
