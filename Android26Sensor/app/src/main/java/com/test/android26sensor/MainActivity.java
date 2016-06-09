package com.test.android26sensor;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import java.util.List;


public class MainActivity extends Activity
        implements SensorEventListener {

    private SoundPool soundPool;
    int soundID;

    private SensorManager sensorManager;
    private SensorViewEx sensorView; //뷰
    private int winWidth;
    private int winHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //뷰 만들기. 뷰 관련객체를 상속받는 클래스를 만들면 된다.
        sensorView = new SensorViewEx(this); //findbyid로 하지 않고 new로 한다.
        setContentView(sensorView); //MainActivity가 아닌 뷰이다. 뷰 종류는 다 된다.

        getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //풀스크린
        sensorManager = //센서값만큼 움직여주기 위해서
                (SensorManager) getSystemService(SENSOR_SERVICE);


        //내가 현재 보고있는 핸드폰의 정보를 얻어오기위해 사용. 디스플레이 크기 획득
        WindowManager wm =
                (WindowManager) getSystemService(WINDOW_SERVICE);
        Display mDisplay = wm.getDefaultDisplay();
        winWidth = mDisplay.getWidth();
        winHeight = mDisplay.getHeight();

        //센서
        List<Sensor> sensors =
                sensorManager.getSensorList( //쓸수있는 센서목록을 모두 획득
                        Sensor.TYPE_ACCELEROMETER); //엑셀러레이터 센서 사용하겠다(가속센서)

        if (sensors.size() > 0) { //쓸수있다면
            sensorManager.registerListener( //센서기능을 사용할게(활성화할게)
                    this,
                    sensors.get(0), //(0번째꺼=가속센서)
                    SensorManager.SENSOR_DELAY_FASTEST); //기능.센서속도. 딜레이가 가장빠름(반응이 빠름)
        }

        //사운드설정
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        soundID = soundPool.load(this,
                R.raw.camera_click,
                1);//재생우선순위 0,1
    }// end onCrate()


    //뷰를 상속받는 클래스. //뷰 관련 클래스. MainActivity안에있는 inner-class(final이 아니므로 멤버이너클래스)
    class SensorViewEx extends SurfaceView //빠른 그리기(움직임이 있는 그림)
            implements SurfaceHolder.Callback {

        private static final int LAMP_SIZE = 50;
        private Bitmap lamp;
        private int w;
        private int h;
        private float x;
        private float y;
        private int count = 0;
        private long lasttime =
                System.currentTimeMillis();
        private CustomViewThread customViewThread;
        private String str = "화면을터치하세요.";

        //생성자
        public SensorViewEx(Context c) {
            super(c);

            //안드로보 이미지. 비트맵이미지로 만든다.
            lamp = BitmapFactory.decodeResource(
                    c.getResources(),
                    R.mipmap.ic_launcher);//이미지 주소

            getHolder().addCallback(this); //이벤트 처리용 메소드. CallBack인터페이스(추상메소드를 가지고 있으므로 강제 오버라이딩)
            customViewThread =
                    new CustomViewThread(getHolder(), this);
            setFocusable(true);
        }// end constructor


        public void move(float mx, float my) {

            this.x -= (mx * 4f);
            this.y += (my * 4f);
            if (this.x < 35.0f) {
                this.x = 35.0f;
            } else if (this.x > winWidth - 35) {
                this.x = winWidth - 35;
            } else if ((this.x + LAMP_SIZE) > this.w) {
                this.x = this.w - LAMP_SIZE;
            }
            if (this.y < 38.0f) {
                this.y = 38.0f;
            } else if (this.y > winHeight - 40) {
                this.y = winHeight - 40;
            } else if ((this.y + LAMP_SIZE) > this.h) {
                this.y = this.h - LAMP_SIZE;
            }
            invalidate();
            // Log.i(LOG_TAG,this.x+" : "+this.y);

        }// end move

        @Override
        protected void onSizeChanged(
                int w, int h, int oldw, int oldh) {
            this.w = w;
            this.h = h;
            // this.x = (w - LAMP_SIZE) / 2f;
            // this.y = (h - LAMP_SIZE) / 2f;
        }

        @Override
        public void onDraw(Canvas canvas) {
            float screenXCenter = winWidth / 2f;
            float screenYCenter = winHeight / 2f;
            float radius = 200.0f; //원그리기
            float lampXHalf = x - (lamp.getWidth() / 2); //아이콘의 센터값
            float lampYHalf = y - (lamp.getHeight() / 2);

            //캔버스에 칼러를 그리기
            canvas.drawColor(Color.parseColor("#000000"));
            canvas.drawBitmap(lamp, lampXHalf, lampYHalf, null);

            //글자=paint 올리기
            Paint text = new Paint();
            text.setARGB(200, 255, 255, 255);
            text.setTextSize(30);
            canvas.drawText(str, 0, 30, text); //캔버스에 글자올리기

            //게임적인 요소
            if ((this.x > (screenXCenter - 10)
                    && this.x < (screenXCenter + 10))
                    && (this.y > (screenYCenter - 10)
                    && this.y < (screenYCenter + 10))) {
                radius = 100.0f;

                if (System.currentTimeMillis() - lasttime > 1000) { //1초가 증가하면(=1초가 지나면 그때부터 아래 실행)
                    count++; //카운트를 ++

                    lasttime = System.currentTimeMillis();
                }
                if (count >= 5) {
                    str = "성공"; //5초가 지나면 '성공'이라는 글자가 뜬다
                }else {
                    str = Integer.toString(count);
                    soundPool.play(soundID,1,1,0,0,1);
                }
            } else {
                str = "화면을터치하세요.";
                count = 0;
            }

            //동그라미 그리기
            Paint circleLayer = new Paint();
            circleLayer.setARGB(100, 255, 255, 255);
            canvas.drawCircle(
                    screenXCenter,
                    screenYCenter,
                    radius,
                    circleLayer);

            circleLayer.setColor(Color.BLACK);

            //십자가 그리기
            canvas.drawLine(screenXCenter - 50,
                    screenYCenter,
                    screenXCenter + 50,
                    screenYCenter,
                    circleLayer);
            canvas.drawLine(screenXCenter,
                    screenYCenter - 50,
                    screenXCenter,
                    screenYCenter + 50,
                    circleLayer);
        }// end onDraw

        //강제1.
        public void surfaceCreated(SurfaceHolder holder) {
            customViewThread.setRunning(true); //그림그릴 준비
            customViewThread.start(); //스타트
        }

        //강제2
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        //강제3
        public void surfaceDestroyed(SurfaceHolder holder) {
            boolean result = true;
            customViewThread.setRunning(false);

            while (result) { //무한루프(쓰레드를 무한으로 join해서 죽임) 쓰레드는 강제로 죽이기가 어려워서 이 방법 많이 사용
                try {
                    customViewThread.join(); //쓰레드 멈추기 위해 join해줌
                    result = false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            x = event.getX();
            y = event.getY();

            Log.i("testLog", "winWidth:" + winWidth);
            Log.i("testLog", "winHeight:" + winHeight);

            // return super.onTouchEvent(event);
            return true;
        }

    }//end SensorViewEx class

    //쓰레드 클래스(멤버이너2)
    class CustomViewThread extends Thread {

        private SurfaceHolder surfaceHolder;
        private SensorViewEx customView;
        private boolean running = false;

        public void setRunning(boolean running) {
            this.running = running;
        }

        //생성자
        public CustomViewThread(
                SurfaceHolder surfaceHolder,
                SensorViewEx customView) {
            super();
            this.surfaceHolder = surfaceHolder;
            this.customView = customView;
        }

        @SuppressLint("WrongCall")
        @Override
        public void run() {
            Canvas canvas; //캔버스객체 선언
            while (running) {
                canvas = null;

                //캔버스에 그림그리기용 세트
                try {
                    canvas = surfaceHolder.lockCanvas(null);//락
                    synchronized (surfaceHolder) {
                        customView.onDraw(canvas); //락 한다음 onDraw해준다
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (canvas != null)
                        surfaceHolder.unlockCanvasAndPost(canvas);//언락
                }
            }
        }
    }//end CustomViewThread class

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(
            Sensor sensor, int accuracy) {
    }// end onAccuracyChanged

    @Override
    public void onSensorChanged(
            final SensorEvent event) {
        Sensor sensor = event.sensor;
        switch (sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                sensorView.move(
                        event.values[0], event.values[1]);

                break;
            default:
                break;
        }
    }// end onSensorChanged

    @Override
    protected void onStop() {
        super.onStop();
        soundPool.release();
        soundPool = null;
    }
}
