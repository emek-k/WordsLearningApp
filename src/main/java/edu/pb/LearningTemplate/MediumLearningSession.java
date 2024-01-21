package edu.pb.LearningTemplate;

import edu.pb.model.Dictionary;
import edu.pb.model.words.Word;

import java.util.*;

public class MediumLearningSession extends LearningSessionTemplate {

    public MediumLearningSession(Dictionary dictionary, String difficulty) {
        super(dictionary, difficulty);
    }

    @Override
    protected void performLearning(Word word) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Translate the word: " + word.getName());
        List<String> options = generateRandomOptions(word, 5);
        displayOptions(options);

        int userChoice = getUserChoice(scanner, options.size());
        String userAnswer = options.get(userChoice - 1);

        if (userAnswer.equals(word.getTranslation())) {
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect. The correct translation is: " + word.getTranslation());
        }

        saveProgress(); // Zapisz postęp po każdym pytaniu
    }

    @Override
    protected List<String> generateOptions(Word word, String difficulty) {
        List<Word> allWords = dictionary.getAllWordsByDifficulty(difficulty);
        List<String> options = new ArrayList<>();

        // Add the correct answer
        options.add(word.getTranslation());

        // Add random incorrect answers
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            Word randomWord = allWords.get(random.nextInt(allWords.size()));
            options.add(randomWord.getTranslation());
        }

        // Shuffle options to avoid always having the correct one in the same position
        Collections.shuffle(options);

        return options;
    }

    @Override
    protected void displayQuestion(Word word) {
        System.out.println("Translate the word: " + word.getName());
    }

    @Override
    protected void displayResult(boolean isCorrect, Word word) {
        if (isCorrect) {
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect. The correct translation is: " + word.getTranslation());
        }
    }
}
