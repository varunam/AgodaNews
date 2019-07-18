package news.agoda.com.sample.view;

import news.agoda.com.sample.model.NewsEntity;

/**
 * Created by varun.am on 2019-07-18
 */
public interface ClickedCallback {
    void onNewsItemClicked(NewsEntity clickedNews);
}
