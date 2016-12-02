package com.example.luke.btquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    int i = 5;

    private Button student;
    private Button faculty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activitySwitch();
    }

    public void activitySwitch(){
        student = (Button) findViewById(R.id.studentButton);
        faculty = (Button) findViewById(R.id.facultyButton);

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Quiz.class);
                startActivity(intent);
            }
        });

        faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, CreateQuiz.class);
                startActivity(intent);
            }
        });

    }
}