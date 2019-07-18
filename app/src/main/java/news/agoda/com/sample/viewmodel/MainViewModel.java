package news.agoda.com.sample.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.ViewModel;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import news.agoda.com.sample.model.NewsResponse;
import news.agoda.com.sample.network.NewsApi;
import news.agoda.com.sample.utils.Constants;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by varun.am on 2019-07-16
 */
public class MainViewModel extends ViewModel {
    
    private NewsApi newsApi;
    
    public MainViewModel() {
        newsApi = getNewsClient().create(NewsApi.class);
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
    
    private Retrofit getNewsClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(loggingInterceptor);
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build();
    }
    
}
