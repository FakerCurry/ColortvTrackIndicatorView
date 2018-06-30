package com.hc.colortracktextview;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hc.colortracktextview.indicator.IndicatorAdapter;
import com.hc.colortracktextview.indicator.TrackIndicatorView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String[] items = {"直播", "推荐", "视频", "段友秀", "图片", "段子", "精华", "同城", "游戏"};
    private TrackIndicatorView mIndicatorContainer;// 变成通用的
    private List<ColorTrackTextView> mIndicators;
    private ViewPager mViewPager;
    private String TAG = "ViewPagerActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIndicators = new ArrayList<>();
        mIndicatorContainer = (TrackIndicatorView) findViewById(R.id.indicator_view);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        initIndicator();
        initViewPager();
    }


    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return ItemFragment.newInstance(items[position]);
            }

            @Override
            public int getCount() {
                return items.length;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {

            }
        });

        // 监听ViewPager的滚动
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // 滚动的过程中会不断的回掉
                // Log.e("TAG", "position --> " + position + "  positionOffset --> "
                //         + positionOffset + " positionOffsetPixels --> " + positionOffsetPixels);

                ColorTrackTextView left = mIndicators.get(position);
                left.setDirection(ColorTrackTextView.Direction.RIGHT_TO_LEFT);
                left.setCurrentProgress(1 - positionOffset);

                try {
                    ColorTrackTextView right = mIndicators.get(position + 1);
                    right.setDirection(ColorTrackTextView.Direction.LEFT_TO_RIGHT);
                    right.setCurrentProgress(positionOffset);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onPageSelected(int position) {
                // 选中毁掉
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 初始化可变色的指示器
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void initIndicator() {
////        LinearLayout linearLayout = new LinearLayout(this);
//
////        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
////        lp.setLayoutDirection(LinearLayout.HORIZONTAL);
////        linearLayout.setLayoutParams(lp);
//        for (int i = 0; i < items.length; i++) {
//            // 动态添加颜色跟踪的TextView
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT);
//            params.weight = 1;
//            ColorTrackTextView colorTrackTextView = new ColorTrackTextView(this);
//            // 设置颜色
//            colorTrackTextView.setTextSize(20);
//            colorTrackTextView.setChangeColor(Color.RED);
//            colorTrackTextView.setText(items[i]);
//            colorTrackTextView.setLayoutParams(params);
////            linearLayout.addView(colorTrackTextView);
//            // 加入集合
//            mIndicators.add(colorTrackTextView);
//        }
// 把新的加入LinearLayout容器
//        mIndicatorContainer.addView(linearLayout);
        mIndicatorContainer.setAdapter(new IndicatorAdapter<ColorTrackTextView>() {
            @Override
            public int getCount() {
                return items.length;
            }

            @Override
            public ColorTrackTextView getView(int position, ViewGroup parent) {
//                TextView colorTrackTextView = new TextView(MainActivity.this);
//                // 设置颜色
//                colorTrackTextView.setTextSize(14);
//                colorTrackTextView.setGravity(Gravity.CENTER);
//                // colorTrackTextView.setChangeColor(Color.RED);
//                colorTrackTextView.setText(items[position]);
//                colorTrackTextView.setTextColor(Color.BLACK);
//                int padding = 20;
//                colorTrackTextView.setPadding(padding, padding, padding, padding);
                //  mIndicators.add(colorTrackTextView);

//                // 设置颜色
//                colorTrackTextView.setTextSize(14);
//                colorTrackTextView.setGravity(Gravity.CENTER);
//                // colorTrackTextView.setChangeColor(Color.RED);
//                colorTrackTextView.setText(items[position]);
//                colorTrackTextView.setTextColor(Color.BLACK);
//                int padding = 20;
//                colorTrackTextView.setPadding(padding, padding, padding, padding);
                ColorTrackTextView colorTrackTextView = new ColorTrackTextView(MainActivity.this);
                colorTrackTextView.setTextSize(20);
                colorTrackTextView.setChangeColor(Color.RED);
                colorTrackTextView.setText(items[position]);

                mIndicators.add(colorTrackTextView);
                return colorTrackTextView;
            }

            @Override
            public void highLightIndicator(ColorTrackTextView view) {
//                TextView textView = (TextView) view;
//                textView.setTextColor(Color.RED);
                view.setDirection(ColorTrackTextView.Direction.RIGHT_TO_LEFT);
                view.setCurrentProgress(1);
            }

            @Override
            public void restoreIndicator(ColorTrackTextView view) {
//                TextView textView = (TextView) view;
//                textView.setTextColor(Color.BLACK);
                view.setDirection(ColorTrackTextView.Direction.RIGHT_TO_LEFT);
                view.setCurrentProgress(0);
            }
        }, mViewPager,false);
    }
}
