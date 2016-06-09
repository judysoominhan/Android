package com.test.android26draw9patch;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //토스트객체1 생성
        Toast toast = new Toast(this);
        toast.setGravity(Gravity.TOP, 0, 0); //위치
        toast.setDuration(Toast.LENGTH_LONG); //시간

        //텍스트뷰1 객체 생성
        TextView tv = new TextView(this);
        tv.setTextSize(20.0f); //사이즈
        tv.setText("Large Textaaaaaaaaaaaa\naaaaaa\naaaaaa"); //tv의 글자 셋팅
        tv.setBackgroundResource(
                R.drawable.ic_launcher_9); //나인패치로 바꿈
        toast.setView(tv); //뷰타입 셋팅
        toast.show();

        //토스트객체2 생성
        Toast toast2 = new Toast(this);
        toast2.setGravity(Gravity.CENTER, 0, 0);
        toast2.setDuration(Toast.LENGTH_LONG);

        //텍스트뷰2 객체 생성
        TextView tv2 = new TextView(this);
        tv2.setTextSize(20.0f);
        tv2.setText("Large Textaaaaaaaaaaaa\naaaaaa\naaaaaa");
        tv2.setBackgroundResource(
                R.drawable.img02_9); //.9확장자가 있다. 나인패치 이미지
        toast2.setView(tv2);
        toast2.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
