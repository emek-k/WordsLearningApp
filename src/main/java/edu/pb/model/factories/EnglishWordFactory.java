package edu.pb.model.factories;

import edu.pb.model.words.EnglishWord;
import edu.pb.model.words.Word;

public class EnglishWordFactory extends WordsFactory{

    @Override
    public String setWordDifficulty(String name) {
        if (name.length()>7){
            if (name.length()>11) return "hard";
            return "medium";
        }
        return "easy";
    }

    @Override
    public Word createWord(String name, String definition, String translations) {
        String difficulty = setWordDifficulty(name);
        EnglishWord word = new EnglishWord(name, definition, translations);
        word.setDifficulty(difficulty);
        return word;
    }
}
