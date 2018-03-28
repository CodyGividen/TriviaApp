package com.example.codygividen.triviaappandroid;


import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable{


    protected String mainQuestion;
    protected String correctAnswer;
    protected String wrongAnswer1;
    protected String getWrongAnswer2;
    protected String wrongAnswer3;

    public Question(String mainQuestion, String correctAnswer, String wrongAnswer1, String getWrongAnswer2, String wrongAnswer3) {
        this.mainQuestion = mainQuestion;
        this.correctAnswer = correctAnswer;
        this.wrongAnswer1 = wrongAnswer1;
        this.getWrongAnswer2 = getWrongAnswer2;
        this.wrongAnswer3 = wrongAnswer3;
    }

    protected Question(Parcel in) {
        mainQuestion = in.readString();
        correctAnswer = in.readString();
        wrongAnswer1 = in.readString();
        getWrongAnswer2 = in.readString();
        wrongAnswer3 = in.readString();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public String getMainQuestion() {
        return mainQuestion;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getWrongAnswer1() {
        return wrongAnswer1;
    }

    public String getGetWrongAnswer2() {
        return getWrongAnswer2;
    }

    public String getWrongAnswer3() {
        return wrongAnswer3;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(mainQuestion);
        dest.writeString(correctAnswer);
        dest.writeString(wrongAnswer1);
        dest.writeString(getWrongAnswer2);
        dest.writeString(wrongAnswer3);
    }
}
