package com.example.luke.btquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class CreateQuiz extends AppCompatActivity {

    //Declare Variables
    public Button createQuizButton;
    public TextView question1, question2, question3;
    public EditText question4, answerA, answerB, answerC, answerD; //Make your own
    public RadioButton aRadio, bRadio, cRadio, dRadio; //Make your own
    public CheckBox checkBox1, checkBox2, checkBox3,checkBox4;
    String question1String = "How many sides are there in a square?";
    String question2String = "Select the fastest animal?";
    String question3String = "How many feet are on a football field?";
    public boolean makeYourOwn;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //start onCreate
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);

        //Instantiate variables
        createQuizButton = (Button) findViewById(R.id.createQuizButton);

        question1 = (TextView) findViewById(R.id.question1);
        question2 = (TextView) findViewById(R.id.question2);
        question3 = (TextView) findViewById(R.id.question3);

        question4 = (EditText) findViewById(R.id.question4); //Make your own
        answerA = (EditText) findViewById(R.id.answerA);
        answerB = (EditText) findViewById(R.id.answerB);
        answerC = (EditText) findViewById(R.id.answerC);
        answerD = (EditText) findViewById(R.id.answerD);

        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
        checkBox4 = (CheckBox) findViewById(R.id.checkBox4);

        aRadio = (RadioButton) findViewById(R.id.aRadio);
        bRadio = (RadioButton) findViewById(R.id.bRadio);
        cRadio = (RadioButton) findViewById(R.id.cRadio);
        dRadio = (RadioButton) findViewById(R.id.dRadio);

        //Set up widgets
        String questionString = question1String +"<br><br>A. 1    B. 2     C. 3     <b>D. 4</b>";
        question1.setText(Html.fromHtml(questionString));
        questionString = question2String +"<br><br>A. Hippo     B. Giraffe     <b>C. Cheeta</b>     D. Mouse Rat";
        question2.setText(Html.fromHtml(questionString));
        questionString = question3String +"<br><br>A. 50     B. 100     C. 300     <b>D. 330</b>";
        question3.setText(Html.fromHtml(questionString));

        aRadio.setOnClickListener(new View.OnClickListener() { //start aRadio onClick
            @Override
            public void onClick(View view) {
                if(bRadio.isChecked())
                    bRadio.setChecked(false);
                if(cRadio.isChecked())
                    cRadio.setChecked(false);
                if(dRadio.isChecked())
                    dRadio.setChecked(false);
            }
        }); //end aRadio onClick

        bRadio.setOnClickListener(new View.OnClickListener() { //start bRadio onClick
            @Override
            public void onClick(View view) {
                if(aRadio.isChecked())
                    aRadio.setChecked(false);
                if(cRadio.isChecked())
                    cRadio.setChecked(false);
                if(dRadio.isChecked())
                    dRadio.setChecked(false);
            }
        }); //end bRadio onClick

        cRadio.setOnClickListener(new View.OnClickListener() { //start cRadio onClick
            @Override
            public void onClick(View view) {
                if(aRadio.isChecked())
                    aRadio.setChecked(false);
                if(bRadio.isChecked())
                    bRadio.setChecked(false);
                if(dRadio.isChecked())
                    dRadio.setChecked(false);
            }
        }); //end cRadio onClick

        dRadio.setOnClickListener(new View.OnClickListener() { //start dRadio onClick
            @Override
            public void onClick(View view) {
                if(aRadio.isChecked())
                    aRadio.setChecked(false);
                if(bRadio.isChecked())
                    bRadio.setChecked(false);
                if(cRadio.isChecked())
                    cRadio.setChecked(false);
            }
        }); //end dRadio onClick

        checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { //start make your own question checkbox
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    question4.setClickable(true);
                    aRadio.setClickable(true);
                    bRadio.setClickable(true);
                    cRadio.setClickable(true);
                    dRadio.setClickable(true);
                }
                else
                {
                    question4.setClickable(false);
                    aRadio.setClickable(false);
                    bRadio.setClickable(false);
                    cRadio.setClickable(false);
                    dRadio.setClickable(false);
                }
                makeYourOwn = b;

            }
        }); //end  make your own question checkbox

        createQuizButton.setOnClickListener(new View.OnClickListener() { //start createQuiz button onClick
            @Override
            public void onClick(View view) {
                //When button is clicked...
                String quiz2Send = new String("");
                boolean continueOn = true;

                if(checkBox1.isChecked())
                    quiz2Send += question1String +":1:2:3:4:4,";
                if(checkBox2.isChecked())
                    quiz2Send += question2String +":Hippo:Giraffe:Cheeta:Mouse Rat:Cheeta,";
                if(checkBox3.isChecked())
                    quiz2Send += question3String +":50:100:300:330:330,";
                if(makeYourOwn) {
                    String myoQuestion = question4.getText().toString();
                    if(myoQuestion == "")
                        continueOn = false;

                    String myoAnswer = new String("");

                    String answerCorrect = "";
                    if(aRadio.isChecked())
                        answerCorrect = answerA.getText().toString();
                    else if(bRadio.isChecked())
                        answerCorrect = answerB.getText().toString();
                    else if(cRadio.isChecked())
                        answerCorrect = answerC.getText().toString();
                    else if(dRadio.isChecked())
                        answerCorrect = answerD.getText().toString();
                    else
                        continueOn = false;

                    myoAnswer += ":" +answerA.getText().toString() +":" +answerB.getText().toString() +":" +answerC.getText().toString() +":" +answerD.getText().toString() +":" +answerCorrect;

                    if(myoAnswer == "")
                        continueOn = false;

                    quiz2Send += myoQuestion + myoAnswer;

                    if(quiz2Send == "")
                        continueOn = false;
                }

                Context c = getApplicationContext();
                if(continueOn) {
                    //Toast.makeText(c, "Quiz successfully created", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(CreateQuiz.this, FacultyConnect.class);
                    intent.putExtra("quiz", quiz2Send);
                    startActivity(intent);
                }
                else
                    Toast.makeText(c, "More information needed", Toast.LENGTH_LONG).show();
            }
        });  //end createQuiz button onClick
    }//end onCreate
}


//Bundle bundle = getIntent().getExtras();
//quiz = bundle.getString("quiz");