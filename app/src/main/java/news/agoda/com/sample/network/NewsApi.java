package news.agoda.com.sample.network;

import io.reactivex.Flowable;
import news.agoda.com.sample.model.NewsResponse;
import retrofit2.http.GET;

/**
 * Created by varun.am on 2019-07-16
 */

public interface NewsApi {
    
    @GET("/bins/nl6jh")
    public Flowable<NewsResponse> getNews();
    
}
