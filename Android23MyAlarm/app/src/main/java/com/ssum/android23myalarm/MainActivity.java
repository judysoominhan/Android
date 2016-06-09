package com.ssum.android23myalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //타이머 관련 객체선언
    Handler handler = new Handler();
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    TimerTask timerTask;

    //보여지는 객체들 선언
    TextView tv_time;
    Button bt_01;
    Button bt_02;
    Button bt_03;

    //알람매니저 관련 객체선언
    AlarmManager am;
    PendingIntent pi;

    //mediaplayer관련 객체선언
    MediaPlayer mp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, MediaActivity.class);
        am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        pi = PendingIntent.getActivity(
                getApplicationContext(),
                0,
                intent,
                PendingIntent.FLAG_ONE_SHOT);

        tv_time = (TextView) findViewById(R.id.tv_time);
        bt_01 = (Button) findViewById(R.id.bt_01);
        bt_02 = (Button) findViewById(R.id.bt_02);
        bt_03 = (Button) findViewById(R.id.bt_03);

        bt_01.setOnClickListener(this);
        bt_02.setOnClickListener(this);
        bt_03.setOnClickListener(this);

        final long timeNow = System.currentTimeMillis();

        timerTask = new TimerTask() {
            //run메소드가 있다는 것은 쓰레드로 돌릴수있다는 것.
            public void run() {
                Log.i("timerTask", "timerTask.." + System.currentTimeMillis());

                handler.post(new Runnable() {
                    public void run() {
                        tv_time.setText(sdf.format(new Date())); //텍스트셋팅
                        long endTime = System.currentTimeMillis(); //시간 셋팅
                    }
                });
            }
        };

        Timer timer = new Timer();
        timer.schedule(timerTask, 0, 1000);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_01:
                Toast.makeText(getApplicationContext(),"2초후 알림이 울립니다.",Toast.LENGTH_SHORT).show();
                am.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+2000,pi);
                break;
            case R.id.bt_02:
                Toast.makeText(getApplicationContext(),"5초후 알림이 울립니다.",Toast.LENGTH_SHORT).show();
                am.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+5000,pi);
                break;
            case R.id.bt_03:
                am.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+10000,pi);
                break;
        }

    }
}
