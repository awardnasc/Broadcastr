package com.test.alexward.broadcastr;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.test.alexward.broadcastr.R.string.save;

public class MainActivity extends Activity {

    private Button saveButton;
    private EditText phone, text;
    private String phoneList, textMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phone = (EditText)findViewById(R.id.editText);
        text = (EditText)findViewById(R.id.editText2);
        Button saveButton = (Button) findViewById(R.id.button);

        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                phoneList = phone.getText().toString();
                textMessage = text.getText().toString();
                Log.d(phoneList, textMessage);
            }
        });

    }





    };

