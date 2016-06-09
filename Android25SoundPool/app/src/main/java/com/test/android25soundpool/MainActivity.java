package com.test.android25soundpool;

import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button;

    private SoundPool soundPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        final int soundID = soundPool.load(this,
                R.raw.camera_click, //내가 재생할 소리
                1);//재생우선순위 0,1

        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   soundPool.play(soundID,1,1,0,0,1);
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        soundPool.release();
        soundPool = null;
    }
}
