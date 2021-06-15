package com.example.studdysupport;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;
import java.util.TimerTask;

public class StopWatch extends TimerTask {
    long start;
    long end;
    long elapsed = 0;
    boolean f = false;
    TextView time;
    final Handler handler = new Handler(Looper.getMainLooper());

    public void start(TextView text){
        //start = SystemClock.elapsedRealtime();
        //Log.i("start1", "" + start);
        f = true;
        time = text;
    }

    public void interruption(){
        //Log.i("start", ""+ start);
        //end = SystemClock.elapsedRealtime();
        //elapsed += end - start;
        f = false;
    }

    public long reset(){
        //start = 0;
        //end = 0;
        f = false;
        time.setText("00:00");
        DailyStudyTime dst = new DailyStudyTime();
        dst.Write((int)elapsed/10/60);
        return elapsed/10/60;
    }

    @Override
    public void run() {
        handler.post(new Runnable() {
            @SuppressLint("DefaultLocale")
            @Override
            public void run() {
                if (f) {
                    time.setText(String.format("%02d:%02d", elapsed/10/60, elapsed/10%60));
                    elapsed++;
                }
            }
        });
    }
}
