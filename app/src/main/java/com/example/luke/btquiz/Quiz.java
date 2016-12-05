package com.example.luke.btquiz;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.bluetooth.BluetoothAdapter;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;


public class Quiz extends AppCompatActivity {

    Intent discoverableIntent;
    public ProgressBar spinner;
    public TextView titleText, question1, question2, question3, question4;
    public RadioButton a1, a2, a3, a4, b1, b2, b3, b4, c1, c2, c3, c4, d1, d2, d3, d4;
    public Button b, mainMenuB;
    public TabHost tabHost;
    String answer1, answer2, answer3, answer4, userAnswer1, userAnswer2, userAnswer3, userAnswer4;
    public RadioGroup answers1,answers2,answers3,answers4;
    public int numOfQuestions;
    BluetoothAdapter mBluetoothAdapter;
    private BluetoothChatService mChatService = null;
    private String mConnectedDeviceName = null;

    public String scoreQuiz(){//start scoreQuiz
        double correct = 0;

        if(answers1.getCheckedRadioButtonId() != -1)
            userAnswer1 = ((RadioButton)findViewById(answers1.getCheckedRadioButtonId())).getText().toString();
        else
            userAnswer1 = "";
        if(answer1.equals(userAnswer1))
            correct += 1;
        if(answer2 != null) {
            if(answers2.getCheckedRadioButtonId() != -1)
                userAnswer2 = ((RadioButton)findViewById(answers2.getCheckedRadioButtonId())).getText().toString();
            else
                userAnswer2 = "";
            if (answer2.equals(userAnswer2))
                correct += 1;
        }
        if(answer3 != null) {
            if(answers3.getCheckedRadioButtonId() != -1)
                userAnswer3 = ((RadioButton) findViewById(answers3.getCheckedRadioButtonId())).getText().toString();
            else
                userAnswer3 = "";
            if (answer3.equals(userAnswer3))
                correct += 1;
        }
        if(answer4 != null)
        {
            if(answers4.getCheckedRadioButtonId() != -1)
                userAnswer4 = ((RadioButton)findViewById(answers4.getCheckedRadioButtonId())).getText().toString();
            else
                userAnswer4 = "";
            if(answer4.equals(userAnswer4))
                correct += 1;
        }

        double perc = (correct/numOfQuestions) * 100;
        return String.valueOf(perc) +"%"; //This returns 0.0 when the score doesn't equal 100%
    }//end scoreQuiz

    public void startQuiz()
    {//start startQuiz
        spinner.setVisibility(View.GONE);
        titleText.setText("Q u i z");
        b.setText("Submit Quiz");
        tabHost.setVisibility(View.VISIBLE);
    }//end startQuiz

    public void setUpActivity()
    {//start setUpActivity
        spinner = (ProgressBar) findViewById(R.id.waitingForConn);
        titleText = (TextView) findViewById(R.id.titleText);
        b = (Button) findViewById(R.id.button);
        mainMenuB = (Button) findViewById(R.id.mainMenuB);
        mainMenuB.setVisibility(View.GONE); //Starts out invisible
    }//end setUpActivity

    public void setupQuiz(String msg)
    {//start splitIntoQuiz
        String[] questions = msg.split(",");
        numOfQuestions = questions.length;

        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        answers1 = (RadioGroup)findViewById(R.id.answers1);

        TabHost.TabSpec spec = tabHost.newTabSpec("Q1");
        spec.setContent(R.id.Q1);
        spec.setIndicator("Q1");
        tabHost.addTab(spec);

        question1 = (TextView) findViewById(R.id.question1);
        a1 = (RadioButton) findViewById(R.id.a1);
        b1 = (RadioButton) findViewById(R.id.b1);
        c1 = (RadioButton) findViewById(R.id.c1);
        d1 = (RadioButton) findViewById(R.id.d1);

        String[] parts = questions[0].split(":");

        question1.setText(parts[0]);
        a1.setText(parts[1]);
        b1.setText(parts[2]);
        c1.setText(parts[3]);
        d1.setText(parts[4]);
        answer1 = parts[5];

        if(numOfQuestions >= 2) {
            answers2 = (RadioGroup)findViewById(R.id.answers2);
            spec = tabHost.newTabSpec("Q2");
            spec.setContent(R.id.Q2);
            spec.setIndicator("Q2");
            tabHost.addTab(spec);

            question2 = (TextView) findViewById(R.id.question2);
            a2 = (RadioButton) findViewById(R.id.a2);
            b2 = (RadioButton) findViewById(R.id.b2);
            c2 = (RadioButton) findViewById(R.id.c2);
            d2 = (RadioButton) findViewById(R.id.d2);

            parts = questions[1].split(":");

            question2.setText(parts[0]);
            a2.setText(parts[1]);
            b2.setText(parts[2]);
            c2.setText(parts[3]);
            d2.setText(parts[4]);
            answer2 = parts[5];
        }

        if(numOfQuestions >= 3) {
            answers3 = (RadioGroup)findViewById(R.id.answers3);
            spec = tabHost.newTabSpec("Q3");
            spec.setContent(R.id.Q3);
            spec.setIndicator("Q3");
            tabHost.addTab(spec);

            question3 = (TextView) findViewById(R.id.question3);
            a3 = (RadioButton) findViewById(R.id.a3);
            b3 = (RadioButton) findViewById(R.id.b3);
            c3 = (RadioButton) findViewById(R.id.c3);
            d3 = (RadioButton) findViewById(R.id.d3);

            parts = questions[2].split(":");

            question3.setText(parts[0]);
            a3.setText(parts[1]);
            b3.setText(parts[2]);
            c3.setText(parts[3]);
            d3.setText(parts[4]);
            answer3 = parts[5];
        }

        if(numOfQuestions >= 4) {
            answers4 = (RadioGroup)findViewById(R.id.answers4);
            spec = tabHost.newTabSpec("Q4");
            spec.setContent(R.id.Q4);
            spec.setIndicator("Q4");
            tabHost.addTab(spec);

            question4 = (TextView) findViewById(R.id.question4);
            a4 = (RadioButton) findViewById(R.id.a4);
            b4 = (RadioButton) findViewById(R.id.b4);
            c4 = (RadioButton) findViewById(R.id.c4);
            d4 = (RadioButton) findViewById(R.id.d4);

            parts = questions[3].split(":");

            question4.setText(parts[0]);
            a4.setText(parts[1]);
            b4.setText(parts[2]);
            c4.setText(parts[3]);
            d4.setText(parts[4]);
            answer4 = parts[5];
        }

        tabHost.setVisibility(View.GONE); //Starts out invisible
    }//end splitIntoQuiz

