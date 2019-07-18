package news.agoda.com.sample.di.viewmodel;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import news.agoda.com.sample.di.news.NewsModule;
import news.agoda.com.sample.di.news.NewsViewModelsModule;
import news.agoda.com.sample.view.DetailViewActivity;
import news.agoda.com.sample.view.MainActivity;

/**
 * Created by varun.am on 2019-07-18
 */
@Module
public abstract class ActivityBuildersModule {
    
    @ContributesAndroidInjector(
            modules = {NewsModule.class, NewsViewModelsModule.class}
    )
    abstract MainActivity contributeMainActivity();
    
    @ContributesAndroidInjector
    abstract DetailViewActivity contributeDetailViewActivity();
    
}
