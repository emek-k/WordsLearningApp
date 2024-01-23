package edu.pb.LearningTemplate;

import edu.pb.model.dictionary.Dictionary;
import edu.pb.model.words.Word;

import java.util.*;

public class HardLearningSession extends LearningSessionTemplate {

    public HardLearningSession(Dictionary dictionary, String difficulty) {
        super(dictionary, difficulty);
    }

    @Override
    protected LearningData performLearning(Word word) {
        List<String> options = generateRandomOptions(word, 5);

        LearningData data = new LearningData();
        data.setQuestion("Translate the word: " + word.getName());
        data.setOptions(options);
        data.setCorrectAnswer(word.getTranslation());

        return data;
    }

    @Override
    protected List<String> generateOptions(Word word, String difficulty) {
        List<Word> allWords = dictionary.getAllWordsByDifficulty(difficulty);
        List<String> options = new ArrayList<>();

        options.add(word.getTranslation());

        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            Word randomWord = allWords.get(random.nextInt(allWords.size()));
            options.add(randomWord.getTranslation());
        }

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
