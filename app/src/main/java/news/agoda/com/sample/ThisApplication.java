package news.agoda.com.sample;


import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import news.agoda.com.sample.di.DaggerAppComponent;

/**
 * Created by varun.am on 2019-07-18
 */
public class ThisApplication extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
