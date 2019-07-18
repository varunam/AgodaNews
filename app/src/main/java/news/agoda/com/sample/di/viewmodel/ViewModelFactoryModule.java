package news.agoda.com.sample.di.viewmodel;

import androidx.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;

/**
 * Created by varun.am on 14/05/19
 */
@Module
public abstract class ViewModelFactoryModule {
    
    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelsProviderFactory viewModelsProviderFactory);
    
}
