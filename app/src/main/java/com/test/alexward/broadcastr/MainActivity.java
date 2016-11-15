package com.test.alexward.broadcastr;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import android.os.Handler;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


import static com.test.alexward.broadcastr.R.string.save;

public class MainActivity extends Activity {

    private Button saveButton;
    private CheckBox checkBox;
    public EditText phone, text, month, day, year, hour, minute;
    public Calendar cal;

    public String phoneList, textMessage, dateStr, timeStr;
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
        final List<String> phoneArray = Arrays.asList(phoneList.split(","));
        textMessage = text.getText().toString();

        if (TextUtils.isEmpty(phoneList)|| TextUtils.isEmpty(textMessage)){
            Toast.makeText(this, "empty", Toast.LENGTH_SHORT).show();

            return;
        }
        String mon = month.getText().toString();
        int m = Integer.parseInt(mon);

        String da = day.getText().toString();
        int d = Integer.parseInt(da);

        String ye = year.getText().toString();
        int y = Integer.parseInt(ye);

        String ho = hour.getText().toString();
        int h = Integer.parseInt(ho);

        String mi = minute.getText().toString();
        int min = Integer.parseInt(mi);

        cal=Calendar.getInstance();
            cal.set(Calendar.MONTH, m);
            cal.set(Calendar.DAY_OF_MONTH, d);
            cal.set(Calendar.YEAR, y);
            cal.set(Calendar.HOUR, h);
            cal.set(Calendar.MINUTE, min);

       // Long time = new GregorianCalendar().getTimeInMillis()+15*1000;
        Intent intentAlarm = new Intent(this, AlarmReciever.class);
        intentAlarm.putExtra("phone", phoneList);
        intentAlarm.putExtra("text", textMessage);

        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                PendingIntent.getBroadcast(this,1,intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));

        Toast.makeText(this,"Text Scheduled", Toast.LENGTH_LONG).show();
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

    public String getPhoneList(){
        phoneList = phone.getText().toString();
        return phoneList;
    }

    public String getTextMessage(){
        textMessage = text.getText().toString();
        return textMessage;
    }
}

