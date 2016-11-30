package com.example.luke.btquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class CreateQuiz extends AppCompatActivity {


    public Button createQuizButton;
    public TextView question1, question2, question3;
    public EditText question4, answerA, answerB, answerC, answerD, answerCorrect; //Make your own
    public CheckBox checkBox1, checkBox2, checkBox3,checkBox4;
    String question1String = "What is the blank of blank?";
    String question2String = "What much blank is in blank?";
    String question3String = "What is the blank on a blank?";

    @Override
    protected void onCreate(Bundle savedInstanceState) { //start onCreate
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);

        createQuizButton = (Button) findViewById(R.id.createQuizButton);

        question1 = (TextView) findViewById(R.id.question1);
        question2 = (TextView) findViewById(R.id.question2);
        question3 = (TextView) findViewById(R.id.question3);

        question4 = (EditText) findViewById(R.id.question4); //Make your own
        answerA = (EditText) findViewById(R.id.answerA);
        answerB = (EditText) findViewById(R.id.answerB);
        answerC = (EditText) findViewById(R.id.answerC);
        answerD = (EditText) findViewById(R.id.answerD);
        answerCorrect = (EditText) findViewById(R.id.answerCorrect);

        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
        checkBox4 = (CheckBox) findViewById(R.id.checkBox4);

        //Add section to fill questions
        String questionString = question1String +"<br><br><b>A. 1</b>  B. 2   C. 3   D. 4";
        question1.setText(Html.fromHtml(questionString));
        questionString = question2String +"<br><br>A. 1   <b>B. 2</b>   C. 3   D. 4";
        question2.setText(Html.fromHtml(questionString));
        questionString = question3String +"<br><br>A. 1   B. 2   <b>C. 3</b>   D. 4";
        question3.setText(Html.fromHtml(questionString));


        createQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //When button is clicked...
                String quiz2Send = new String("");
                String answers = "A. 1:B. 2:C. 3:D. 4";
                if(checkBox1.isChecked())
                    quiz2Send += question1String +":" +answers +":A,";
                if(checkBox2.isChecked())
                    quiz2Send += question2String +":" +answers +":B,";
                if(checkBox3.isChecked())
                    quiz2Send += question3String +":" +answers +":C,";
                if(checkBox4.isChecked()) {
                    String myoQuestion = question4.getText().toString();
                    String myoAnswer = new String("");
                    myoAnswer += ":" +answerA.getText().toString() +":" +answerB.getText().toString() +":" +answerC.getText().toString() +":" +answerD.getText().toString() +":" +answerCorrect.getText().toString();
                    quiz2Send += myoQuestion + myoAnswer;
                }

                //send quiz info over to student
                Log.e("",quiz2Send);

            }
        });

    }//end onCreate
}