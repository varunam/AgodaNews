package news.agoda.com.sample.di.news;

import androidx.lifecycle.ViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import news.agoda.com.sample.di.viewmodel.ViewModelKey;
import news.agoda.com.sample.viewmodel.MainViewModel;

/**
 * Created by varun.am on 14/05/19
 */
@Module
public abstract class NewsViewModelsModule {
    
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    public abstract ViewModel bindAuthViewModel(MainViewModel viewModel);
    
}
