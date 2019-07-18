package news.agoda.com.sample.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.goweather.android_challenge.R;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import news.agoda.com.sample.di.viewmodel.ViewModelsProviderFactory;
import news.agoda.com.sample.model.NewsEntity;
import news.agoda.com.sample.model.NewsResponse;
import news.agoda.com.sample.viewmodel.MainViewModel;

public class MainActivity extends DaggerAppCompatActivity implements ClickedCallback {
    
    private static final String TAG = MainActivity.class.getSimpleName();
    
    //adapter
    private NewsAdapter newsAdapter;
    
    //Glide
    @Inject
    RequestManager glide;
    
    @Inject
    ViewModelsProviderFactory providerFactory;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        
        init();
    }
    
    private void init() {
        //viewmodel
        MainViewModel mainViewModel = ViewModelProviders.of(this, providerFactory).get(MainViewModel.class);
        mainViewModel.fetchNews().observe(this, newsResponseObserver);
        
        //views
        //views
        RecyclerView newsRecyclerView = findViewById(R.id.news_rv_id);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsAdapter = new NewsAdapter(glide, this);
        newsRecyclerView.setAdapter(newsAdapter);
    }
    
    private Observer<NewsResponse> newsResponseObserver = new Observer<NewsResponse>() {
        @Override
        public void onChanged(NewsResponse newsResponse) {
            Log.d(TAG, "response Received");
            newsAdapter.setNewsEntities(newsResponse.getResults());
        }
    };
    
    @Override
    public void onNewsItemClicked(NewsEntity clickedNews) {
        Intent intent = new Intent(this, DetailViewActivity.class);
        intent.putExtra(DetailViewActivity.NEWS_KEY, clickedNews);
        startActivity(intent);
    }
}
