package com.ssum.android09intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewActivity extends AppCompatActivity {

    //선언
    ListView lv;
    String[] names = new String[20];

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        String id = getIntent().getStringExtra("id");
        String pwd = getIntent().getStringExtra("pwd");
        String name = getIntent().getStringExtra("name");
        String tel = getIntent().getStringExtra("tel");

        Log.i("testLog", id+pwd+name+tel);

        Bundle b = getIntent().getExtras();
        b.getBundle("data");

        //객체생성
        lv = (ListView) findViewById(R.id.lv);

        //리스트뷰 어댑터설정.
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names));

        //배열에 이름넣어주기
        for(int i=0; i<names.length; i++){
            names[i] = "person"+i+": name"+i+": 1234"+i+": 010"+i;
        }

        //이벤트 생성
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), InsertActivity.class);
                intent.putExtra("name", names[position]); //목록에서 메인으로?
                startActivity(intent);
            }
        });
    }
}
