package com.bwf.yiqizhuangxiu.gui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.bwf.yiqizhuangxiu.R;

import java.util.ArrayList;
import java.util.List;

public class Fitment2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitment2);
        Button button= (Button) findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Fitment2Activity.this,FitmentActivity.class);
                startActivity(intent);
            }
        });
        Intent intent=getIntent();
        EntityFitment.DataBean.ChildrenBean a=intent.getParcelableExtra("Entity");
        Log.d("Fitment2Activity", a.getIcon());
        TabLayout tabLayout= (TabLayout) findViewById(R.id.tabs);
        List<String> tabList = new ArrayList<>();
        tabList.add("全部");
        tabList.add("最新");
        tabList.add("精华");
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(tabList.get(2)));
        List<Fragment> fragmentList=new  ArrayList<>();
        Fragment f1=new FragmentFitment();
        Fragment f2=new FragmentFitment();
        Fragment f3=new FragmentFitment();
        a.getTitle();
        a.getIcon();
        a.getPostsnum();
        a.getThreadsnum();


//        textView12.setText("总贴："+a.getPostsnum());
//        TextView textView11= (TextView) findViewById(R.id.textView11);
//        textView11.setText("回贴："+a.getThreadsnum());
//        TextView textView13= (TextView) findViewById(R.id.textView13);
//        textView13.setText("回贴："+a.getTitle());
     //   SimpleDraweeView simpleDraweeView= (SimpleDraweeView) findViewById(R.id.imageView1);
 //       Uri uri=Uri.parse(a.getIcon());
   //     simpleDraweeView.setImageURI(uri);
        fragmentList.add(f1);
        fragmentList.add(f2);
        fragmentList.add(f3);
        Bundle bundle=new Bundle();
        bundle.putString("icon",a.getIcon());
        bundle.putString("title",a.getTitle());
        bundle.putString("threadsnum",a.getThreadsnum());
        bundle.putInt("postsnum",a.getPostsnum());
        f1.setArguments(bundle);

        FragmentAdapter fragmentAdapter=new FragmentAdapter(getSupportFragmentManager(),fragmentList,tabList);
        ViewPager viewPager= (ViewPager) findViewById(R.id.viewpage);
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);


    }
}
