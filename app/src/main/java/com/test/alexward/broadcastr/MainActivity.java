package com.test.alexward.broadcastr;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;



import java.util.Arrays;
import java.util.Calendar;

import java.util.List;


public class MainActivity extends AppCompatActivity {


    public EditText phone, text, month, day, year, hour, minute;
    public Calendar cal;
    public int count;
    public String phoneList, textMessage;
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


        count = 0;
        phone = (EditText) findViewById(R.id.editText);
        text = (EditText) findViewById(R.id.editText2);
        month = (EditText) findViewById(R.id.editText5);
        day = (EditText) findViewById(R.id.editText7);
        year = (EditText) findViewById(R.id.editText8);

        hour = (EditText) findViewById(R.id.editText4);
        minute = (EditText) findViewById(R.id.editText3);


        Button saveButton = (Button) findViewById(R.id.button);
        //final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);

        /*if(checkBox.isChecked() == true){

        }*/

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                phoneList = phone.getText().toString();
                final List<String> phoneArray = Arrays.asList(phoneList.split(","));
                textMessage = text.getText().toString();

                if (TextUtils.isEmpty(phoneList)|| TextUtils.isEmpty(textMessage)){
                    return;
                }

                /* send message to each phone number in Array */
                for (int i = 0; i < phoneArray.size(); i++) {
                    Log.d(phoneArray.get(i), textMessage);

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

    public void scheduleAlarm(View V){

        phoneList = phone.getText().toString();
        textMessage = text.getText().toString();

        if (TextUtils.isEmpty(phoneList)|| TextUtils.isEmpty(textMessage)){
            Toast.makeText(this, "empty", Toast.LENGTH_SHORT).show();

            return;
        }

        String mon = month.getText().toString();
        int m = Integer.parseInt(mon);
        m = m-1;

        String da = day.getText().toString();
        int d = Integer.parseInt(da);

        String ye = year.getText().toString();
        int y = Integer.parseInt(ye);

        String ho = hour.getText().toString();
        int h = Integer.parseInt(ho);

        String mi = minute.getText().toString();
        int min = Integer.parseInt(mi);


        cal = Calendar.getInstance();
        cal.set(y,m,d,h,min,0);

        Intent intentAlarm = new Intent(this, AlarmReciever.class);
        intentAlarm.putExtra("phone", phoneList);
        intentAlarm.putExtra("text", textMessage);

        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                PendingIntent.getBroadcast(this,count,intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
        count++;
        Toast.makeText(this,"Text Scheduled", Toast.LENGTH_LONG).show();
    }

    public void clearAlarms(View V){
        for(int i = 0; i < count; i++){
            Intent alarmIntent = new Intent(this, AlarmReciever.class);
            AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            PendingIntent displayIntent =  PendingIntent.getBroadcast(this,i,alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            alarmManager.cancel(displayIntent);
            Toast.makeText(this, "Cancelled alarm #" + i, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page")
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

