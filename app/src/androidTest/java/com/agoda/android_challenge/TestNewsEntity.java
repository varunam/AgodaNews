package com.agoda.android_challenge;

import androidx.test.runner.AndroidJUnit4;

import com.google.gson.internal.LinkedTreeMap;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import news.agoda.com.sample.model.MediaEntity;
import news.agoda.com.sample.model.NewsEntity;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class TestNewsEntity {
    
    @Test
    public void testGetMediaEntity() {
        // Context of the app under test.
        NewsEntity newsEntity = new NewsEntity("testTitle",
                "testSummary",
                "testArticleUrl",
                "byLine",
                "published_Date",
                new ArrayList<LinkedTreeMap<Object, Object>>());
        
        List<MediaEntity> actual = newsEntity.getMediaEntity();
        List<MediaEntity> expected = new ArrayList<>();
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void testNewsEntity(){
        NewsEntity newsEntity = new NewsEntity("testTitle",
                "testSummary",
                "testArticleUrl",
                "byLine",
                "published_Date",
                new ArrayList<LinkedTreeMap<Object, Object>>());
        
        String actual = newsEntity.getArticleUrl();
        String expected = "testArticleUrl";
        
        assertEquals(expected, actual);
    }
}
