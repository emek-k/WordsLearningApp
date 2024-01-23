package edu.pb.LearningTemplate;

import edu.pb.LearningMemento.LearningMemento;
import edu.pb.model.dictionary.Dictionary;
import edu.pb.model.words.Word;

import java.util.*;

public abstract class LearningSessionTemplate {
    protected Dictionary dictionary;
    protected String difficulty;
    protected int progress;
    protected LearningMemento memento;
    public LearningSessionTemplate(Dictionary dictionary, String difficulty) {
        this.dictionary = dictionary;
        this.difficulty = difficulty;
        this.progress = 0;
        this.memento = new LearningMemento(difficulty, progress);
    }

    public List<LearningData> startLearningSession() {
        List<Word> words = dictionary.getAllWordsByDifficulty(difficulty);
        if (words.size() < 5) {
            throw new RuntimeException("Not enough words in the dictionary for the session");
        }
        List<LearningData> learningDataList = new ArrayList<>();
        for(int i=0; i<5; i++){
            Word word = words.get(i);
            LearningData data = performLearning(word);
            learningDataList.add(data);
            progress++;
        }
        return learningDataList;
    }

    protected abstract LearningData performLearning(Word word);

    protected List<String> generateRandomOptions(Word word, int numberOfOptions) {
        List<Word> allWords = dictionary.getAllWordsByDifficulty(difficulty);
        List<String> options = new ArrayList<>();

        // Add the correct answer
        options.add(word.getTranslation());

        Random random = new Random();
        for (int i = 0; i < numberOfOptions - 1; i++) {
            Word randomWord = allWords.get(random.nextInt(allWords.size()));
            options.add(randomWord.getTranslation());
        }

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
                scanner.next();
            }
            userChoice = scanner.nextInt();
        } while (userChoice < 1 || userChoice > maxOption);

        return userChoice;
    }

    protected abstract List<String> generateOptions(Word word, String difficulty);

    protected abstract void displayQuestion(Word word);

    protected abstract void displayResult(boolean isCorrect, Word word);
    public void saveProgress() {
        memento = new LearningMemento(difficulty, progress);
    }

    // Przywróć stan
    public void restoreProgress() {
        this.difficulty = memento.getDifficulty();
        this.progress = memento.getProgress();
    }
    public String getDifficulty() {
        return difficulty;
    }

    public int getProgress() {
        return progress;
    }

}