package com.ssum.android11sqlite;

        import android.content.Intent;
        import android.database.sqlite.SQLiteDatabase;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //멤버필드 선언
    Button bt_insert;
    SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DB만들기
        mDatabase = openOrCreateDatabase("member.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        //표만들기--> mysql쿼리문과 똑같이 쓰면됨.
        mDatabase.execSQL("create table if not exists member" +
                "(num integer primary key autoincrement, " +
                "id text, pwd text, name text, tel text)");

        bt_insert = (Button) findViewById(R.id.bt_insert);

        bt_insert.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InsertActivity.class);
                intent.setFlags(intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });

    }
}
