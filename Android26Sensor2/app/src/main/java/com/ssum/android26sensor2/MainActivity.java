package com.ssum.android26sensor2;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements SensorEventListener {

    //센서매니져 객체 선언
    SensorManager sensorManager;
    private SoundPool soundPool;
    int soundID;
    TextView tv_x;
    TextView tv_y;
    TextView tv_z;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //센서객체 생성
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        tv_x = (TextView) findViewById(R.id.tv_x);
        tv_y = (TextView) findViewById(R.id.tv_y);
        tv_z = (TextView) findViewById(R.id.tv_z);

        //센서 목록
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (sensors.size() > 0) {
            //onSensorChanged를 오버라이딩 해주면 리스너를 쓸수있음.
            sensorManager.registerListener(this, sensors.get(0),
                    SensorManager.SENSOR_DELAY_FASTEST);
        }

        //사운드풀
        soundPool = new SoundPool(1,AudioManager.STREAM_MUSIC, 0);
        soundID = soundPool.load(this, R.raw.ding, 1);//재생우선순위 0,1

    }//end of onCreate

    //강제 오버라이딩1: 여기서 핵심메소드임.(이벤트가 센서값을 받는다)
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        switch (sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                //로그찍기
                Log.i("testLog", "x:"+event.values[0]);
                Log.i("testLog", "y:"+event.values[1]);
                Log.i("testLog", "z:"+event.values[2]);

                tv_x.setText(""+event.values[0]);
                tv_y.setText(""+event.values[1]);
                tv_z.setText(""+event.values[2]);

                if(event.values[0]>10 || event.values[0]<-10){
                    soundPool.play(soundID, 1,1,0,0,1);
                }

                break;
            default:
                break;
        }
    }// end onSensorChanged

    //강제 오버라이딩2: 사용안하는 메소드
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    //앱이 종료될때 센서 더이상 사용 안하겠다.
    protected void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(this);
    }

    protected void onStop() {
        super.onStop();
        soundPool.release();
        soundPool = null;
    }


}//end of Activity