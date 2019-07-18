package com.goweather.android_challenge;

import com.google.gson.internal.LinkedTreeMap;

import org.junit.Test;

import news.agoda.com.sample.model.MediaEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by varun.am on 2019-07-18
 */
public class TestMediaEntity {
    
    @Test
    public void testMediaEntityUrl(){
        LinkedTreeMap<Object, Object> linkedTreeMap = new LinkedTreeMap<>();
        linkedTreeMap.put("url","testUrl");
        
        MediaEntity mediaEntity = new MediaEntity(linkedTreeMap);
        
        String actual = mediaEntity.getCaption();
    
        assertNull(actual);
    }
    
    @Test
    public void testMediaEntity(){
        LinkedTreeMap<Object, Object> linkedTreeMap = new LinkedTreeMap<>();
        linkedTreeMap.put("url","testUrl");
        linkedTreeMap.put("format", "testFormat");
        linkedTreeMap.put("height", 34d);
        linkedTreeMap.put("width", 45d);
        linkedTreeMap.put("type", "testType");
        linkedTreeMap.put("subtype", "testSubtype");
        linkedTreeMap.put("caption", "testCaption");
        linkedTreeMap.put("copyright", "TestCopyright");
        
        MediaEntity actual = new MediaEntity(linkedTreeMap);
        MediaEntity expected = new MediaEntity();
        expected.setCaption("testCaption");
        expected.setUrl("testUrl");
        expected.setHeight((double) 34);
        expected.setWidth((double) 45);
        expected.setSubType("testSubtype");
        expected.setCaption("testCaption");
        expected.setCopyright("TestCopyright");
        expected.setFormat("testFormat");
        
        
        assertEquals(expected.getHeight(), actual.getHeight());
        assertEquals(expected.getWidth(), actual.getWidth());
    }
    
}
