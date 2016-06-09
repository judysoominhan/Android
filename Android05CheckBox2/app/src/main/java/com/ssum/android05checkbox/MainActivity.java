package com.ssum.android05checkbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox cb_01;
    CheckBox cb_02;
    CheckBox cb_03;
    CheckBox cb_04;
    CheckBox cb_05;
    CheckBox cb_06;
    CheckBox cb_07;
    CheckBox cb_08;
    CheckBox cb_09;
    CheckBox cb_10;
    CheckBox cb_11;
    CheckBox cb_12;
    Button bt_choice_01;
    Button bt_choice_02;
    Button bt_choice_03;
    TextView tv_result_01;
    TextView tv_result_02;
    TextView tv_result_03;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //객체생성
        cb_01 = (CheckBox) findViewById(R.id.cb_01);
        cb_02 = (CheckBox) findViewById(R.id.cb_02);
        cb_03 = (CheckBox) findViewById(R.id.cb_03);
        cb_04 = (CheckBox) findViewById(R.id.cb_04);
        cb_05 = (CheckBox) findViewById(R.id.cb_05);
        cb_06 = (CheckBox) findViewById(R.id.cb_06);
        cb_07 = (CheckBox) findViewById(R.id.cb_07);
        cb_08 = (CheckBox) findViewById(R.id.cb_08);
        cb_09 = (CheckBox) findViewById(R.id.cb_09);
        cb_10 = (CheckBox) findViewById(R.id.cb_10);
        cb_11 = (CheckBox) findViewById(R.id.cb_11);
        cb_12 = (CheckBox) findViewById(R.id.cb_12);
        bt_choice_01 = (Button) findViewById(R.id.bt_choice_01);
        bt_choice_02 = (Button) findViewById(R.id.bt_choice_02);
        bt_choice_03 = (Button) findViewById(R.id.bt_choice_03);
        tv_result_01 = (TextView) findViewById(R.id.tv_result_01);
        tv_result_02 = (TextView) findViewById(R.id.tv_result_02);
        tv_result_03 = (TextView) findViewById(R.id.tv_result_03);

        //이벤트
        bt_choice_01.setOnClickListener(new View.OnClickListener(){
           public void onClick(View v){
               String str="";
               Toast.makeText(getApplicationContext(), "결과가 전송되었습니다.", Toast.LENGTH_SHORT).show();
               if(cb_01.isChecked()){
                   str +=cb_01.getText();
                   //tv_result.setText(cb_01.getText());
               }if(cb_02.isChecked()) {
                   str +=cb_02.getText();
                   //tv_result.setText(cb_02.getText());
               }if(cb_03.isChecked()) {
                   str +=cb_03.getText();
               }if(cb_04.isChecked()) {
                   str +=cb_04.getText();
               }
               tv_result_01.setText(str);
           }
        });

        bt_choice_02.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String str="";
                Toast.makeText(getApplicationContext(), "결과가 전송되었습니다.", Toast.LENGTH_SHORT).show();
                if(cb_05.isChecked()){
                    str +=cb_05.getText();
                }if(cb_06.isChecked()) {
                    str +=cb_06.getText();
                }if(cb_07.isChecked()) {
                    str +=cb_07.getText();
                }if(cb_08.isChecked()) {
                    str +=cb_08.getText();
                }
                tv_result_02.setText(str);
            }
        });

        bt_choice_03.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String str="";
                Toast.makeText(getApplicationContext(), "결과가 전송되었습니다.", Toast.LENGTH_SHORT).show();
                if(cb_09.isChecked()){
                    str +=cb_09.getText();
                }if(cb_10.isChecked()) {
                    str +=cb_10.getText();
                }if(cb_11.isChecked()) {
                    str +=cb_11.getText();
                }if(cb_12.isChecked()) {
                    str +=cb_12.getText();
                }
                tv_result_03.setText(str);
            }
        });
    }
}
