package com.test.android21connectivitymanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends ActionBarActivity {

    private TextView networkState;   //네트웍 상태표시용
    private EditText networkInfo;   //네트웍 정보표시용
    private String str;

    //여기서 핵심인 두개 객체임.
    private ConnectivityManager connMgr; //캐스팅해서 사용한다
    private NetworkInfo[] niArray;   // 사용가능한 네트워크 파악용


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        networkState = (TextView) findViewById(R.id.TextView01);
        networkInfo = (EditText) findViewById(R.id.EditText01);


        str = "";
        connMgr = (ConnectivityManager) //캐스팅
                getSystemService(Context.CONNECTIVITY_SERVICE);


        niArray = connMgr.getAllNetworkInfo();
        for (NetworkInfo n : niArray) {
            str = str.concat(n.toString() + "\n\n");
        }
        networkInfo.setText(str);


    }//end onCreate()

    //앱이 실행될 때
    protected void onStart() {
        super.onStart();

        //지금!!!활성화되어진 네트워크정보만 얻어와!
        NetworkInfo ni = connMgr.getActiveNetworkInfo();

        //null이 아니고 활성화 되어있다면
        if (ni != null) {
            if (connMgr.getActiveNetworkInfo().isAvailable()) {
            /*주의 : ni가 널값이 아닌경우(즉,무선네트워크 연결가능시)에만
				                 조건식에서 체크가능이 성립된다.*/
                String type = connMgr
                        .getActiveNetworkInfo().getTypeName();

                //모바일, 와이파이
                if (type.equalsIgnoreCase("mobile")) {
                    networkState.setText(
                            "현재 연결 네트워크  : 3G/4G/LTE무선 데이터 망" + "(" + type + ")");
                } else if (type.equalsIgnoreCase("wifi")) {
                    networkState.setText("현재 연결 네트워크  : " + type);
                }

                //사용중인 네트워크 정보 EditText로 출력
                str += ("getActiveNetworkInfo : \n"
                        + ni.toString() + "\n");
                networkInfo.setText(str + ">>>"
                        + ni.getState().toString());

                new Thread(){
                    public void run() {
                        doProcess(); //아래에 있는 메소드 실행
                    }
                }.start();

            }//end of if
        } else {
            networkState.setText("무선네트워크 연결 실패");
        }
    }//end onStart()


    //Thread로 돌릴 함수
    private void doProcess(){

        try{
            //구글 로고 이미지 가져와서
            URL url = new URL("https://www.google.co.kr/images/srpr/logo11w.png");
            HttpURLConnection conn =
                    (HttpURLConnection)url.openConnection();

            //HTTP_OK == 200
            if(conn.getResponseCode()==HttpURLConnection.HTTP_OK){
                Log.i("Main Log",
                        "conn.getContentType() : "+
                                conn.getContentType());
                if(conn.getContentType().startsWith("image/")){
                    Log.i("Main Log","이미지~~~맞거든~");
                }
            }


        }catch(Exception ex){
            Log.i("Main Log",ex.toString());
        }finally{

        }

    }//end doProcess()


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

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
