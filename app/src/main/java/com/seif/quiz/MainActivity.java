package com.seif.quiz;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.seif.quiz.model.Question;
import com.seif.quiz.model.QuestionType;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout_questions;
    ArrayList<Question> questions;
    ArrayList<View> question_views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepareQuestions();
        // get the listview
        linearLayout_questions = (LinearLayout) findViewById(R.id.linearlayout_questions);
        // preparing list data
        prepareQuestions();
        // set the data of questions inside the views
        setDataOnViews();
        // draw questions .. add data to linearlayout
        // ViewGroup viewGroup = new
        for (int i = 0; i < question_views.size(); i++) {
            linearLayout_questions.addView(question_views.get(i));
        }
    }

    private void prepareQuestions() {
        //listDataHeader = new ArrayList<>();
        questions = new ArrayList<>();
        //listDataChild = new HashMap<>();
        // get questions from server
        String Q_title = "xmlns stands for?";
        // listDataHeader.add(Q_title);
        questions.add(new Question(Q_title, QuestionType.SINGLE_CHOICE,
                new int[]{3}, new String[]{"Extensible Markup Language Numerous",
                "Execution Model Language Numerous",
                "Extensible Markup Language Namespaces",
                "Extensible Markup Language Android Names"}));

        Q_title = "Order of views is matter in Android XML layouts?";
        //listDataHeader.add(Q_title);
        questions.add(new Question(Q_title
                , QuestionType.SINGLE_CHOICE, new int[]{0}, new String[]{"Yes", "No"}));

        Q_title = "What's R class?";
        // listDataHeader.add(Q_title);
        questions.add(new Question(Q_title, QuestionType.TEXT));

        Q_title = "Function signature includes ..";
        // listDataHeader.add(Q_title);
        questions.add(new Question(Q_title, QuestionType.MULTIPLE_CHOICE,
                new int[]{1, 2}, new String[]{"Function name", "Input parameters",
                "Return value", "Access modifiers"}));


        Q_title = "Are padding and margin affecting by the same way on a view?";
        // listDataHeader.add(Q_title);
        questions.add(new Question(Q_title, QuestionType.SINGLE_CHOICE
                , new int[]{0}, new String[]{"Yes", "No"}));

        Q_title = "What's Gradle?";
        // listDataHeader.add(Q_title);
        questions.add(new Question(Q_title, QuestionType.TEXT));

        Q_title = "Are Fragments defined inside Manifest?";
        // listDataHeader.add(Q_title);
        questions.add(new Question(Q_title, QuestionType.SINGLE_CHOICE,
                new int[]{1}, new String[]{"Yes", "No"}));

    }

    private void setDataOnViews() {
        question_views = new ArrayList<>();
       /* LayoutInflater inflater;
        inflater = getLayoutInflater();*/
        for (Question question : questions) {

            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            TextView textView_title = new TextView(this);
            textView_title.setText(question.getTitle());
            textView_title.setTextColor(Color.RED);
            linearLayout.addView(textView_title);

            switch (question.getType()) {
                case TEXT: {
                    EditText editText = new EditText(this);
                    linearLayout.addView(editText);
                }
                break;
                case MULTIPLE_CHOICE:
                    for (String answer : question.getAnswers()) {
                        CheckBox checkBox = new CheckBox(this);
                        checkBox.setText(answer);
                        linearLayout.addView(checkBox);
                    }
                    break;
                case SINGLE_CHOICE:
                    RadioGroup radioGroup = new RadioGroup(this);
                    for (String answer : question.getAnswers()) {
                        RadioButton radioButton = new RadioButton(this);
                        radioButton.setText(answer);
                        radioGroup.addView(radioButton);
                    }
                    linearLayout.addView(radioGroup);
                    break;
            }
            question_views.add(linearLayout);
        }
    }

    public void submitAnswers(View view) {

    }
}

