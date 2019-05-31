package akhilesh.speechtotext;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    Button b;
    TextView t;
    private final int REQ_CODE_SPEECH_INPUT = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bollhalke();
        b = findViewById(R.id.button);
        t = findViewById(R.id.textView);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bollhalke();
            }
        });
    }





    private void bollhalke() {                //intent is used for transferring the data frm one activity to another activity//purpule
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH); //Perform an action for speech(voice) recognization
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM); //supports multiple language and  maintain langauage free from= lang_model_freefrm
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());   //using default system language
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "hi speak something"); //Displaying additional msg on the recognization
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT); // we pass object of the intent and pass an object in start activity for result
            // start activity for result method is followed by on_Activity_result method
            // req code is variable it req value according

        } catch  (ActivityNotFoundException e) {
            e.printStackTrace();
        }

    }
//receving the speech
    @Override //intent data stores the voice input as it recognizes it store in the intent object in the
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {

                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS); //collection of string as apeec is input it uses object
                    t.setText(result.get(0));
                }
            }
        }
    }
}

