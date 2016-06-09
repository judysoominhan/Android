package com.ssum.android14menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //메소드로 만들어버리기
        mySetMenu();

    }//end of onCreate

    //상속관계에서 가져오려면 protected로 바꿔야함.
    protected void mySetMenu() {
        //toolbar객체 만들기
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); //supportActionBar셋팅

        //떠있는 버튼 객체 만들기(메세지아이콘)
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //버튼 이벤트 추가
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //drawer객체 생성
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState(); //toggle상태 저장.

        //네비게이션 객체 생성& 이벤트 달아주기.
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    public void onBackPressed() {

        //네비게이션이 열려있다면 close해라.
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    } //뒤로가기 눌렀을때.


    //팝업메뉴
    //중요. onOptionsItemSelected와 항상 함께 다녀야함.
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //리소스에 있는 main.xml을 넣어라.포함해라.
        //inflate: 원래 있던 레이아웃에, 새로운 레이아웃을 대신 집어넣는것.
        getMenuInflater().inflate(R.menu.main, menu);

        //nav_header_main.xml에 있는 imageView
        ImageView title_image = (ImageView)findViewById(R.id.imageView);
        title_image.setImageResource(R.drawable.ic_menu_camera); //이미지 바꾸기
        title_image.setOnClickListener(new View.OnClickListener() { //아이콘 눌르면 로그찍기
            @Override
            public void onClick(View v) {
                Log.i("testLog", "robo is clicked");
                Log.i("testLog", "네비게이션이 닫힙니다."); //아이콘 눌르면 네비 닫힘.
                // 이벤트 추가해줌
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
            }
        });

        return true;
    }

    //팝업메뉴
    //선택한 옵션에 대한 이벤트
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId(); //사용자가 어떤걸 눌렀는지 main.xml의 id를 구별해서 반응함.

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Log.i("testLog", "action_setting is pressed");
            return true;
        } else if (id == R.id.date_settings) { //우리가 추가한 date_settings추가해주기
            Log.i("testLog", "date_setting is pressed");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")

    //네비게이션메뉴
    //클릭시 발생할 이벤트 등록해주기.
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        //내가 누른 item의 id를 get해주기.
        int id = item.getItemId();

        //로그로 찍어보기
        if (id == R.id.nav_camera) {
            // Handle the camera action
            Log.i("testLog", "nav_camera is pressed");
        } else if (id == R.id.nav_gallery) {
            Log.i("testLog", "nav_gallery is pressed");
        } else if (id == R.id.nav_slideshow) {
            Log.i("testLog", "nav_slideshow is pressed");
        } else if (id == R.id.nav_manage) {
            Log.i("testLog", "nav_manage is pressed");
        } else if (id == R.id.nav_share) {
            Log.i("testLog", "nav_share is pressed");
        } else if (id == R.id.nav_send) {
            Log.i("testLog", "nav_send is pressed");
        } else if (id == R.id.test_robo) { //activity_main_drawer.xml에 추가해준 안드로보이미지
            Log.i("testLog", "안드로보 클릭!!");
            Toast.makeText(getApplicationContext(), "안드로보 클릭!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, TestActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
