package com.ssum.android06listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    //배열 만들기.
    String[] names = new String[30];

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //객체생성.
        lv = (ListView) findViewById(R.id.listView); //리스트뷰의 경우, '어댑터'라는 객체가 필요하다.

        //리스트뷰에 어댑터를 셋팅
        lv.setAdapter(new ArrayAdapter<String>(
                this,
                R.layout.list_row,
                names));

        //이벤트: 리스트를 눌렀을때 발생할 이벤트
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), names[position]+"을 선택하셨습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        //배열에 데이터입력.
        for (int i=0; i<names.length; i++) {
            names[i] = "person"+i;
        }
    }
}
