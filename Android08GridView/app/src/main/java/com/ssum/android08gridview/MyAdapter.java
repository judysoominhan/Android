package com.ssum.android08gridview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/*내가 만든 클래스. BaseAdapter를 상속받는 클래스.
이러한 추상클래스를 상속받으려면 추상메소드를 반드시 오버라이딩 해줘야한다.*/
public class MyAdapter extends BaseAdapter{

    //전역변수 선언
    Context mContext;
    int[] imgs;

    //생성자.
    public MyAdapter(Context mContext, int[] imgs) {
        //생성자의 역할: 전역변수의 초기화.
        this.mContext = mContext;
        this.imgs = imgs;
    }

    @Override //배열의 길이 값을 리턴해주는 메소드.
    public int getCount() {
        return imgs.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override //그리드의 한칸을 보여주는 메소드.
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView iv=null;

        if(convertView==null) {
            //이미지뷰 객체 생성
            iv = new ImageView(mContext);
            iv.setLayoutParams(new GridView.LayoutParams(200,200));
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            //convertview를 캐스팅해줌.
            iv = (ImageView)convertView;
        }
        //이미지 셋팅해주기
        iv.setImageResource(imgs[position]);
        return iv;

    }
}

//생성자로 주거나 static에 올려놓기??