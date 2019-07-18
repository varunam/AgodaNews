package com.goweather.android_challenge;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;

import java.util.List;

public class NewsListAdapter extends ArrayAdapter {
    
    private List<NewsEntity> newsEntities;
    
    private static class ViewHolder {
        TextView newsTitle;
        SimpleDraweeView imageView;
    }
    
    public NewsListAdapter(Context context, int resource, List<NewsEntity> objects) {
        super(context, resource, objects);
        this.newsEntities = objects;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsEntity newsEntity = (NewsEntity) getItem(position);
        List<MediaEntity> mediaEntityList = newsEntity.getMediaEntity();
        
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item_news, parent, false);
            viewHolder.newsTitle =  convertView.findViewById(R.id.news_title);
            viewHolder.imageView =  convertView.findViewById(R.id.news_item_image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.newsTitle.setText(newsEntity.getTitle());
        String thumbnailURL = "";
        if (mediaEntityList != null && mediaEntityList.size() > 0) {
            MediaEntity mediaEntity = mediaEntityList.get(0);
            thumbnailURL = mediaEntity.getUrl();
            DraweeController draweeController = Fresco.newDraweeControllerBuilder().setImageRequest(ImageRequest.fromUri
                    (Uri.parse(thumbnailURL))).setOldController(viewHolder.imageView.getController()).build();
            viewHolder.imageView.setController(draweeController);
        }
        return convertView;
    }
}
