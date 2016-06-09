package com.ssum.android10whoishimandher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button bt_rand;
    TextView tv_name;
    Random r = new Random();

    String[] names = new String[]{
            "강세현","김가현", "김성일","김강수", "김광옥", "김명철",
            "김수완", "김순기", "서동휘","우덕화", "유왕재", "김윤",
            "김재은", "도지현", "문정훈", "민혜미", "박세환", "박유연",
            "김진성", "김주연", "신성", "유보나", "이기영", "이동엽",
            "이동혁", "정해준", "이승현", "전웅", "정수주", "송관준",
            "박희도", "신현정", "정요한", "조준범", "최종진", "한수민",
            "한승훈","김진성"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //객체 생성
        bt_rand = (Button) findViewById(R.id.bt_rand);
        tv_name = (TextView) findViewById(R.id.tv_name);

        //이벤트: 버튼클릭시 랜덤이름이 TextView에 나타남.
        bt_rand.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        tv_name.setText(names[r.nextInt(names.length)]);
    }
}
