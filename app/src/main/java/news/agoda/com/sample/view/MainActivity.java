package news.agoda.com.sample.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.goweather.android_challenge.R;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import news.agoda.com.sample.di.viewmodel.ViewModelsProviderFactory;
import news.agoda.com.sample.model.NewsEntity;
import news.agoda.com.sample.model.NewsResponse;
import news.agoda.com.sample.viewmodel.MainViewModel;

public class MainActivity extends DaggerAppCompatActivity implements ClickedCallback {
    
    //adapter
    private NewsAdapter newsAdapter;
    
    //viewmodel
    private MainViewModel mainViewModel;
    
    //Glide
    @Inject
    RequestManager glide;
    
    @Inject
    ViewModelsProviderFactory providerFactory;
    
    //view
    private ProgressDialog progressDialog;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        init();
    }
    
    private void init() {
        //viewmodel
        mainViewModel = ViewModelProviders.of(this, providerFactory).get(MainViewModel.class);
        mainViewModel.fetchNews().observe(this, newsResponseObserver);
        mainViewModel.observeLoader().observe(this, showLoaderObserver);
        
        
        //setting toolbar
        setSupportActionBar((Toolbar) findViewById(R.id.main_toolbar_id));
        
        //views
        progressDialog = new ProgressDialog(this);
        RecyclerView newsRecyclerView = findViewById(R.id.news_rv_id);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsAdapter = new NewsAdapter(glide, this);
        newsRecyclerView.setAdapter(newsAdapter);
    }
    
    private Observer<NewsResponse> newsResponseObserver = new Observer<NewsResponse>() {
        @Override
        public void onChanged(NewsResponse newsResponse) {
            mainViewModel.observeLoader().postValue(false);
            newsAdapter.setNewsEntities(newsResponse.getResults());
        }
    };
    
    private Observer<Boolean> showLoaderObserver = new Observer<Boolean>() {
        @Override
        public void onChanged(Boolean aBoolean) {
            showLoader(aBoolean);
        }
    };
    
    private void showLoader(Boolean aBoolean) {
        if (aBoolean) {
            progressDialog.setTitle("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        } else {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }
    
    @Override
    public void onNewsItemClicked(NewsEntity clickedNews) {
        Intent intent = new Intent(this, DetailViewActivity.class);
        intent.putExtra(DetailViewActivity.NEWS_KEY, clickedNews);
        startActivity(intent);
    }
}
