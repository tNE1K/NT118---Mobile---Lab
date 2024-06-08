package com.example.lab4_c3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class MainActivity extends AppCompatActivity {


    public static final String SMS_FORWARD_BROADCAST_RECEIVER = "sms_forward_broadcast_receiver";
    public static final String SMS_MESSAGE_ADDRESS_KEY = "sms_messages_key";

    private IntentFilter filter;
    private ReentrantLock reentrantLock;
    private Switch swAutoResponse;
    private LinearLayout llButton;
    private Button btnSafe, btnMayday;
    private ArrayList<String> requesters;
    private ArrayAdapter<String> adapter;
    private ListView lvMessages;
    private BroadcastReceiver broadcastReceiver;
    public static boolean isRunning;
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;
    private final String AUTO_RESPONSE = "auto_response";

    private void findViewByIds(){
        swAutoResponse = (Switch) findViewById(R.id.sw_auto_response);
        llButton = (LinearLayout) findViewById(R.id.ll_button);
        lvMessages = (ListView) findViewById(R.id.lv_message);
        btnSafe = (Button) findViewById(R.id.btn_safe);
        btnMayday = (Button) findViewById(R.id.btn_mayday);
    }

    private void respond(String to, String response){
        reentrantLock.lock();
        requesters.remove(to);
        adapter.notifyDataSetChanged();
        reentrantLock.unlock();

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(to, null, response, null, null);
    }

    private void respond(boolean ok){
        String okString = getString(R.string.i_am_safe_and_well_worry_not);
        String notOKString = getString(R.string.tell_my_mother_i_love_her);
        String outputString = ok ? okString:notOKString;
        ArrayList<String> requestersCopy = (ArrayList<String>) requesters.clone();
        for(String to:requestersCopy)
            respond(to, outputString);
    }

    public void processReceiveAddresses(ArrayList<String> addresses){
        for(int i = 0; i<addresses.size(); i++){
            if(!requesters.contains(addresses.get(i))){
                reentrantLock.lock();
                requesters.add(addresses.get(i));
                adapter.notifyDataSetChanged();
                reentrantLock.unlock();

                if(swAutoResponse.isChecked())
                    respond(true);
            }
        }
    }

    public void processReceive(Context context, Intent intent){
        String queryString = "Are you OK?".toLowerCase();

        Bundle bundle = intent.getExtras();
        if(bundle != null){
            Object[] pdus = (Object[]) bundle.get("pdus");
            SmsMessage[] messages = new SmsMessage[pdus.length];
            for(int i = 0; i<pdus.length; i++){
                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
            }

            ArrayList<String> addresses = new ArrayList<>();
            for (SmsMessage message:messages){
                if(message.getMessageBody().toLowerCase().contains(queryString)){
                    addresses.add(message.getOriginatingAddress());
                }
            }
            for(int i = 0; i<addresses.size(); i++){
                if(!requesters.contains(addresses.get(i))){
                    reentrantLock.lock();
                    requesters.add(addresses.get(i));
                    adapter.notifyDataSetChanged();
                    reentrantLock.unlock();

                    if(swAutoResponse.isChecked())
                        respond(true);
                }
            }
        }
    }

    private void handleOnClickListener(){
        btnSafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respond(true);
            }
        });

        btnMayday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respond(false);
            }
        });

        swAutoResponse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    llButton.setVisibility(View.GONE);
                else
                    llButton.setVisibility(View.VISIBLE);
                editor.putBoolean(AUTO_RESPONSE, isChecked);
                editor.commit();
            }
        });
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
        isRunning = true;
        if(broadcastReceiver == null)
            initBroadcastReceiver();

        registerReceiver(broadcastReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        isRunning = false;

        unregisterReceiver(broadcastReceiver);
    }

    private void initVariables(){
        sharedPreferences = getPreferences(MODE_PRIVATE);
        editor = sharedPreferences.edit();
        reentrantLock = new ReentrantLock();
        requesters = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, requesters);
        lvMessages.setAdapter(adapter);

        boolean autoResponse = sharedPreferences.getBoolean(AUTO_RESPONSE, false);
        swAutoResponse.setChecked(autoResponse);
        if(autoResponse)
            llButton.setVisibility(View.GONE);
        initBroadcastReceiver();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewByIds();
        initVariables();
        handleOnClickListener();
    }
}