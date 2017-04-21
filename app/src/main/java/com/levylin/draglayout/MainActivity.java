package com.levylin.draglayout;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import me.relex.photodraweeview.PhotoDraweeView;

public class MainActivity extends AppCompatActivity {

    private List<String> pics = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pics.add("http://img.tuku.cn/file_thumb/201502/m2015022418083083.jpg");
        pics.add("http://img.tuku.cn/file_thumb/201502/m2015022418082585.jpg");
        pics.add("http://img.tuku.cn/file_thumb/201502/m2015022418082137.jpg");
        pics.add("http://img.tuku.cn/file_thumb/201502/m2015022418081640.jpg");
        pics.add("http://img.tuku.cn/file_thumb/201502/m2015022418080866.jpg");
        pics.add("http://img.tuku.cn/file_thumb/201502/m2015022418080330.jpg");
        pics.add("http://img.tuku.cn/file_thumb/201502/m2015022418075726.jpg");
        pics.add("http://img.tuku.cn/file_thumb/201502/m2015022418075149.jpg");
        DragRelativeLayout layout = (DragRelativeLayout) findViewById(R.id.act_main_container_rl);
        ColorDrawable drawable = new ColorDrawable(Color.BLACK);
        layout.setBackgroundDrawable(drawable);
        LinearLayout containerLayout = (LinearLayout) findViewById(R.id.act_main_other_layout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.act_main_vp);
        ImageAdapter adapter = new ImageAdapter();
        viewPager.setAdapter(adapter);
        layout.setOnMDragListener(new DragHelper(this, adapter, layout, containerLayout));
    }

    private class ImageAdapter extends PagerAdapter implements DragHelper.IGetCurrentViewAdapter {

        private View currentView;

        @Override
        public int getCount() {
            return pics.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Context context = container.getContext();
            View view = LayoutInflater.from(context).inflate(R.layout.act_main_image_item,container,false);
            PhotoDraweeView draweeView = (PhotoDraweeView) view.findViewById(R.id.photo_view);
            draweeView.setPhotoUri(Uri.parse(pics.get(position)));
            container.addView(view);
            return view;
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            super.setPrimaryItem(container, position, object);
            currentView = (View) object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public View getCurrentView() {
            return currentView;
        }
    }
}
