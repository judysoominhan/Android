package com.ssum.android18network;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView tv_web;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //쓰레드로 돌리기
        new Thread() {
            public void run() {
                getParsing();
            }
        }.start();
    }

    //파싱하는 메소드
    private void getParsing(){
        tv_web = (TextView) findViewById(R.id.tv_web);

        TestParsing tp = new TestParsing();

        String data = tp.getData(); //쓰레드로 돌아야함. ->메소드로 빼기
        String result = "";
        try {
            JSONArray jsonArray = new JSONArray(data);
            for(int i=0; i<jsonArray.length(); i++) {
                //array에서 i번째 object 뽑아.
                JSONObject jsonObject = jsonArray.getJSONObject(i); //object
                //i번째 object의 name/age 가져와
                result += jsonObject.getString("name");
                result += jsonObject.getString("age");
            }

            Log.i("testLog", result);

            tv_web.setText(result);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
