package com.ssum.android26proximitysensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements SensorEventListener {

    TextView tv_game;
    SensorManager sensorManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_game = (TextView) findViewById(R.id.tv_game);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    protected void onStart(){
        super.onStart();

        //센서 목록
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_PROXIMITY);
        if (sensors.size() > 0) {
            //onSensorChanged를 오버라이딩 해주면 리스너를 쓸수있음.
            sensorManager.registerListener(this, sensors.get(0),
                    SensorManager.SENSOR_DELAY_FASTEST);
        }
    }//end of onCreate

    //강제 오버라이딩1: 여기서 핵심메소드임.(이벤트가 센서값을 받는다)
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        switch (sensor.getType()) {
            case Sensor.TYPE_PROXIMITY:
                //로그찍기
                tv_game.setText(""+event.values[0]);
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
}
