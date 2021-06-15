//TimerUI(仮) 
package com.example.studdysupport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;

public class MainTimerFrame extends AppCompatActivity {
    boolean f = false;
    boolean isReset = true;
    StopWatch stopwatch = new StopWatch();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView time = findViewById(R.id.textView);
        Button start = findViewById(R.id.button);
        Button reset = findViewById(R.id.button2);
        Button set = findViewById(R.id.button3);
        EditText inputTime = findViewById(R.id.editTextNumber);
        Timer timer = new Timer();
        StopWatch stopwatch = new StopWatch();
        BottomNavigationView menu = findViewById(R.id.bnv);
        menu.getMenu().findItem(R.id.Timer).setChecked(true);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (f != true){
                    stopwatch.start(time);
                    if (isReset){
                        Timer timer = new Timer();
                        timer.scheduleAtFixedRate(stopwatch, 0, 100);
                    }
                    f = true;
                    isReset = false;
                }else{
                    stopwatch.interruption();
                    f = false;
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("elapsed", stopwatch.reset() + "s");
                timer.cancel();
                isReset = true;
            }
        });

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int time = Integer.parseInt(inputTime.getText().toString());
                DailyStudyTime dst = new DailyStudyTime();
                Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"));
                cal.clear();
                cal.set(2021, 5, 15);
                String str = inputTime.getText().toString();
                dst.Write(Integer.parseInt(str));
                Log.i("read", "t = " + dst.Read(cal));
            }
        });

        menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.Main){
                    Intent intent = new Intent(MainTimerFrame.this, MainUI.class);
                    startActivity(intent);
                    return true;
                }else if(id == R.id.Timer){
                    return true;
                }else if(id == R.id.Calendar){
                    Intent intent = new Intent(MainTimerFrame.this, Calndar_UI.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }
}
