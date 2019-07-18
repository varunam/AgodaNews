package news.agoda.com.sample.model;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;

/**
 * This represents a news item
 */
public class NewsEntity {
    private static final String TAG = NewsEntity.class.getSimpleName();
    
    @SerializedName("title")
    private String title;
    
    @SerializedName("abstract")
    private String summary;
    
    @SerializedName("url")
    private String articleUrl;
    
    @SerializedName("byline")
    private String byline;
    
    @SerializedName("published_date")
    private String publishedDate;
    
    @SerializedName("multimedia")
    private Object mediaEntities;
    
    private ArrayList<MediaEntity> mediaEntityList;
    
    public NewsEntity(String title, String summary, String articleUrl, String byline, String publishedDate, Object mediaList) {
        this.title = title;
        this.summary = summary;
        this.articleUrl = articleUrl;
        this.byline = byline;
        this.publishedDate = publishedDate;
        this.mediaEntities = mediaList;
    }
    
    private ArrayList<MediaEntity> getMediaEntityListFromMap(Object mediaList) {
        Log.d(TAG, "mediaList");
        if (TextUtils.isEmpty(mediaList.toString())) {
            return new ArrayList<>();
        } else {
            ArrayList<LinkedTreeMap<Object, Object>> mediaEntities = (ArrayList<LinkedTreeMap<Object, Object>>) mediaList;
            ArrayList<MediaEntity> mediaEntitiesList = new ArrayList<>();
            for (int i = 0; i < (mediaEntities.size()); i++) {
                LinkedTreeMap<Object, Object> map = mediaEntities.get(i);
                MediaEntity mediaEntity = new MediaEntity(map);
                mediaEntitiesList.add(mediaEntity);
            }
            return mediaEntitiesList;
        }
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getSummary() {
        return summary;
    }
    
    public String getArticleUrl() {
        return articleUrl;
    }
    
    public String getByline() {
        return byline;
    }
    
    public String getPublishedDate() {
        return publishedDate;
    }
    
    public List<MediaEntity> getMediaEntity() {
        mediaEntityList = getMediaEntityListFromMap(mediaEntities);
        return mediaEntityList;
    }
}
