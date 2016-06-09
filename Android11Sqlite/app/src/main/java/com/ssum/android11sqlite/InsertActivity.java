package com.ssum.android11sqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {

    //멤버필드 선언
    SQLiteDatabase mDatabase;
    EditText et_id;
    EditText et_pwd;
    EditText et_name;
    EditText et_tel;
    Button bt_finish;
    Button bt_list;
    ContentValues values = new ContentValues();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        //객체생성
        mDatabase = openOrCreateDatabase("member.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        et_id = (EditText) findViewById(R.id.et_id);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        et_name = (EditText) findViewById(R.id.et_name);
        et_tel = (EditText) findViewById(R.id.et_tel);
        bt_finish = (Button) findViewById(R.id.bt_finish);
        bt_list = (Button) findViewById(R.id.bt_list);

        //이벤트: 입력완료 버튼눌렀을때, DB에 데이터 저장
        bt_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //value라는 객체를 만든 후, id/pwd/name/tel값들을 넣어줌
                values.put("id", et_id.getText().toString());
                values.put("pwd", et_pwd.getText().toString());
                values.put("name", et_name.getText().toString());
                values.put("tel", et_tel.getText().toString());

                //member라는 테이블에 value객체를 넣어줌.(value는 키와 값을 설정해주는 객체임)
                mDatabase.insert("member", null, values);
                Toast.makeText(getApplicationContext(), "데이터가 입력되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        //이벤트: 버튼 클릭시 listview로 화면이 넘어간다.
        bt_list.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ListViewActivity.class);
                intent.setFlags(intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });
    }
}
