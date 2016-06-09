package com.test.android24mediaplayer;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //미디어플래이어 객체 선언
    private MediaPlayer mp;

    //버튼 객체 선언
    private ImageButton btn_start;
    private Button btn_pause;
    private Button btn_restart;
    private Button btn_stop;

    private int pausePostion;

    //onCreate 메소드
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //객체 초기화
        btn_start = (ImageButton)findViewById(R.id.btn_start);
        btn_pause = (Button)findViewById(R.id.btn_pause);
        btn_restart = (Button)findViewById(R.id.btn_restart);
        btn_stop = (Button)findViewById(R.id.btn_stop);

        //버튼 이벤트 등록
        //onClickListener를 상속받았기 때문에 this로 쓸 수 있다.
        btn_start.setOnClickListener(this);
        btn_pause.setOnClickListener(this);
        btn_restart.setOnClickListener(this);
        btn_stop.setOnClickListener(this);

    }//end of onCreate

    //미디어플레이어앱이 종료될때,
    protected void onStop() {
        super.onStop();
        //사용되어졌다면, 사용되어진게 있다면
        if(mp!=null){
            mp.stop(); //스탑해준후
            mp.release(); //자원해제해주고(=스트림의 close와 비슷함)
            mp = null; //mp객체 비우기(null로 만들어주기)
        }
    }

    //onClick메소드
    public void onClick(View v) {
        //버튼의 아이디를 이용하여 switch-case사용
        switch (v.getId()){
            case R.id.btn_start:
                mp = MediaPlayer.create(getApplicationContext(), R.raw.test_cbr);
                mp.seekTo(7000); //7초 지난 후부터 시작해
                mp.start();
                break;
            case R.id.btn_pause:
                pausePostion = mp.getCurrentPosition();
                mp.pause();
                break;
            case R.id.btn_restart:
                mp.seekTo(pausePostion); //멈췃던 데에서 다시시작해라
                mp.start();
                break;
            case R.id.btn_stop:
                mp.stop();
                break;
            default:break;
        }
    }
}//end of Activity
