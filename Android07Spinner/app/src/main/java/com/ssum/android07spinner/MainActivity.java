package com.ssum.android07spinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    //선언
    TextView tv_email;
    EditText et_id;
    Spinner spinner;
    Button bt_clear;
    Button bt_input;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //객체생성
        tv_email = (TextView)findViewById(R.id.tv_email);
        spinner = (Spinner) findViewById(R.id.spinner);
        et_id = (EditText) findViewById(R.id.et_id);
        bt_clear = (Button) findViewById(R.id.bt_clear);
        bt_input = (Button) findViewById(R.id.bt_input);

        //이벤트: spinner 선택시 이메일주소가 textview에 나타남.
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    tv_email.setText("");
                } else {
                    tv_email.setText(et_id.getText()+"@"+ parent.getItemAtPosition(position));
                    //parent가 배열에대한 정보를 알고있음. 어댑터 필요없음.
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bt_clear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tv_email.setText("");
                et_id.setText("");
                Toast.makeText(getApplicationContext(), "clear", Toast.LENGTH_SHORT).show();
            }
        });

        bt_input.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "입력완료", Toast.LENGTH_SHORT).show();
              //tv_email.setText(et_id.getText()+"@"+emailNames[position]);
            }
        });
    }
}
