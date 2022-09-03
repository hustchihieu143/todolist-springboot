package hieuphan.oop;

import java.util.Date;

public class Student extends Person {

    public int score;

    public Student(int score, String name) {
        super(name);
        this.score = score;
    }
    public Student(){}

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
