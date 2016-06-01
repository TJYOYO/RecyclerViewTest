package com.ttjjttjj.recyclerviewtest;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.MemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.LRULimitedMemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * Created by Administrator on 2016/6/1 0001.
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader(getApplicationContext());
    }

    /**
     *
     * 开源Imageloder的全局配置
     */
    /**
     * 初始化图片加载相关
     *
     * @param context
     */
    private void initImageLoader(Context context) {
        int memoryCacheSize = (int) (Runtime.getRuntime().maxMemory() / 4);
        MemoryCache memoryCache;
        if (Build.VERSION.SDK_INT >= 9) {
            memoryCache = new LruMemoryCache(memoryCacheSize);
        } else {
            memoryCache = new LRULimitedMemoryCache(memoryCacheSize);
        }
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context).threadPriority(Thread.NORM_PRIORITY - 3)
                .memoryCache(memoryCache).denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new HashCodeFileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }
}
