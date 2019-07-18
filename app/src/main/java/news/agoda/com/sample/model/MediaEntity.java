package news.agoda.com.sample.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;

/**
 * This class represents a media item
 */
public class MediaEntity implements Parcelable {
    private String url;
    private String format;
    private Double height;
    private Double width;
    private String type;
    
    @SerializedName("subtype")
    private String subType;
    
    private String caption;
    private String copyright;
    
    MediaEntity(LinkedTreeMap<Object, Object> map) {
        this.url = (String) getValue(map, "url");
        this.format = (String) getValue(map, "format");
        this.height = (Double) getValue(map, "height");
        this.width = (Double) getValue(map, "width");
        this.type = (String) getValue(map, "type");
        this.subType = (String) getValue(map, "subtype");
        this.caption = (String) getValue(map, "caption");
        this.copyright = (String) getValue(map, "copyright");
    }
    
    protected MediaEntity(Parcel in) {
        url = in.readString();
        format = in.readString();
        if (in.readByte() == 0) {
            height = null;
        } else {
            height = in.readDouble();
        }
        if (in.readByte() == 0) {
            width = null;
        } else {
            width = in.readDouble();
        }
        type = in.readString();
        subType = in.readString();
        caption = in.readString();
        copyright = in.readString();
    }
    
    public static final Creator<MediaEntity> CREATOR = new Creator<MediaEntity>() {
        @Override
        public MediaEntity createFromParcel(Parcel in) {
            return new MediaEntity(in);
        }
        
        @Override
        public MediaEntity[] newArray(int size) {
            return new MediaEntity[size];
        }
    };
    
    private Object getValue(LinkedTreeMap<Object, Object> map, String key) {
        if (map.containsKey(key))
            return map.get(key);
        else
            return null;
    }
    
    public String getUrl() {
        return url;
    }
    
    public String getFormat() {
        return format;
    }
    
    public Double getHeight() {
        return height;
    }
    
    public Double getWidth() {
        return width;
    }
    
    public String getType() {
        return type;
    }
    
    public String getSubType() {
        return subType;
    }
    
    public String getCaption() {
        return caption;
    }
    
    public String getCopyright() {
        return copyright;
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
    
        dest.writeString(url);
        dest.writeString(format);
        if (height == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(height);
        }
        if (width == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(width);
        }
        dest.writeString(type);
        dest.writeString(subType);
        dest.writeString(caption);
        dest.writeString(copyright);
    }
}
