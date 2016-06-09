package com.ssum.android23myalarm;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MediaActivity extends AppCompatActivity {

    MediaPlayer mp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mp = MediaPlayer.create(getApplicationContext(), R.raw.test_cbr);
        mp.start();
    }

    @Override
    //미디어플레이어앱이 종료될때,
    protected void onStop() {
        super.onStop();

        if(mp!=null){
            mp.stop(); //스탑해준후
            mp.release(); //자원해제해주고(=스트림의 close와 비슷함)
            mp = null; //mp객체 비우기(null로 만들어주기)
        }
    }
}
