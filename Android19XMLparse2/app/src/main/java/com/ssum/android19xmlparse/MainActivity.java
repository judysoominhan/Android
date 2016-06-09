package com.ssum.android19xmlparse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView tv_parse;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_parse = (TextView) findViewById(R.id.tv_parse);

        new Thread() {
            public void run() {
                //객체생성
                XmlPullParserFactory factory = null;

                try {
                    //경로
//                    URL url = new URL("http://192.168.0.149/androidStudy/test.xml");
                    URL url = new URL("http://192.168.1.29:8080/hello.xml");

                    factory = XmlPullParserFactory.newInstance();
                    factory.setNamespaceAware(true); //namespace쓰겠다. ex) android:

                    //풀파서 만들기.
                    XmlPullParser xpp = factory.newPullParser();
                    xpp.setInput(url.openStream(), "euc-kr");
                    //xpp.setInput( new StringReader( "<foo>Hello World!</foo>" ) );

                    int eventType = xpp.getEventType();
                    while (eventType != XmlPullParser.END_DOCUMENT) { //문서가 끝나지 않았다면 계속반복해라
                        if (eventType == XmlPullParser.START_DOCUMENT) {
                        } else if (eventType == XmlPullParser.START_TAG) { // <
                            System.out.println("속성의 갯수 = " + xpp.getAttributeCount());
                            System.out.println("name 속성 가져오기=" + xpp.getAttributeValue(null,"name"));
                            System.out.println("tel 속성 가져오기=" + xpp.getAttributeValue(null,"tel"));
                        } else if (eventType == XmlPullParser.END_TAG) { // >
                        } else if (eventType == XmlPullParser.TEXT) { //<>text</>
                            tv_parse.setText(xpp.getText());
                        }
                        eventType = xpp.next(); //next읽어라 (루프돌면서 계속 읽어옴)
                    }
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();


    }
}
