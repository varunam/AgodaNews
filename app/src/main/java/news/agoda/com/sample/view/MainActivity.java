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
import news.agoda.com.sample.utils.DialogButtonListener;
import news.agoda.com.sample.utils.DialogUtils;
import news.agoda.com.sample.utils.NetworkUtils;
import news.agoda.com.sample.viewmodel.MainViewModel;

public class MainActivity extends DaggerAppCompatActivity implements ClickedCallback, DialogButtonListener {
    
    private static final int INTERNET_ERROR_REQ_CODE = 1212;
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
        
        //setting toolbar
        setSupportActionBar((Toolbar) findViewById(R.id.main_toolbar_id));
        
        //views
        progressDialog = new ProgressDialog(this);
        RecyclerView newsRecyclerView = findViewById(R.id.news_rv_id);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsAdapter = new NewsAdapter(glide, this);
        newsRecyclerView.setAdapter(newsAdapter);
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        checkInternetAndInitiate();
    }
    
    private void checkInternetAndInitiate() {
        if (NetworkUtils.isNetworkAvailable(getApplicationContext())) {
            mainViewModel.fetchNews().observe(this, newsResponseObserver);
            mainViewModel.observeLoader().observe(this, showLoaderObserver);
        } else {
            showNetworkNotAvailableError();
        }
    }
    
    private void showNetworkNotAvailableError() {
        DialogUtils.createDialog(
                this,
                getResources().getString(R.string.internet_error),
                getResources().getString(R.string.internet_error_message),
                getResources().getString(R.string.retry),
                getResources().getString(R.string.exit_app),
                R.drawable.ic_error,
                INTERNET_ERROR_REQ_CODE,
                this
        ).show();
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
    
    @Override
    public void onDialogPositiveButtonClicked(int reqCode) {
        if (reqCode == INTERNET_ERROR_REQ_CODE) {
            checkInternetAndInitiate();
        }
    }
    
    @Override
    public void onDialogNegativeButtonClicked(int reqCode) {
        if (reqCode == INTERNET_ERROR_REQ_CODE) {
            finish();
        }
    }
}
