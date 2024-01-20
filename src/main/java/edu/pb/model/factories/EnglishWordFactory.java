package edu.pb.model.factories;

import edu.pb.model.words.EnglishWord;
import edu.pb.model.words.Word;

public class EnglishWordFactory implements IWordFactory{

    @Override
    public String setWordDifficulty(String name) {
        if (name.length()>7){
            if (name.length()>12) return "hard";
            return "medium";
        }
        return "easy";
    }

    @Override
    public Word createWord(String name, String definition, String translations) {
        return new EnglishWord(name, definition, translations);
    }

//    @Override
//    public void deleteWord() {
//
//    }
//
//    @Override
//    public Word getWord() {
//        return null;
//    }
}
