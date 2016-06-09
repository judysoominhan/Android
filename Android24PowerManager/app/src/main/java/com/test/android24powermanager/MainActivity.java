package com.test.android24powermanager;

import android.os.Bundle;
import android.os.PowerManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    //계속 켜진채로 앱을 놔둬야 하는경우,
    //ex.네비게이션 등등
    private PowerManager.WakeLock mWakeLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();

        PowerManager mPowerManager =
                (PowerManager) getSystemService(POWER_SERVICE);

        mWakeLock = mPowerManager.newWakeLock(
                PowerManager.PARTIAL_WAKE_LOCK,
                "yangssem");
        mWakeLock.acquire();

    }

    @Override
    //앱을 종료했을 때
    protected void onStop() {
        super.onStop();
        mWakeLock.release(); //release해줘야한다
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
