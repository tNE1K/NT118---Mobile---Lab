package com.example.lab5_c4;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button btnStart;
    ProgressBar pbTimeline;
    int duration;
    MusicPlaying musicPlaying;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button) findViewById(R.id.btnStart);
        pbTimeline = (ProgressBar) findViewById(R.id.pbTimeline);
        Uri sampleUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.buonhayvui);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    mediaPlayer.setDataSource(MainActivity.this, sampleUri);
                    mediaPlayer.prepare();
                    duration = mediaPlayer.getDuration();
                    musicPlaying = new MusicPlaying(MainActivity.this, duration, pbTimeline);
                    mediaPlayer.start();
                    musicPlaying.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}