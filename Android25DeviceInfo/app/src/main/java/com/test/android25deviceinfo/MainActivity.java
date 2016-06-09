package com.test.android25deviceinfo;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1.
        WindowManager mWindowManager =
                (WindowManager) getSystemService(WINDOW_SERVICE);
        Display mDisplay = mWindowManager.getDefaultDisplay();
        Log.i("Main Log","가로= "+mDisplay.getWidth());
        Log.i("Main Log", "세로 " + mDisplay.getHeight());

        //2.가로세로 길이 정보
        DisplayMetrics mDisplayMetrics =
                getResources().getDisplayMetrics();
        Log.i("Main Log","가로픽셀= "+mDisplayMetrics.widthPixels);
        Log.i("Main Log","세로픽셀= "+mDisplayMetrics.heightPixels);

        //3.폰 정보 가져오기
        TelephonyManager mTelephonyManager =
                (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        Log.i("Main Log","내 번호 = "+mTelephonyManager.getLine1Number());

        //4.모델명/브랜드/디바이스명/시리얼번호
        Log.i("Main Log","모델명 = "+Build.MODEL);
        Log.i("Main Log","브랜드명 = "+Build.BRAND);
        Log.i("Main Log","디바이스명 = "+Build.DEVICE);
        Log.i("Main Log","시리얼번호 = "+Build.SERIAL);


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
