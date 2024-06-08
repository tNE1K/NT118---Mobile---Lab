package com.example.lab5_c4;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;

public class MusicPlaying extends AsyncTask<Void, Integer, Integer> {

    private ProgressBar pbTimeline;
    private int duration;
    private Context context;

    public MusicPlaying(Context context, int duration, ProgressBar pbTimeline){
        this.context = context;
        this.duration = duration;
        this.pbTimeline = pbTimeline;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pbTimeline.setMax(100);
    }

    @Override
    protected Integer doInBackground(Void... arg0) {
        for(int i = 0; i < 100; i++)
        {
            publishProgress(i);

            try {
                Thread.sleep(2918);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        pbTimeline.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);
        Toast.makeText(context, "End", Toast.LENGTH_SHORT).show();
    }
}
