package news.agoda.com.sample.model;

import java.util.ArrayList;

/**
 * Created by varun.am on 2019-07-16
 */
public class NewsResponse {
    
    private ArrayList<NewsEntity> results;
    
    public NewsResponse(ArrayList<NewsEntity> results) {
        this.results = results;
    }
    
    public ArrayList<NewsEntity> getResults() {
        return results;
    }
    
    public void setResults(ArrayList<NewsEntity> results) {
        this.results = results;
    }
}
