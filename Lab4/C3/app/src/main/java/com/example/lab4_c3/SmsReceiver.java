package com.example.lab4_c3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import java.util.ArrayList;

public class SmsReceiver extends BroadcastReceiver {

    public static final String SMS_FORWARD_BROADCAST_RECEIVER = "sms_forward_broadcast_receiver";
    public static final String SMS_MESSAGE_ADDRESS_KEY = "sms_messages_key";
    @Override
    public void onReceive(Context context, Intent intent) {
        if(!MainActivity.isRunning)
        {
            Intent intent1 = new Intent(context, MainActivity.class);
            context.startActivity(intent1);
        }
    }
}
