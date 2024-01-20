package edu.pb.model.words;

public abstract class Word implements IWord{
    //protected String language;
    public String name;
    public String translation;
    protected int length;
    protected String definition;
    protected String difficulty;

    public String getDifficulty() {
        return difficulty;
    }

    @Override
    public String toString() {
        return "Word: " + this.name + ", Definition: " + this.definition + ", translation: " + this.translation; // + inne pola, jeśli to konieczne
    }
//    public void editWord(Word word) {
//        this.word = word;
//    }
//    public Word getWordDetails() {
//        return word;
//    }
}
