package news.agoda.com.sample.di.news;

import dagger.Module;
import dagger.Provides;
import news.agoda.com.sample.network.NewsApi;
import retrofit2.Retrofit;

/**
 * Created by varun.am on 2019-07-18
 */
@Module
public class NewsModule {
    
    @Provides
    public NewsApi provideNewsApi(Retrofit retrofit){
        return retrofit.create(NewsApi.class);
    }
}
