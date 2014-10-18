package com.basu.likefacebook.volley;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

public class LruBitmapCache extends LruCache<String, Bitmap> implements ImageCache {
	
	public LruBitmapCache() {
		this(getDefaultLruCacheSize());
	}

	private static int getDefaultLruCacheSize() {
		final int maxMemory = (int) (Runtime.getRuntime().maxMemory()/1024);
		final int cacheSize = maxMemory/8;
		return cacheSize;
	}

	public LruBitmapCache(int maxSize) {
		super(maxSize);
	}
	
	@Override
	public int sizeOf(String key, Bitmap value) {
		return value.getRowBytes() * value.getHeight() / 1024;
	}

	@Override
	public Bitmap getBitmap(String url) {
		return get(url);
	}

	@Override
	public void putBitmap(String url, Bitmap bitmap) {
		put(url, bitmap);
	}

}
