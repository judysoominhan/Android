package com.ssum.android04radiobutton;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup rg;
    RadioButton rb_korea;
    RadioButton rb_japan;
    RadioButton rb_USA;

    Button bt_send;
    String result = "오답";
    Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rg = (RadioGroup) findViewById(R.id.rg);
        rb_korea = (RadioButton) findViewById(R.id.rb_korea);
        rb_japan = (RadioButton) findViewById(R.id.rb_japan);
        rb_USA = (RadioButton) findViewById(R.id.rb_USA);
        bt_send = (Button) findViewById(R.id.bt_send);


        //send버튼을 눌렀을때 나타낼 이벤트
        bt_send.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //id 바뀌는지 로그에 찍어보기
                Log.i("testLog", "check id" + rg.getCheckedRadioButtonId());
                Log.i("testLog", result);
            }
        });

        //라디오버튼이 눌렸을때 id체크하고 변수에 담는 이벤트
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.i("testLog", "id is..." + checkedId);
                if(checkedId == R.id.rb_korea){
                    Log.i("testLog", "한국");
                    result = "한국";
                    toast.makeText(getApplicationContext(),"한국을 선택하셨습니다.", Toast.LENGTH_SHORT).show();
                }else if(checkedId == R.id.rb_japan){
                    Log.i("testLog", "일본");
                    result = "일본";
                }else{
                    Log.i("testLog", "미국");
                    result = "미국";
                }
            }
        });
    }
}
