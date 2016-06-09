package com.ssum.android11sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {

    //선언
    ListView lv;
    SQLiteDatabase mDatabase;
    ArrayList <String> lists = new ArrayList<>();
    //arraylist는 배열과 달리, 딱 add한 만큼만 들어감

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        //객체생성
        lv = (ListView) findViewById(R.id.lv);

        //intent를 받음.
        final Intent intent = getIntent();

        //DB 열어주기
        mDatabase = openOrCreateDatabase("member.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        //커서 객체 생성
        Cursor cursor = mDatabase.query("member", null, null,null,null, null, "num asc");
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){ //마지막행이 아니라면 돌아라
            lists.add(cursor.getString(0)+": "+ cursor.getString(1)
                    +": "+cursor.getString(2)+": "+cursor.getString(3)+": "+cursor.getString(4));
            cursor.moveToNext(); //다음 커서로 이동
        }

        //리스트뷰 어댑터설정.
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lists));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(getApplicationContext(), UpdateActivity.class);
                intent1.putExtra("lists", lists.get(position));
                intent1.setFlags(intent1.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent1);
            }
        });
    }
}
