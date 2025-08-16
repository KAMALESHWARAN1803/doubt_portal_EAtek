package com.doubtportal.model;

import jakarta.persistence.*;

@Entity
@Table(name = "doubts")
public class Doubt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String question;

    private String answer;

    private String askedBy;

    private String answeredBy;

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }

    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }

    public String getAskedBy() { return askedBy; }
    public void setAskedBy(String askedBy) { this.askedBy = askedBy; }

    public String getAnsweredBy() { return answeredBy; }
    public void setAnsweredBy(String answeredBy) { this.answeredBy = answeredBy; }
}
