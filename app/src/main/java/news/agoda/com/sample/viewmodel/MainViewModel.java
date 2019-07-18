package news.agoda.com.sample.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;
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
    private MutableLiveData<Boolean> loader = new MutableLiveData<>();
    
    @Inject
    MainViewModel(NewsApi newsApi) {
        this.newsApi = newsApi;
    }
    
    public LiveData<NewsResponse> fetchNews() {
        loader.postValue(true);
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
    
    public MutableLiveData<Boolean> observeLoader(){
        return loader;
    }
    
}
