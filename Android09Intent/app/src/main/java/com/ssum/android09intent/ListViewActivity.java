package com.ssum.android09intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewActivity extends AppCompatActivity {

    //선언
    ListView lv;
    String[] names = new String[20];

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        //객체생성
        lv = (ListView) findViewById(R.id.lv);

        //리스트뷰 어댑터설정.
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names));

        //배열에 이름넣어주기
        for(int i=0; i<names.length; i++){
            names[i] = "person "+i;
        }
    }
}
