package com.bwf.yiqizhuangxiu.gui.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bwf.yiqizhuangxiu.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/6 0006.
 */

public class ProgressGridAdapter extends BaseAdapter {
    private List<String> imageUri;
    private LayoutInflater inflater;

    public ProgressGridAdapter(Context context, String[] image) {
        imageUri = new ArrayList<>();
        inflater = LayoutInflater.from(context);

        for (int i = 0; i < image.length; i++) {
            imageUri.add(image[i]);
        }
    }

    @Override
    public int getCount() {
        return imageUri.size();
    }

    @Override
    public String getItem(int position) {
        return imageUri.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.progress_grid_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.imageProgressGrid.setImageURI(Uri.parse(imageUri.get(position)));
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.image_progress_grid)
        SimpleDraweeView imageProgressGrid;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
