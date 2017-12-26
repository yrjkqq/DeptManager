package com.cdsxt.util;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocal<T> {
    // 保存 Session 的集合
    private Map<Thread, T> cache = new HashMap<>();

    public void set(T object) {
        cache.put(Thread.currentThread(), object);
    }

    public T get() {
        return cache.get(Thread.currentThread());
    }

    public void remove() {
        cache.remove(Thread.currentThread());
    }
}
