package com.bwf.yiqizhuangxiu.gui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.gui.adapter.FragmentViewPageAdapter;
import com.bwf.yiqizhuangxiu.gui.fragment.BudgetFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/23.
 */

public class BudgetActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);
        TabLayout tabLayout= (TabLayout) findViewById(R.id.tabs);
//        Button button= (Button) tabLayout.findViewById(R.id.button);
//        Log.d("123", button+"");
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(BudgetActivity.this,MainActivity.class);
//                startActivity(intent);
//            }
//        });
        List<String> tabList=new ArrayList<>();
        tabList.add("装修报价");
        tabList.add("我的预算");
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(1)));

        Fragment fragment1=new BudgetFragment();
        Fragment fragment2=new Fragment();
        List<Fragment> fragments=new ArrayList<>();
        fragments.add(fragment1);
        fragments.add(fragment2);
        FragmentViewPageAdapter fragmentViewPageAdapter=
                new FragmentViewPageAdapter(getSupportFragmentManager(),tabList,fragments);
        ViewPager viewPager= (ViewPager) findViewById(R.id.viewpage);
        viewPager.setAdapter(fragmentViewPageAdapter);
        tabLayout.setupWithViewPager(viewPager);
        Button button= (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BudgetActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
