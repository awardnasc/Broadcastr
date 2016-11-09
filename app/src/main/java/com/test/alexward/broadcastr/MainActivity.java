package com.test.alexward.broadcastr;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Handler;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static com.test.alexward.broadcastr.R.string.save;

public class MainActivity extends Activity {

    private Button saveButton;
    private CheckBox checkBox;
    private EditText phone, text, date, time;
    private String phoneList, textMessage, dateStr, timeStr;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phone = (EditText) findViewById(R.id.editText);
        text = (EditText) findViewById(R.id.editText2);
     //   date = (EditText) findViewById(R.id.editText3);
       // time = (EditText) findViewById(R.id.editText4);

        Button saveButton = (Button) findViewById(R.id.button);
        //final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                phoneList = phone.getText().toString();
                final List<String> phoneArray = Arrays.asList(phoneList.split(","));
                textMessage = text.getText().toString();

                if (TextUtils.isEmpty(phoneList)|| TextUtils.isEmpty(textMessage)){
                    return;
                }
                /*
                int isChecked = 0;
                if (checkBox.isChecked()) {
                    isChecked = 1;
                }
                if (isChecked == 1) {
                    dateStr = date.getText().toString();
                    timeStr = time.getText().toString();
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable(){
                        @Override
                        public void run(){
                            for (int i = 0; i < phoneArray.size(); i++) {
                                Log.d(phoneArray.get(i), textMessage);

                                //The below code causes a crash????
                                SmsManager smsManager = SmsManager.getDefault();
                                smsManager.sendTextMessage(phoneArray.get(i), null, textMessage, null, null);

                            }
                        }
                    }, 100);


                }*/

                /* send message to each phone number in Array */
                for (int i = 0; i < phoneArray.size(); i++) {
                    Log.d(phoneArray.get(i), textMessage);

                    //The below code causes a crash????
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneArray.get(i), null, textMessage, null, null);

                }
                Toast toast = Toast.makeText(getApplicationContext(), "Message Sent!", Toast.LENGTH_SHORT);
                toast.show();
                text.setText(null);

            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}

