package news.agoda.com.sample.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.goweather.android_challenge.R;

import java.util.ArrayList;

import news.agoda.com.sample.model.NewsEntity;

/**
 * Created by varun.am on 2019-07-17
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    
    private static final String TAG = NewsAdapter.class.getSimpleName();
    private ArrayList<NewsEntity> newsEntities;
    
    private RequestManager glide;
    private ClickedCallback clickedCallback;
    
    public NewsAdapter(RequestManager glide, ClickedCallback clickedCallback) {
        this.glide = glide;
        this.clickedCallback = clickedCallback;
    }
    
    public void setNewsEntities(ArrayList<NewsEntity> newsEntities) {
        this.newsEntities = newsEntities;
        notifyDataSetChanged();
    }
    
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_news, parent, false);
        return new ViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final NewsEntity newsEntity = newsEntities.get(position);
        holder.title.setText(newsEntity.getTitle());
        if (newsEntity.getMediaEntity().size() > 0) {
            Log.d(TAG, "URL: " + newsEntity.getMediaEntity().get(0).getUrl());
            glide.load(newsEntity.getMediaEntity().get(0).getUrl())
                    .placeholder(R.drawable.place_holder)
                    .dontAnimate()
                    .into(holder.thumbnail);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedCallback.onNewsItemClicked(newsEntity);
            }
        });
    }
    
    @Override
    public int getItemCount() {
        if (newsEntities != null && newsEntities.size() > 0) {
            return newsEntities.size();
        } else {
            return 0;
        }
    }
    
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView thumbnail;
        
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            
            title = itemView.findViewById(R.id.news_title);
            thumbnail = itemView.findViewById(R.id.news_item_image);
        }
    }
}
