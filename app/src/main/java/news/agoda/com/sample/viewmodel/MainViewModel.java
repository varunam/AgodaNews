package news.agoda.com.sample.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import news.agoda.com.sample.model.NewsResponse;
import news.agoda.com.sample.network.NewsApi;

/**
 * Created by varun.am on 2019-07-16
 */
public class MainViewModel extends ViewModel {
    
    private NewsApi newsApi;
    
    @Inject
    MainViewModel(NewsApi newsApi) {
        this.newsApi = newsApi;
    }
    
    public LiveData<NewsResponse> fetchNews() {
        return LiveDataReactiveStreams.fromPublisher(
                newsApi.getNews()
                        .onErrorReturn(new Function<Throwable, NewsResponse>() {
                            @Override
                            public NewsResponse apply(Throwable throwable) throws Exception {
                                return null;
                            }
                        })
                        .subscribeOn(Schedulers.io())
        );
    }
    
}
