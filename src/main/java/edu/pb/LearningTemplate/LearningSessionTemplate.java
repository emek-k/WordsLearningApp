package edu.pb.LearningTemplate;

import edu.pb.model.Dictionary;
import edu.pb.model.words.Word;

import java.util.*;

public abstract class LearningSessionTemplate {
    protected Dictionary dictionary;
    protected String difficulty;

    public LearningSessionTemplate(Dictionary dictionary, String difficulty) {
        this.dictionary = dictionary;
        this.difficulty = difficulty;
    }

    public void startLearningSession() {
        List<Word> words = dictionary.getAllWordsByDifficulty(difficulty);

        for (Word word : words) {
            performLearning(word);
        }
    }

    protected abstract void performLearning(Word word);

    protected List<String> generateRandomOptions(Word word, int numberOfOptions) {
        List<Word> allWords = dictionary.getAllWordsByDifficulty(difficulty);
        List<String> options = new ArrayList<>();

        // Add the correct answer
        options.add(word.getTranslation());

        // Add random incorrect answers
        Random random = new Random();
        for (int i = 0; i < numberOfOptions - 1; i++) {
            Word randomWord = allWords.get(random.nextInt(allWords.size()));
            options.add(randomWord.getTranslation());
        }

        // Shuffle options to avoid always having the correct one in the same position
        Collections.shuffle(options);

        return options;
    }

    protected void displayOptions(List<String> options) {
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }

    protected int getUserChoice(Scanner scanner, int maxOption) {
        int userChoice;

        do {
            System.out.print("Enter your choice (1-" + maxOption + "): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Consume the invalid input
            }
            userChoice = scanner.nextInt();
        } while (userChoice < 1 || userChoice > maxOption);

        return userChoice;
    }

    protected abstract List<String> generateOptions(Word word, String difficulty);

    protected abstract void displayQuestion(Word word);

    protected abstract void displayResult(boolean isCorrect, Word word);
}