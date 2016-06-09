package com.ssum.android18network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by kosta on 2016-06-02.
 */
public class TestParsing {

    public String getData(){

        StringBuilder sb = new StringBuilder();

        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        HttpURLConnection conn = null;
        URL url = null;
        String str = null;

        try {
//            url = new URL("http://192.168.0.149/androidStudy/javaProject/java09Network/src/test/com/test.txt");
            url = new URL("http://192.168.1.29:8080/test.jsp?name=ssum&age=26");

            conn = (HttpURLConnection) url.openConnection();

            if(conn.getResponseCode()==200 && conn.getContentType().equals("text/plain")){
                is = url.openStream();
                isr = new InputStreamReader(is);
                br = new BufferedReader(isr);

                while((str=br.readLine())!=null){
                    sb.append(str);
                }
            }

        } catch (IOException e) {
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                isr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String data = sb.toString();
        return data;
    }
}
