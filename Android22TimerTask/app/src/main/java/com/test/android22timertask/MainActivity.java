package com.test.android22timertask;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    private Handler handler = new Handler();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //타이머태스크
    private TimerTask  timerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();

        tv = (TextView)findViewById(R.id.textView);
        tv.setText("3초후 이벤트 페이지로 이동합니다."); //인자 두개일때 나옴.

        //앱이 실행될때 starttime이 셋팅됨.
        final long startTime = System.currentTimeMillis();

        timerTask = new TimerTask() {
            @Override
            //run메소드가 있다는 것은 쓰레드로 돌릴수있다는 것.
            public void run() {
                Log.i("timerTask", "timerTask.." + System.currentTimeMillis());

                //
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText(sdf.format(new Date())); //텍스트셋팅
                        long endTime = System.currentTimeMillis(); //시간 셋팅
                        Log.i("timerTask", "endTime-startTime.." + (endTime-startTime));

                        //특정시간(3초)이 지나면 액티비티 열기
                        if(endTime-startTime>=3000){
                            startActivity(new Intent(getApplicationContext(),EventActivity.class));
                        }
                    }
                });

            }
        };

        //타이머 객체 생성
        Timer timer = new Timer();

        //1초에 한번씩 작동하도록(실행시켜줌)
        timer.schedule(timerTask, 0, 1000);
        /*내가 주고싶은 시간으로 줄수있다.
        아래처럼 인자가 여러개인것도 있음.
        timer.schedule(timerTask,3000);
        timer.schedule(timerTask, 3000, 1000);*/

        //타이머 <->쓰레드는 멈췄다 동작했다를 반복하지만 타이머의 경우는 내가 원하는동작만 하도록 해줌.

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("timerTask", "timerTask.cancel()");
        timerTask.cancel();
    }
}