    @Override
    protected void onCreate(Bundle savedInstanceState) {//start onCreate
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        setUpActivity();

        // enable discoverability
        discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        startActivity(discoverableIntent);

        mChatService = new BluetoothChatService(this, mHandler);
        //mChatService.start();

        //This is the string that you get from faculty
        //The tabs are suppose to by added as you add questions but it's not working for more than 2 questions right now.
        String receivedMessage = "How many sides are there in a square?:1:2:3:4:4,Select the fastest animal:Hippo:Giraffe:Cheeta:Mouse Rat:Cheeta,How many feet are on a football field?:50:100:300:330:330";
        setupQuiz(receivedMessage);

        b.setOnClickListener(new View.OnClickListener() { //start quiz button listener
            @Override
            public void onClick(View view) {
                Context c = getApplicationContext();

                if(spinner.getVisibility() == View.VISIBLE) {
                    startQuiz();
                    Toast.makeText(c, "Your quiz has started", Toast.LENGTH_LONG).show();
                }
                else if(spinner.getVisibility() == View.GONE) {
                    tabHost.setVisibility(View.GONE);
                    b.setVisibility(View.GONE);
                    Toast.makeText(c, "Quiz submitted", Toast.LENGTH_LONG).show();
                    String score = scoreQuiz();
                    titleText.setText("Your score is: " +score);
                    mainMenuB.setVisibility(View.VISIBLE);
                }
            }
        });//start quiz button listener

        mainMenuB.setOnClickListener(new View.OnClickListener() {//mainMenuB onClickListener
            @Override
            public void onClick(View view) {
                //This button can be used to send the results back to the prof
                Context c = getApplicationContext();
                Toast.makeText(c, "Quiz submitted", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Quiz.this, MainActivity.class);
                startActivity(intent);
            }
        });//mainMenuB onClickListener
    }//end onCreate

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Activity activity = new Activity();
            switch (msg.what) {
                case Constants.MESSAGE_STATE_CHANGE:
                    switch (msg.arg1) {
                        case BluetoothChatService.STATE_CONNECTED:
                            //mConversationArrayAdapter.clear();
                            break;
                        case BluetoothChatService.STATE_CONNECTING:
                            break;
                        case BluetoothChatService.STATE_LISTEN:
                        case BluetoothChatService.STATE_NONE:
                            break;
                    }
                    break;
//                case Constants.MESSAGE_WRITE:
//                    byte[] writeBuf = (byte[]) msg.obj;
//                    // construct a string from the buffer
//                    String writeMessage = new String(writeBuf);
//                    mConversationArrayAdapter.add("Me:  " + writeMessage);
//                    break;
                case Constants.MESSAGE_READ:
                    byte[] readBuf = (byte[]) msg.obj;
                    // construct a string from the valid bytes in the buffer
                    String readMessage = new String(readBuf, 0, msg.arg1);
                    setupQuiz(readMessage);
                    break;
                case Constants.MESSAGE_DEVICE_NAME:
                    // save the connected device's name
                    mConnectedDeviceName = msg.getData().getString(Constants.DEVICE_NAME);
                    if (null != activity) {
                        Toast.makeText(getApplicationContext(), "Connected to "
                                + mConnectedDeviceName, Toast.LENGTH_SHORT).show();
                    }
                    break;
                case Constants.MESSAGE_TOAST:
                    if (null != activity) {
                        Toast.makeText(getApplicationContext(), msg.getData().getString(Constants.TOAST),
                                Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };
}