package edu.pb.LearningMemento;

import java.util.List;

public class LearningMemento {
    private final String difficulty;
    private final int progress;

    public LearningMemento(String difficulty, int progress) {
        this.difficulty = difficulty;
        this.progress = progress;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getProgress() {
        return progress;
    }
}