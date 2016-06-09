package com.ssum.android08gridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //선언
    GridView gv;
    String[] msg;

    //이미지를 넣을 int형 배열 선언.
    int[] imgs = new int[]{
        R.drawable.sample_0, R.drawable.sample_0,
        R.drawable.sample_1, R.drawable.sample_1,
        R.drawable.sample_2, R.drawable.sample_2,
        R.drawable.sample_3, R.drawable.sample_3,
        R.drawable.sample_4, R.drawable.sample_4,
        R.drawable.sample_5, R.drawable.sample_5,
        R.drawable.sample_6, R.drawable.sample_6,
        R.drawable.sample_7, R.drawable.sample_7,
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //그리드뷰 객체생성
        gv = (GridView)findViewById(R.id.gv);
        msg = new String[imgs.length];

        for(int i=0; i<imgs.length; i++) {
            msg[i] = "설명" + i;
        }


        //그리드뷰 어댑터 설정.
        gv.setAdapter(new MyAdapter(this, imgs)); // this는 mainactivity하고만 놀수있음. context
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), msg[position],Toast.LENGTH_SHORT).show();
            }
        });

    }

}
