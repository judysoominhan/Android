package com.test.android23alarmmanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button;

    AlarmManager manager;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //캐스팅
        manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(Intent.ACTION_CALL,
                Uri.parse("tel:010-3858-9494"));

        //펜딩인텐트는 단독으로 쓰이지 않고 intent와 같이쓰인다.
        pendingIntent = PendingIntent.getActivity(
                getApplicationContext(),
                0,
                intent,
                PendingIntent.FLAG_ONE_SHOT
        );

        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                manager.set(AlarmManager.RTC_WAKEUP, //RTC_WakeUP(==앱깨우기?)
                        System.currentTimeMillis()+5000, //5초가 지나면
                        pendingIntent); //pendingIntent열어!
            }
        });
    }

    //앱이 종료될 때
    protected void onDestroy() {
        super.onDestroy();
//        manager.cancel(pendingIntent);
//        pendingIntent = null;
//        manager = null;
    }
}
