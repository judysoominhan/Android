package com.ssum.android09intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    //선언
    Button bt_insert;
    Button bt_list;

    //onCreate
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //객체생성
        bt_insert = (Button) findViewById(R.id.bt_insert);
        bt_list = (Button) findViewById(R.id.bt_list);

        //버튼이벤트
        bt_insert.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i("testLog", "입력페이지로 이동");
                startActivity(new Intent(getApplicationContext(), InsertActivity.class));
            }
        });
        bt_list.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ListViewActivity.class));
            }
        });

    }// end of onCreate
}// end of class