package news.agoda.com.sample.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;

/**
 * This represents a news item
 */
public class NewsEntity implements Parcelable {
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
    
    private NewsEntity(Parcel in) {
        title = in.readString();
        summary = in.readString();
        articleUrl = in.readString();
        byline = in.readString();
        publishedDate = in.readString();
        mediaEntityList = in.createTypedArrayList(MediaEntity.CREATOR);
        mediaEntities = new Gson().fromJson(in.readString(), Object.class);
    }
    
    public static final Creator<NewsEntity> CREATOR = new Creator<NewsEntity>() {
        @Override
        public NewsEntity createFromParcel(Parcel in) {
            return new NewsEntity(in);
        }
        
        @Override
        public NewsEntity[] newArray(int size) {
            return new NewsEntity[size];
        }
    };
    
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
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        
        dest.writeString(title);
        dest.writeString(summary);
        dest.writeString(articleUrl);
        dest.writeString(byline);
        dest.writeString(publishedDate);
        dest.writeTypedList(mediaEntityList);
        dest.writeString(new Gson().toJson(mediaEntities));
    }
}
