package com.ssum.android09intent;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;

public class InsertActivity extends AppCompatActivity {
    //선언
    Button bt_main;
    EditText et_id;
    EditText et_pwd;
    EditText et_name;
    EditText et_tel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        //객체생성
        bt_main = (Button) findViewById(R.id.bt_main);
        et_id = (EditText) findViewById(R.id.et_id);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        et_name = (EditText) findViewById(R.id.et_name);
        et_tel = (EditText) findViewById(R.id.et_tel);

        //이벤트: 목록버튼 클릭시 리스트로 이동.
        bt_main.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i("testLog", "리스트로 이동.");
                Log.i("testLog", et_id.getText().toString());
                Log.i("testLog", et_pwd.getText().toString());
                Log.i("testLog", et_name.getText().toString());
                Log.i("testLog", et_tel.getText().toString());
                startActivity(new Intent(getApplicationContext(), ListViewActivity.class));
            }
        });
    }
}
