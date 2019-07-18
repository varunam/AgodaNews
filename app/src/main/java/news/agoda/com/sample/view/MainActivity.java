package news.agoda.com.sample.view;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.goweather.android_challenge.R;

import news.agoda.com.sample.model.NewsResponse;
import news.agoda.com.sample.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {
    
    private static final String TAG = MainActivity.class.getSimpleName();
    
    //views
    private RecyclerView newsRecyclerView;
    
    //adapter
    private NewsAdapter newsAdapter;
    
    //viewmodel
    private MainViewModel mainViewModel;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        
        init();
    }
    
    private void init() {
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.fetchNews().observe(this, newsResponseObserver);
        
        //views
        newsRecyclerView = findViewById(R.id.news_rv_id);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsAdapter = new NewsAdapter();
        newsRecyclerView.setAdapter(newsAdapter);
    }
    
    private Observer<NewsResponse> newsResponseObserver = new Observer<NewsResponse>() {
        @Override
        public void onChanged(NewsResponse newsResponse) {
            Log.d(TAG, "response Received");
            newsAdapter.setNewsEntities(newsResponse.getResults());
        }
    };
}
