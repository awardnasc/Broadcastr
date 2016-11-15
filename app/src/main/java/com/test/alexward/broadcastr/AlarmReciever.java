package com.test.alexward.broadcastr;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Alex Ward on 11/14/2016.
 *
 */

public class AlarmReciever extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {


        String phoneNum = intent.getStringExtra("phone");
        String message = intent.getStringExtra("text");

        if (TextUtils.isEmpty(phoneNum)|| TextUtils.isEmpty(message)){
            Toast.makeText(context, message + phoneNum, Toast.LENGTH_SHORT).show();

            return;
        }

        final List<String> phoneArray = Arrays.asList(phoneNum.split(","));



        for (int i = 0; i < phoneArray.size(); i++) {
            Log.d(phoneArray.get(i), message);

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneArray.get(i), null, message, null, null);

        }


        Toast.makeText(context, "Alarm Triggered and SMS Sent", Toast.LENGTH_LONG).show();
    }
}
