package com.test.alexward.broadcastr;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.test.alexward.broadcastr.R.string.save;

public class MainActivity extends Activity {

    private Button saveButton;
    private EditText phone, text;
    private String phoneList, textMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},1);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phone = (EditText)findViewById(R.id.editText);
        text = (EditText)findViewById(R.id.editText2);
        Button saveButton = (Button) findViewById(R.id.button);

        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                phoneList = phone.getText().toString();
                List<String> phoneArray = Arrays.asList(phoneList.split(","));
                textMessage = text.getText().toString();

                //send message to each phone number in Array
                for(int i =0; i < phoneArray.size(); i++){
                    Log.d(phoneArray.get(i), textMessage);

                    //The below code causes a crash????
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneArray.get(i), null, textMessage, null, null);
                }

            }
        });

    }





    }

