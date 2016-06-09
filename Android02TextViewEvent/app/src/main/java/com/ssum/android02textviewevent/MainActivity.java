package com.ssum.android02textviewevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView title_tv;
    EditText et_name;
    Button bt_click;
    Button bt_clear;
    @Override
    //가장 먼저 실행됨. 번들 객체는 데이터를 바인딩(키:값으로)해서 엮어서 전달해준다.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 보여지도록. 괄호안에 있는 대상을 보여지도록,
        // text를 test.xml파일에 저장된 값으로 셋팅한다.
        setContentView(R.layout.activity_main); //<-test.xml

        //Debugging 찍어보기
        System.out.println("sysout...");
        Log.i("testLog", "");

        //객체만들기
        final TextView title_tv = (TextView) findViewById(R.id.title_tv);
        et_name = (EditText) findViewById(R.id.et_name);
        bt_click = (Button)findViewById(R.id.bt_click);
        bt_clear = (Button) findViewById(R.id.bt_clear);

        //click 버튼 클릭시 이벤트 만들기.
        bt_click.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                title_tv.setText(""); //*옵션* 클리어하고 가져오게 만들면 good.
                title_tv.setText(et_name.getText()); //버튼을 누를때 뿌려지는 텍스트 설정.
                //title_tv.setText("Kim"); //버튼 클릭시 타이틀의 텍스트가 Kim으로 바뀜.
            }
        });

        //clear버튼 클릭시 이벤트 만들기.
        bt_clear.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                title_tv.setText(""); //clear 버튼을 누르면 title이 다 지워짐
                et_name.setText(""); //clear 버튼을 누르면 editText에 써진 내용들이 다 지워짐.
            }
        });

    }//end of onCreate
}//end of Main
