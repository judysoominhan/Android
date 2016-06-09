package com.ssum.android03imageview;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button bt_change;
    ImageView img_lock;
    int cnt=0;
    Button bt_star;
    ImageView img_star;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //객체 생성(change버튼, 이미지)
        img_lock = (ImageView) findViewById(R.id.img_lock);
        bt_change = (Button) findViewById(R.id.bt_change);
        img_star = (ImageView) findViewById(R.id.img_star);
        bt_star = (Button) findViewById(R.id.bt_star);

        //버튼 클릭시 이벤트 만들기
        bt_change.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if (cnt==0) {
                    img_lock.setImageResource(android.R.drawable.presence_online);
                    cnt=1;
                    Toast.makeText(MainActivity.this, "image changed successfully!", Toast.LENGTH_SHORT).show();
                } else if(cnt==1){
                    img_lock.setImageResource(android.R.drawable.ic_lock_lock);
                    cnt=0;
                    Toast.makeText(MainActivity.this, "image changed successfully!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        bt_star.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if (cnt==0) {
                    img_star.setImageResource(android.R.drawable.btn_star);
                    cnt=1;
                    Toast.makeText(MainActivity.this, "image changed successfully!", Toast.LENGTH_SHORT).show();
                } else if(cnt==1){
                    img_star.setImageResource(android.R.drawable.star_on);
                    cnt=0;
                    Toast.makeText(MainActivity.this, "image changed successfully!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
