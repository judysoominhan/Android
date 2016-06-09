package com.ssum.android26image_upload;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //전역변수
    Uri curVoiceURI;
    String filePath;

    //객체선언
    ImageView iv_androbo;
    Button bt_getfile;
    Button bt_send;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //객채 생성
        iv_androbo = (ImageView) findViewById(R.id.iv_androbo);
        bt_getfile = (Button) findViewById(R.id.bt_getfile);
        bt_send = (Button) findViewById(R.id.bt_send);

        //버튼이벤트: 클릭시 탐색기 실행됨.
        bt_getfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //음성녹음?
                Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("audio/*"); //모든 음성파일을 보겠다.
                startActivityForResult(intent, 1); //결과를 위한 startActivity. 정보/결과를 받을 수있다.
                //1=요청코드-->onActivityResult메소드의 첫번째인자로 간다.
                //startActivityForResult(intent); //기존의 intent

            }
        });

        //버튼이벤트: send버튼 클릭시 doProcess메소드 쓰레드로 실행
        bt_send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new Thread() {
                    public void run() {
                        doProcess();
                    }
                }.start();
            }
        });

    }//end of onCreate

    //메소드 오버라이딩// getfile버튼의 이벤트에서 인텐트와 관련된 액티비티.
    // 요청코드, 결과코드, 데이터(intent에 담겨져서 옴)가 인자로 전달됨
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        curVoiceURI = data.getData();

        //URI찍어보기
        Log.i("Main Log", ">>>알려줘1" + curVoiceURI.toString());

        setImage(curVoiceURI);
    }

    private void setImage(Uri curVoiceURI) {
        Log.i("Main Log", ">>>알려줘2" + curVoiceURI.toString());

        //filePath. 커서객체로 URI를 획득함. 기존에 있는애한테 달라고함? 기존에 구축되어진 디비한테 URI를 줄테니 커서객체에 정보를 주세요
        Cursor cursor = getContentResolver().query(curVoiceURI, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){ //last까지
            for (int i=0;i<cursor.getColumnCount();i++){
                Log.i("Main Log", "Column >>>" + cursor.getColumnName(i));
                Log.i("Main Log", ">>>알려줘3" + cursor.getString(cursor.getColumnIndexOrThrow(cursor.getColumnName(i))));
            }
            filePath = cursor.getString(cursor.getColumnIndex("_data"));
            cursor.moveToNext();//next로 옮겨가면서 읽어오기
        }

        Log.i("Main Log", "파일경로 >>>" + filePath);
        cursor.close(); //커서 닫기
        Bitmap bitmap = null;

        try { //비트맵으로 바꿔서 이미지 뷰에 셋팅하기
            //디코딩하기 decode@@@
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(curVoiceURI), null, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //이미지뷰에 셋팅.
        iv_androbo.setImageBitmap(bitmap);
    }


    //doProcess메소드
    private void doProcess() {
        String charset = "UTF-8";
        File my_file = new File(filePath);

        //요청할 URL(서버주소): "내아이피:포트번호/경로????/##@$#.jsp"
        String requestURL = "http://192.168.1.29:8080/JSP_11FileUpload/uploadOk.jsp";

        //파일 존재하는지 찍어보기
        Log.i("Main Log", "doProcess()...5uploadFile1.exists():"+my_file.exists());

        try {
            MultipartUtility multipart = null;
            try {
                //멀티파트 객체생성
                multipart = new MultipartUtility(requestURL, charset);
                Log.i("Main Log", "doProcess()...multipart:"+multipart);
            } catch (IOException e) {
                e.printStackTrace();
                Log.i("Main Log", "doProcess()...IOException:" + e.toString());
            }

            /*multipart.addHeaderField("User-Agent", "CodeJava");
            multipart.addHeaderField("Test-Header", "Header-Value");*/
            multipart.addFormField("tel", "010-0000-0000");
            multipart.addFilePart("multipartFile", my_file); //만든객체 넣고

            List<String> response = multipart.finish();//finish 해줌. 결과는 list타입으로 온다.

            Log.i("Main Log", "아래는 서버가 응답한 내용입니다 :");

            for (String line : response) {
                Log.i("Main Log", line);
            }
        } catch (IOException ex) {
            Log.i("Main Log", ex.toString());
        }
    }

}//end of activity
