package news.agoda.com.sample.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.RequestManager;
import com.goweather.android_challenge.R;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import news.agoda.com.sample.model.NewsEntity;

public class DetailViewActivity extends DaggerAppCompatActivity {
    
    public static final String NEWS_KEY = "news-key";
    public NewsEntity thisNewsEntity;
    
    @Inject
    RequestManager glide;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        
        if (getIntent() != null && getIntent().hasExtra(NEWS_KEY)) {
            thisNewsEntity = getIntent().getParcelableExtra(NEWS_KEY);
            init(thisNewsEntity);
        }
        
    }
    
    private void init(NewsEntity thisNewsEntity) {
        setSupportActionBar((Toolbar) findViewById(R.id.details_toolbar_id));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        
        ((TextView) findViewById(R.id.detail_title_id)).setText(thisNewsEntity.getTitle());
        ((TextView) findViewById(R.id.detail_summary_content_id)).setText(thisNewsEntity.getSummary());
        if (thisNewsEntity.getMediaEntity() != null && thisNewsEntity.getMediaEntity().size()> 0) {
            glide.load(thisNewsEntity.getMediaEntity().get(0).getUrl())
                    .placeholder(R.drawable.place_holder)
                    .into(((ImageView) findViewById(R.id.detail_news_image_id)));
        }
    }
    
    public void onFullStoryClicked(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(thisNewsEntity.getArticleUrl()));
        startActivity(intent);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
