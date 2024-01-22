package edu.pb.LearningTemplate;

import java.util.List;

public class LearningData {
    private String question;
    private List<String> options;
    private String correctAnswer;

    public void setQuestion(String question){
        this.question = question;
    }
    public String getQuestion(){
        return question;
    }
    public void setOptions(List<String> options){
        this.options = options;
    }
    public List<String> getOptions(){
        return this.options;
    }
    public void setCorrectAnswer(String correctAnswer){
        this.correctAnswer = correctAnswer;
    }
    public String getCorrectAnswer(){
        return this.correctAnswer;
    }
}
