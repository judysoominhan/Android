package com.ssum.android20webview;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.HttpURLConnection;

public class MainActivity extends AppCompatActivity {
    //객체선언
    WebView wb;
    HttpURLConnection conn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wb = (WebView)findViewById(R.id.webView);

        //웹뷰에 대한 셋팅해주기. 자바스크립트 활성화해주기!!
        wb.getSettings().setJavaScriptEnabled(true);

        //내 안드로이드에서 화면이 보인다.
        wb.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.i("testLog", "페이지 로딩중...");
                return super.shouldOverrideUrlLoading(view, url);
            }

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.i("testLog", "불러오기 시작");
            }

            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.i("testLog", "불러오기 완료");
            }
        });

        //내가 보고싶은 웹의 url
        //wb.loadUrl("http://yangssem.blog.me");
        wb.loadUrl("http://blog.naver.com/6533574");

    }
}
