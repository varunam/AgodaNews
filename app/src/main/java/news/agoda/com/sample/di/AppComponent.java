package news.agoda.com.sample.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import news.agoda.com.sample.ThisApplication;
import news.agoda.com.sample.di.viewmodel.ActivityBuildersModule;
import news.agoda.com.sample.di.viewmodel.ViewModelFactoryModule;

/**
 * Created by varun.am on 07/05/19
 */
@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ViewModelFactoryModule.class,
        ActivityBuildersModule.class
})
public interface AppComponent extends AndroidInjector<ThisApplication> {
    
    @Component.Builder
    interface Builder {
        
        @BindsInstance
        Builder application(Application application);
        
        AppComponent build();
    }
    
}
