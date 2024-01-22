package edu.pb.model.words;

import edu.pb.model.dictionary.IDictionaryComponent;

public abstract class Word implements IDictionaryComponent {
    public String name;
    public String translation;
    protected int length;
    protected String definition;
    protected String difficulty;
    protected Word word;
    public String getName() {
        return name;
    }

    public String getTranslation() {
        return translation;
    }
    public String getDifficulty() {
        return difficulty;
    }

//    public Word (Word word) {
//        this.word = word;
//    }

    //docelowo nie uzywana
    @Override
    public String toString() {
        return "Word: " + this.name + ", Definition: " + this.definition + ", translation: " + this.translation + ", difficulty " + this.difficulty;
    }

    //docelowo uzywana
    @Override
    public void printDetails() {
        System.out.println("Słowo: " + this.name + ", Definition: " + this.definition + ", translation: " + this.translation + ", difficulty: " + this.difficulty);
    }

    public void printDetails(String extra) {
        System.out.println("parametr niedozwolony w wywołaniu printDetails dla elementu word");
    }

    public String getDefinition() {
        return definition;
    }
//    public void editWord(Word word) {
//        this.word = word;
//    }
//    public Word getWordDetails() {
//        return word;
//    }
}
