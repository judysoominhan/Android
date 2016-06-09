package com.ssum.android05checkbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    CheckBox cb_01;
    CheckBox cb_02;
    CheckBox cb_03;
    EditText te_id;
    EditText te_pwd;
    Button bt_login;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //객체생성
        cb_01 = (CheckBox) findViewById(R.id.cb_01);
        cb_02 = (CheckBox) findViewById(R.id.cb_02);
        cb_03 = (CheckBox) findViewById(R.id.cb_03);
        te_id = (EditText) findViewById(R.id.et_id);
        te_pwd = (EditText) findViewById(R.id.et_pwd);
        bt_login = (Button) findViewById(R.id.bt_login);

        //이벤트

    }
}
