package com.example.cloudrecovery.DoctorPlanActivity;

import android.widget.RadioGroup;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.cloudrecovery.DoctorPlanAdapter.PagerMainAdapter;
import com.example.cloudrecovery.R;
import com.example.cloudrecovery.DoctorPlanFragment.ChatFragment;
import com.example.cloudrecovery.DoctorPlanFragment.MyFragment;
import com.example.cloudrecovery.DoctorPlanFragment.PlanFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private ViewPager vp;
    private RadioGroup rg;
    private int[] rbs = {R.id.rb_chat, R.id.rb_plan,R.id.rb_my };
    private List<Fragment> mFragments;


    //简化后的方法
    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        vp = f(R.id.vp);
        rg = f(R.id.rg);
    }
    @Override
    protected void initData() {

        mFragments=new ArrayList<>();
        ChatFragment chat = new ChatFragment();
        PlanFragment plan = new PlanFragment();
        MyFragment my = new MyFragment();
        mFragments.add(chat);
        mFragments.add(plan);
        mFragments.add(my);



        // 设置填充器
        vp.setAdapter(new PagerMainAdapter(getSupportFragmentManager(),mFragments));
        // 设置缓存页面数
        vp.setOffscreenPageLimit(2);

    }

    @Override
    protected void initListener() {
        //radioGroup的点击事件
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                for (int i = 0; i < rbs.length; i++) {
                    if (rbs[i] != checkedId) continue;
                    //加载滑动
                    vp.setCurrentItem(i);
                }
            }
        });
        //ViewPager的点击事件 vp-rg互相监听：vp
        vp.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                rg.check(rbs[position]);
            }
        });
        //设置一个默认页
        rg.check(rbs[0]);
    }
}
