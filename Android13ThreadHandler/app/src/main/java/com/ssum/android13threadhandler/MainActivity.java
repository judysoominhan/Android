package com.ssum.android13threadhandler;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    TextView tv_time;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
    Handler mHandler = new Handler();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_time = (TextView) findViewById(R.id.tv_time);

        new Thread() {
            public void run() {
                while(true)
                try {
                    //Thread.sleep(1000);
                    Thread.sleep(200);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            tv_time.setText(df.format(System.currentTimeMillis()));
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }//end of onCreate
}
