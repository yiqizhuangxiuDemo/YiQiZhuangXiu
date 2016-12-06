package com.bwf.yiqizhuangxiu.gui.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bwf.yiqizhuangxiu.R;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class FitmentActivity extends Activity {
    private ListView mListView;
    private MaterialRefreshLayout refreshLayout;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        Fresco.initialize(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitment);
        Button btn= (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FitmentActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        mListView = (ListView) findViewById(R.id.listview);
        refreshLayout = (MaterialRefreshLayout) findViewById(R.id.refreshlayout);
        refreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefresLayout) {
                Toast.makeText(FitmentActivity.this, "正在刷新", Toast.LENGTH_SHORT).show();
                OkHttpUtils.get().url("http://bbs.17house.com/motnt/index.php?a=product&m=misc&model=android&uuid=a444d1b"
                        + "2af4f&app_version=android_com.aiyiqi.galaxy_1.1").build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(FitmentActivity.this, "加载失败，请重试", Toast.LENGTH_LONG).show();
                        refreshLayout.finishRefresh();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Toast.makeText(FitmentActivity.this, "加载成功", Toast.LENGTH_SHORT).show();
                        refreshLayout.finishRefresh();
                        final EntityFitment entity = JSON.parseObject(response, EntityFitment.class);
                        mListView.setAdapter(new BaseAdapter() {
                            @Override
                            public int getCount() {
                                return entity.getData().size();
                            }

                            @Override
                            public Object getItem(int position) {
                                return null;
                            }

                            @Override
                            public long getItemId(int position) {
                                return 0;
                            }

                            @Override
                            public View getView(final int position1, View convertView, ViewGroup parent) {
                                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.listview_item_fitment, null);
                                MyGridView myGridView = (MyGridView) convertView.findViewById(R.id.gridView);
                                myGridView.setAdapter(new BaseAdapter() {
                                    @Override
                                    public int getCount() {

                                        return entity.getData().get(position1).getChildren().size() / 3 * 3 + 6;
                                    }

                                    @Override
                                    public Object getItem(int position) {
                                        return null;
                                    }

                                    @Override
                                    public long getItemId(int position) {
                                        return 0;
                                    }

                                    @Override
                                    public View getView(final int position, View convertView, ViewGroup parent) {
                                        if (position == 0) {
                                            convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item1_fitment, null);
                                            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) convertView.findViewById(R.id.item1_imageview);
                                            Uri uri = Uri.parse(entity.getData().get(position1).getIcon());
                                            simpleDraweeView.setImageURI(uri);
                                            TextView textView = (TextView) convertView.findViewById(R.id.item1_text);
                                            textView.setText(entity.getData().get(position1).getTitle());
                                            convertView.setEnabled(false);
                                            return convertView;
                                        } else if (position == 1 || position == 2) {
                                            convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item2_fitment, null);
                                            convertView.setEnabled(false);
                                            return convertView;


                                        } else if (position+1-3>entity.getData().get(position1).getChildren().size()) {
                                            convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item2_fitment, null);
                                            convertView.setEnabled(false);
                                            return convertView;

                                        } else {
                                            convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item2_fitment, null);
                                            TextView textView2 = (TextView) convertView.findViewById(R.id.item2_textview);

                                            textView2.setText(entity.getData().get(position1).getChildren().get(position - 3).getTitle());
                                            convertView.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Intent intent = new Intent(FitmentActivity.this, Fitment2Activity.class);
                                                    intent.putExtra("Entity", entity.getData().get(position1).getChildren().get(position - 3));
                                                    startActivity(intent);
                                                }
                                            });
                                            return convertView;
                                        }
                                    }
                                });
                                return convertView;
                            }
                        });


                    }
                });

            }
        });


        OkHttpUtils.get().url("http://bbs.17house.com/motnt/index.php?a=product&m=misc&model=android&uuid=a444d1b"
                + "2af4f&app_version=android_com.aiyiqi.galaxy_1.1").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(FitmentActivity.this, "加载失败，请重试", Toast.LENGTH_LONG).show();
                refreshLayout.finishRefresh();
            }

            @Override
            public void onResponse(String response, int id) {
                Toast.makeText(FitmentActivity.this, "加载成功", Toast.LENGTH_SHORT).show();
                refreshLayout.finishRefresh();
                final EntityFitment entity = JSON.parseObject(response, EntityFitment.class);
                mListView.setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return entity.getData().size();
                    }

                    @Override
                    public Object getItem(int position) {
                        return null;
                    }

                    @Override
                    public long getItemId(int position) {
                        return 0;
                    }

                    @Override
                    public View getView(final int position1, View convertView, ViewGroup parent) {
                        convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.listview_item_fitment, null);
                        MyGridView myGridView = (MyGridView) convertView.findViewById(R.id.gridView);
                        myGridView.setAdapter(new BaseAdapter() {
                            @Override
                            public int getCount() {

                                return entity.getData().get(position1).getChildren().size() / 3 * 3 + 6;
                            }

                            @Override
                            public Object getItem(int position) {
                                return null;
                            }

                            @Override
                            public long getItemId(int position) {
                                return 0;
                            }

                            @Override
                            public View getView(final int position, View convertView, ViewGroup parent) {
                                if (position == 0) {
                                    convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item1_fitment, null);
                                    SimpleDraweeView simpleDraweeView = (SimpleDraweeView) convertView.findViewById(R.id.item1_imageview);
                                    Uri uri = Uri.parse(entity.getData().get(position1).getIcon());
                                    simpleDraweeView.setImageURI(uri);
                                    TextView textView = (TextView) convertView.findViewById(R.id.item1_text);
                                    textView.setText(entity.getData().get(position1).getTitle());
                                    convertView.setEnabled(false);
                                    return convertView;
                                } else if (position == 1 || position == 2) {
                                    convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item2_fitment, null);
                                    convertView.setEnabled(false);
                                    return convertView;


                                } else if (position+1-3>entity.getData().get(position1).getChildren().size()) {
                                    convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item2_fitment, null);
                                    convertView.setEnabled(false);
                                    return convertView;

                                } else {
                                    convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item2_fitment, null);
                                    TextView textView2 = (TextView) convertView.findViewById(R.id.item2_textview);

                                    textView2.setText(entity.getData().get(position1).getChildren().get(position - 3).getTitle());
                                    convertView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent(FitmentActivity.this, Fitment2Activity.class);
                                            intent.putExtra("Entity", entity.getData().get(position1).getChildren().get(position - 3));
                                            startActivity(intent);
                                        }
                                    });
                                    return convertView;
                                }
                            }
                        });
                        return convertView;
                    }
                });


            }
        });


    }


}
