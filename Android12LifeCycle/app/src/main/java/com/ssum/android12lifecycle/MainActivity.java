package com.ssum.android12lifecycle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity{

    //선언
    TextView tv_time;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("testLog", "onCreate()");

        //객체생성
        tv_time = (TextView) findViewById(R.id.tv_time);

        //화면을 회전하기 전이므로, null을 잡아줘야한다.
        if(savedInstanceState!=null) { //앱을 실행했을때는 실행되지 않다가 한번 회전 이후 실행됨.
            Log.i("testLog", "onCreate......."+savedInstanceState.getString("name"));
        }

    }//end of onCreate

    protected void onStart() {
        super.onStart();
        Log.i("testLog", "onStart()");

        //현재시간 가져오기 - home버튼 눌러도 시간이 갱신된다.
        Log.i("testLog", "time now = "+System.currentTimeMillis());
        tv_time.setText(df.format(System.currentTimeMillis()));
    }

    protected void onResume() {
        super.onResume();
        Log.i("testLog", "onResume()");
    }

    //뒤로가기
    protected void onPause() {
        super.onPause();
        Log.i("testLog", "onPause()");
    }

    protected void onStop() {
        super.onStop();
        Log.i("testLog", "onStop()");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.i("testLog", "onDestroy()");
    }

    protected void onRestart() {
        super.onRestart();
        Log.i("testLog", "onRestart()");
    }

    //중요한 두가지 함수. 특징. Bundle을 사용함.
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("testLog", "onSaveInstanceState()");

        //put하고
        outState.putString("name", "kim........");
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        //get하기
        Log.i("testLog", "onRestoreInstanceState()"+savedInstanceState.getString("name"));
    }

}//end of class
