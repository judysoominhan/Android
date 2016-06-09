package com.ssum.android11sqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    //선언
    Button bt_list;
    Button bt_finish;
    Button bt_delete;
    EditText et_id;
    EditText et_pwd;
    EditText et_name;
    EditText et_tel;
    TextView tv_num;

    SQLiteDatabase mDatabase;
    ContentValues values = new ContentValues();//key-value를 설정해주는 객체

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        //객체생성
        bt_list = (Button) findViewById(R.id.bt_list);
        bt_finish = (Button) findViewById(R.id.bt_finish);
        bt_delete = (Button) findViewById(R.id.bt_delete);
        et_id = (EditText) findViewById(R.id.et_id);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        et_name = (EditText) findViewById(R.id.et_name);
        et_tel = (EditText) findViewById(R.id.et_tel);
        tv_num = (TextView) findViewById(R.id.tv_num);

        //DB열기
        mDatabase = openOrCreateDatabase("member.db",SQLiteDatabase.CREATE_IF_NECESSARY,null);

        //전화번호부에서 클릭한 lists을 받아서 로그에 찍어줌.
        Intent intent = getIntent();
        String name = intent.getStringExtra("lists");
        Log.i("testLog", name);

        //:을 통해서 분리해줌.
//        String[] num_temp = name.split("| ");
        String[] temp = name.split(": ");

        //et_id.setText(name);
//        tv_num.setText(num_temp[0]);
        tv_num.setText(temp[0]);
        et_id.setText(temp[1]);
        et_pwd.setText(temp[2]);
        et_name.setText(temp[3]);
        et_tel.setText(temp[4]);


        //이벤트: 목록버튼 클릭시 리스트로 이동.
        bt_list.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            Log.i("testLog", "리스트로 이동.");
            Log.i("testLog", tv_num.getText().toString());
            Log.i("testLog", et_id.getText().toString());
            Log.i("testLog", et_pwd.getText().toString());
            Log.i("testLog", et_name.getText().toString());
            Log.i("testLog", et_tel.getText().toString());

            Intent intent = new Intent(getApplicationContext(), ListViewActivity.class);
            startActivity(intent);
            }
        });

        //이벤트: 수정완료 버튼 클릭시, 리스트로 이동/데이터 수정.
        bt_finish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            //수정할 내용 입력.

            values.put("id", et_id.getText().toString());
            values.put("pwd", et_pwd.getText().toString());
            values.put("name", et_name.getText().toString());
            values.put("tel", et_tel.getText().toString());

            //update set member id='', pwd='', name='', tel='' where num=99
            mDatabase.update("member", values, "num=?", new String[]{tv_num.getText().toString()}); //?는 동적으로 할당해주는 와일드카드임.

            Toast.makeText(getApplicationContext(), "수정이 완료되었습니다.", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getApplicationContext(), ListViewActivity.class);
            startActivity(intent);
            }
        });

        //이벤트: 삭제 버튼 클릭시, 리스트로 이동/데이터 수정.
        bt_delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            //delete member where num=99
            mDatabase.delete("member", "num=?", new String[]{tv_num.getText().toString()});

            Toast.makeText(getApplicationContext(), "삭제가 완료되었습니다.", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getApplicationContext(), ListViewActivity.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
            }
        });
    }
}
