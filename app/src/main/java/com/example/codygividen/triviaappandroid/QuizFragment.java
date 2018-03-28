package com.example.codygividen.triviaappandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.codygividen.triviaappandroid.MainActivity.QUESTION_LIST;

public class QuizFragment extends Fragment {

    @BindView(R.id.quiz_question_textview)
    protected TextView quizQuestion;
    @BindView(R.id.answer_one_button)
    protected Button answerOneButton;
    @BindView(R.id.answer_two_button)
    protected Button answerTwoButton;
    @BindView(R.id.answer_three_button)
    protected Button answerThreeButton;
    @BindView(R.id.answer_four_button)
    protected Button answerFourButton;
    private QuizCallBack quizCallback;
    private List<Question>questionsList;
    private Question question;
    private int questionListPosition = 0;
    private int correctAnswers = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public static QuizFragment newInstance() {

        Bundle args = new Bundle();

        QuizFragment fragment = new QuizFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onStart() {
        super.onStart();
        questionsList = new ArrayList<>();
        questionsList = getArguments().getParcelableArrayList(QUESTION_LIST);
        populateQuizContent();
    }
    private void populateQuizContent(){
        question = questionsList.get(questionListPosition);
        quizQuestion.setText(question.getMainQuestion());
        List<Button> buttonList = new ArrayList<>();
        buttonList.add(answerOneButton);
        buttonList.add(answerTwoButton);
        buttonList.add(answerThreeButton);
        buttonList.add(answerFourButton);
        List<String> possibleAnswerList = new ArrayList<>();
        possibleAnswerList.add(question.getCorrectAnswer());
        possibleAnswerList.add(question.getWrongAnswer1());
        possibleAnswerList.add(question.getGetWrongAnswer2());
        possibleAnswerList.add(question.getWrongAnswer3());

        for (Button button : buttonList) {
            int random = (int) (Math.random() * possibleAnswerList.size() - 1);
            button.setText(possibleAnswerList.get(random));
            possibleAnswerList.remove(random);
        }
    }
    @OnClick(R.id.answer_one_button)
    protected  void buttonOneClicked(){
        checkAnswers(answerOneButton.getText().toString());
    }
    @OnClick(R.id.answer_two_button)
    protected  void buttonTwoClicked(){
        checkAnswers(answerTwoButton.getText().toString());
    }
    @OnClick(R.id.answer_three_button)
    protected  void buttonThreeClicked(){
        checkAnswers(answerThreeButton.getText().toString());
    }
    @OnClick(R.id.answer_four_button)
    protected  void buttonFourClicked(){
        checkAnswers(answerFourButton.getText().toString());
    }

    private void checkAnswers(String answer){
        questionListPosition++;
        if(question.getCorrectAnswer().equals(answer)){
            quizQuestion.setText("correct!!");
            correctAnswers++;
            answerOneButton.setEnabled(false);
            answerTwoButton.setEnabled(false);
            answerThreeButton.setEnabled(false);
            answerFourButton.setEnabled(false);
        }else{
            quizQuestion.setText(getString(R.string.wrong_answer_text, question.getCorrectAnswer()));
            answerOneButton.setEnabled(false);
            answerTwoButton.setEnabled(false);
            answerThreeButton.setEnabled(false);
            answerFourButton.setEnabled(false);
        }


    }


    @OnClick(R.id.next_button)
    protected   void nextButtonClicked(){
        if(questionListPosition <= questionsList.size() - 1){
            populateQuizContent();
            answerOneButton.setEnabled(true);
            answerTwoButton.setEnabled(true);
            answerThreeButton.setEnabled(true);
            answerFourButton.setEnabled(true);
        }else{
            quizCallback.quizFinished(correctAnswers);
            answerOneButton.setEnabled(true);
            answerTwoButton.setEnabled(true);
            answerThreeButton.setEnabled(true);
            answerFourButton.setEnabled(true);
        }
    }

    public interface QuizCallBack{
        void quizFinished(int correctAnswers);
    }
    public void attachView(QuizCallBack quizCallBack){
        this.quizCallback = quizCallBack;
    }
}
