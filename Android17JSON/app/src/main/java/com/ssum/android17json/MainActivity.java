package com.ssum.android17json;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    //섹션어댑터
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        //왓다갔다
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
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

    /**
     * A placeholder fragment containing a simple view.
     */

    //fragment상속. static inner 클래스
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        //static상수
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            //json 형태
            String data1 = "[1,2,3]"; //json배열 object //getint=jsonarray의 i번째
            String data2 = "[\"홍길동\", \"최길동\", \"박길동\"]"; //getString=jsonarray의 i번째
            String data3 = "[{\"name\":\"kim1\", \"age\": 44},{\"name\":\"kim2\", \"age\": 33}]"; //getJson =json ㅐㅠㅓㄷㅊㅅ

            String data4 = "[[],[]]"; //[123][456] //jsonarray, array[]
            String data5 = "[{\"홍팀\":[{\"name\":\"hong1\", \"age\": 44},{\"name\":\"hong2\", \"age\": 33}]}," +
                    "{\"청팀\":[{\"name\":\"blue1\", \"age\": 44},{\"name\":\"blue2\", \"age\": 33}]}]";

            //result1 append해주기
            int result1 = 0;
            try {
                JSONArray jsonArray = new JSONArray(data1);
                for (int i=0; i<jsonArray.length(); i++){
//                    jsonArray.getInt(i); //1,2,3이 차례대로 나옴
                    result1 += jsonArray.getInt(i); //1+2+3
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //result2 append해주기
            String result2 = ""; //null은 안됨
            try {
                JSONArray jsonArray = new JSONArray(data2);
                for(int i=0; i<jsonArray.length(); i++) {
                    result2 += jsonArray.getString(i);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //result3 append해주기
            String result3 = "";
            try {
                JSONArray jsonArray = new JSONArray(data3);
                for(int i=0; i<jsonArray.length(); i++) {
                    //array에서 i번째 object 뽑아.
                    JSONObject jsonObject = jsonArray.getJSONObject(i); //object
                    //i번째 object의 name/age 가져와
                    result3 += jsonObject.getString("name");
                    result3 += jsonObject.getInt("age");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //텍스트뷰에 셋팅해주기
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            //추가한 내용
            textView.append("result1 = "+result1);  //result1을 append
            textView.append("result2 = "+result2);  //result2을 append
            textView.append("result3 = "+result3);  //result3을 append
            textView.setTextColor(Color.BLUE);
            textView.setTextSize(40.0f);
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */

    //일반 inner클래스
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }
}
