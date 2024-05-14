package ntu.mssv63131717.Models;

public class Question {
    private String question,optionA,optionB,optionC,optionD,optionCorrect;

    public Question(String question, String optionA, String optionB, String optionC, String optionD, String optionCorrect) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.optionCorrect = optionCorrect;
    }

    public String getQuestion() {
        return question;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public String getOptionCorrect() {
        return optionCorrect;
    }
}
