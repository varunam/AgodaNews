package news.agoda.com.sample.model;

import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;

/**
 * This class represents a media item
 */
public class MediaEntity {
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
    
}
