package ru.job4j.multithread;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests the class Cache.
 */
public class CacheTest {

    /**
     * Tests add(Integer, Model).
     */
    @Test
    public void whenPutModelThenModelInCache() {
        Cache cache = new Cache();
        Model model1 = new Model();
        model1.setVersion(1);
        model1.setTitle("Title");
        model1.setDescription("Article");

        cache.add(1, model1);

        assertThat(cache.get(1), is(model1));
    }

    /**
     * Tests update(Integer, Model).
     */
    @Test
    public void whenUpdateThenModelUpdated() {
        Cache cache = new Cache();
        Model model1 = new Model();
        model1.setVersion(1);
        model1.setTitle("Title");
        model1.setDescription("Article");
        Model newModel = new Model();
        newModel.setTitle("New Title");
        newModel.setDescription("New article");

        cache.add(1, model1);
        cache.update(1, newModel);

        assertThat(cache.get(1), is(newModel));
        assertThat(cache.get(1).getVersion(), is(2));
    }

    /**
     * Tests update(Integer, Model) with multithreading.
     */
    @Test
    public void whenMultiThreadUpdatingThenException() {
        Cache cache = new Cache();
        Model model1 = new Model();
        model1.setVersion(1);
        model1.setTitle("Title");
        model1.setDescription("Article");
        Model newModel = new Model();
        newModel.setTitle("New Title");
        newModel.setDescription("New article");
        cache.add(1, model1);
        List<Thread> threadList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            threadList.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    cache.update(1, newModel);
                }
            }));
        }

        for (Thread thread
                :
                threadList) {
            thread.start();
        }

        for (Thread thread
                :
                threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}