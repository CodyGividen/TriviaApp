package com.example.codygividen.triviaappandroid;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements QuestionCreatorFragment.Callback, QuizFragment.QuizCallBack {

    private QuestionCreatorFragment questionCreatorFragment;
    private QuizFragment quizFragment;
    private List<Question> questionList;
    public static final String QUESTION_LIST = "question_list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        questionList = new ArrayList<>();
    }

    @OnClick(R.id.add_question_button)
    protected void addQuestionClicked(View view){
        questionCreatorFragment = QuestionCreatorFragment.newInstance();
        questionCreatorFragment.attachView( this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, questionCreatorFragment).commit();
    }
    @OnClick(R.id.take_quiz_button)
    protected void takeQuiz(View view){
        quizFragment = QuizFragment.newInstance();
        quizFragment.attachView(this);
        if(questionList.isEmpty()){
            Toast.makeText(this, "You need to add questions first.", Toast.LENGTH_SHORT).show();
        }else {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, quizFragment).commit();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(QUESTION_LIST, (ArrayList<? extends Parcelable>) questionList);
            quizFragment.setArguments(bundle);
        }
    }
    @OnClick(R.id.delete_quiz_button)
    protected void deleteQuizClicked(){
        if (questionList.isEmpty()) {
            Toast.makeText(this, "There is not a quiz to delete.", Toast.LENGTH_SHORT).show();
        }else{

        }

    }

    @Override
    public void saveQuestion(Question question) {
        questionList.add(question);
        Toast.makeText(this,"Question Added",Toast.LENGTH_LONG).show();
        getSupportFragmentManager().beginTransaction().remove(questionCreatorFragment).commit();
    }

    @Override
    public void quizFinished(int correctAnswers) {

    }
}
