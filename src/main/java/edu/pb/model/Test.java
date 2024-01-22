package edu.pb.model;

import edu.pb.model.dictionary.Dictionary;
import edu.pb.model.words.Word;

import java.util.*;

public class Test {
    private ArrayList<Word> questions;
    private edu.pb.model.dictionary.Dictionary dictionary;

    public Test(Dictionary dictionary) {
        this.dictionary = dictionary;
        this.questions = new ArrayList<>();
    }

    public void generateTest(String difficulty){
        int questionsNumber = 15;
        Random generator = new Random();
        List<Word> allWords = dictionary.getAllWordsByDifficulty(difficulty);
        //Map<String, Word> allWords = dictionary.getAllWords();

        for (int i = 0; i < questionsNumber; i++) {
            Word randomWord = allWords.get(generator.nextInt(allWords.size()));
            questions.add(randomWord);
        }
    }

    public void displayTest(){
        for (Object element: questions)
            System.out.println(element);
    }
}
