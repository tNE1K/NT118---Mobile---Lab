package com.example.lab4_c1;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver broadcastReceiver;
    private IntentFilter filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initBroadcastReceiver();
    }

    public void processReceive(Context context, Intent intent){
        Toast.makeText(context, getString(R.string.you_have_a_new_message), Toast.LENGTH_SHORT).show();

        TextView tvContent = (TextView) findViewById(R.id.tv_content);

        final String SMS_EXTRA = "pdus";
        Bundle bundle = intent.getExtras();

        Object[] messages = (Object[]) bundle.get(SMS_EXTRA);
        String sms = "";

        SmsMessage smsMsg;
        for(int i = 0; i < messages.length; i++){
            smsMsg = SmsMessage.createFromPdu((byte[]) messages[i]);


            if (smsMsg != null) { // Kiểm tra xem smsMsg có null hay không trước khi sử dụng
                String msgBody = smsMsg.getMessageBody();
                String address = smsMsg.getDisplayOriginatingAddress();
                sms += address + ":\n" + msgBody + "\n";
            }
        }

        tvContent.setText(sms);
    }

    private void initBroadcastReceiver(){
        filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                processReceive(context, intent);
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(broadcastReceiver == null)
            initBroadcastReceiver();

        registerReceiver(broadcastReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiver);
    }
}