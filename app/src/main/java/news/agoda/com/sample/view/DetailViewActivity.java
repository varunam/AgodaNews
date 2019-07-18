package news.agoda.com.sample.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.goweather.android_challenge.R;

import news.agoda.com.sample.model.NewsEntity;

public class DetailViewActivity extends AppCompatActivity {
    
    public static final String NEWS_KEY = "news-key";
    public NewsEntity thisNewsEntity;
    
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
        ((TextView) findViewById(R.id.detail_title_id)).setText(thisNewsEntity.getTitle());
        ((TextView) findViewById(R.id.detail_summary_content_id)).setText(thisNewsEntity.getSummary());
        if (thisNewsEntity.getMediaEntity() != null && thisNewsEntity.getMediaEntity().size() > 0) {
            Glide.with(this)
                    .load(thisNewsEntity.getMediaEntity().get(0).getUrl())
                    .placeholder(R.drawable.place_holder)
                    .into(((ImageView) findViewById(R.id.detail_news_image_id)));
        }
    }
    
    public void onFullStoryClicked(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(thisNewsEntity.getArticleUrl()));
        startActivity(intent);
    }
}
