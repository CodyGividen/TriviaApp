package com.example.codygividen.triviaappandroid;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuestionCreatorFragment extends Fragment {
    private Callback callback;

    @BindView(R.id.correct_answer_edittext)
    protected EditText correct;
    @BindView(R.id.first_wrong_answer_edittext)
    protected EditText wrongAnswer1;
    @BindView(R.id.second_wrong_answer_edittext)
    protected EditText wrongAnswer2;
    @BindView(R.id.third_wrong_answer_edittext)
    protected EditText wrongAnswer3;
    @BindView(R.id.input_question)
    protected EditText quizQuestion;

    public static QuestionCreatorFragment newInstance() {
        QuestionCreatorFragment fragment = new QuestionCreatorFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question_creator, container, false);
        ButterKnife.bind(this, view);
        return view;
    }



    public void attachView(Callback callback) {
        this.callback = callback;
    }



    @OnClick(R.id.save_question_button)
    protected void saveQuestionClicked() {
        if (quizQuestion.getText().toString().isEmpty()
                || correct.getText().toString().isEmpty()
                || wrongAnswer1.getText().toString().isEmpty()
                || wrongAnswer2.getText().toString().isEmpty()
                || wrongAnswer3.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "Try Again, please fill in all options!!",Toast.LENGTH_LONG).show();
        }else {
            String questionTitle = quizQuestion.getText().toString();
            String correctAnswer = correct.getText().toString();
            String firstWrongAnswer = wrongAnswer1.getText().toString();
            String secondWrongAnswer = wrongAnswer2.getText().toString();
            String thirdWrongAnswer = wrongAnswer3.getText().toString();

            Question question = new Question(questionTitle, correctAnswer, firstWrongAnswer, secondWrongAnswer, thirdWrongAnswer);
            callback.saveQuestion(question);
        }

    }

    public interface Callback {
        void saveQuestion(Question question);
    }
}
