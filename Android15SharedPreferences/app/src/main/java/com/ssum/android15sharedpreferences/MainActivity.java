package com.ssum.android15sharedpreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //선언
    TextView tv_title;
    SharedPreferences pref;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //shared_prefs/settings.xml불러오기
        pref = getSharedPreferences("settings", 0);

        //string과 int셋팅. shared_prefs 폴더에 저장됨.
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name", "ssum's app");
        editor.putInt("age", 26);
        editor.commit();

        //키워드는 "name", "age"
        String name = pref.getString("name", "default_name");
        int age = pref.getInt("age", 0);

        tv_title = (TextView) findViewById(R.id.tv_title);

        //자동로그인이 체크되어있으면 editor가 설정한 값으로 셋팅하기
        if(pref.getBoolean("cb_login", true)) {
            tv_title.setText(name);
            tv_title.append(String.valueOf(age));
        }

        ////
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //floatingActionBar 제거함.
    }

    // 메뉴생성: settings가 들어있는 메뉴. (...메뉴)
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // 이벤트: ...메뉴 클릭시 이벤트 등록
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
//            Toast.makeText(getApplicationContext(), "setting화면으로 넘어갑니다.", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getApplicationContext(), MySetting.class);
            intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
