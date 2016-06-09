package com.ssum.android15sharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class MySetting extends AppCompatActivity {

    //객체 선언
    SharedPreferences pref;
    CheckBox cb_login;
    Boolean isAutoLogin; //기본값은 false

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mysetting);

        //체크박스 객체생성
        cb_login = (CheckBox) findViewById(R.id.cb_login);

        //이벤트: 체크박스 헤재/체크 시 toast띄우기
        cb_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(cb_login.isChecked()){
                    Toast.makeText(getApplicationContext(), "자동로그인 체크", Toast.LENGTH_SHORT).show();
                }else if(!cb_login.isChecked()){
                    Toast.makeText(getApplicationContext(), "자동로그인 체크해제", Toast.LENGTH_SHORT).show();
                };
            }
        });

        //이벤트: 체크박스 체크상태 변경시 이벤트
        cb_login.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isAutoLogin = isChecked;
            }
        });

        //저장된 값 불러오기
        pref = getSharedPreferences("settings", 0);

        //체크상태
        isAutoLogin = pref.getBoolean("cb_login", false); //에디터의 키와 같다. //기본값
        cb_login.setChecked(isAutoLogin);
    }

    //어플 종료시
    protected void onStop() {
        super.onStop();

        //체크박스 객체생성
        cb_login = (CheckBox) findViewById(R.id.cb_login);

        //저장된 값 불러오기
        pref = getSharedPreferences("settings", 0);

        //에디터로 체크박스상태 저장해주기
        SharedPreferences.Editor setting_editor = pref.edit(); //에디터 불러오기
        setting_editor.putBoolean("cb_login",cb_login.isChecked()); //저장할 값들
        setting_editor.commit(); //저장합니다.
    }
}
