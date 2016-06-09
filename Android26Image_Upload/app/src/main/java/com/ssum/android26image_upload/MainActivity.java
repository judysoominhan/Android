package com.ssum.android26image_upload;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
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
    Uri curImgURI;
    String filePath;

    //객체선언
    ImageView imageView;
    Button bt_getImage;
    Button bt_sendMultipart;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //객채 생성
        imageView = (ImageView) findViewById(R.id.ImageView01);
        bt_getImage = (Button) findViewById(R.id.getImage);
        bt_sendMultipart = (Button) findViewById(R.id.sendMultipart);

        //버튼이벤트: 클릭시 사진첩으로 이동?
        bt_getImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(); //액션과 타입을 따로설정하겠다.
                intent.setAction(Intent.ACTION_GET_CONTENT); //action_view, call아님.
                //content는 타입임. 갤러리
                intent.setType("image/*"); //모든이미지를 보겠다.
                startActivityForResult(intent, 1); //결과를 위한 startActivity. 정보/결과를 받을 수있다.
                //1=요청코드-->onActivityResult메소드의 첫번째인자로 간다.
                //startActivityForResult(intent); //기존의 intent

            }
        });

        //버튼이벤트
        bt_sendMultipart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new Thread() {
                    public void run() {
                        doProcess();
                    }
                }.start();
            }
        });

    }//end of onCreate

    //메소드 오버라이딩// 인텐트와 관련.//요청코드, 결과코드, 데이터가 intent에 담겨져서 온다
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        curImgURI = data.getData();

        //URI찍어보기
        Log.i("Main Log", ">>>" + curImgURI.toString());

        setImage(curImgURI);
    }

    private void setImage(Uri curImgURI) {
        Log.i("Main Log", ">>>" + curImgURI.toString());

        /////filePath. 커서객체로 URI를 획득함. 기존에 있는애한테 달라고함? 기존에 구축되어진 디비한테 URI를 줄테니 커서객체에 정보를 주세요
        Cursor cursor = getContentResolver().query(curImgURI, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){ //last까지
            for (int i=0;i<cursor.getColumnCount();i++){
                Log.i("Main Log", "Column >>>" + cursor.getColumnName(i));
                Log.i("Main Log", ">>>" + cursor.getString(cursor.getColumnIndexOrThrow(cursor.getColumnName(i))));
            }
            filePath = cursor.getString(cursor.getColumnIndex("_data"));
            cursor.moveToNext();//next로 옮겨가면서 읽어오기
        }

        Log.i("Main Log", "파일경로 >>>" + filePath);
        cursor.close(); //커서 닫기
        Bitmap bitmap = null;

        try { //비트맵으로 바꿔서 이미지 뷰에 셋팅하기
            //디코딩하기 decode@@@
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(curImgURI), null, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //이미지뷰에 셋팅.
        imageView.setImageBitmap(bitmap);
    }


    //doProcess메소드
    private void doProcess() {
        //경로얻어오기? storage/emulated
        Log.i("Main Log", "doProcess()..."+ Environment.getExternalStorageDirectory());

        String charset = "UTF-8";
//        File uploadFile1 = new File(Environment.getExternalStorageDirectory(),"DCIM/Camera/IMG_20150331_053729.jpg");
//        File uploadFile1 = new File("/storage/emulated/0/Android/data/com.android.browser/files/Download/"+filePath);
        File uploadFile1 = new File(filePath);

        //요청할 URL(서버주소): "내아이피:포트번호/경로????/##@$#.jsp"
        String requestURL = "http://192.168.1.29:8080/JSP_11FileUpload/uploadOk.jsp";

        //파일 존재하는지 찍어보기
        Log.i("Main Log", "doProcess()...uploadFile1.exists():"+uploadFile1.exists());

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
            multipart.addFilePart("multipartFile", uploadFile1); //만든객체 넣고

            List<String> response = multipart.finish();//finish 해줌. 결과는 list타입으로 온다.

            Log.i("Main Log", "SERVER REPLIED:");

            for (String line : response) {
                Log.i("Main Log", line);
            }
        } catch (IOException ex) {
            Log.i("Main Log", ex.toString());
        }
    }

}//end of activity
