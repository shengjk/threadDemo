package xmht.threadDemo.atomic;

import java.util.concurrent.atomic.AtomicMarkableReference;

public class CacheEntry<V> {
    private final V value;
    private final AtomicMarkableReference<Boolean> isExpired;

    public CacheEntry(V value, boolean initialExpiredState) {
        this.value = value;
        this.isExpired = new AtomicMarkableReference<>(initialExpiredState, false);
    }

    public V getValue() {
        return value;
    }

    public boolean isExpired() {
        return isExpired.isMarked();
    }

    public void expire() {
        isExpired.compareAndSet(false, true, false, true);
    }

    public static void main(String[] args) {
        CacheEntry<String> entry = new CacheEntry<>("Cached Value", false);
        System.out.println("Cached Value: " + entry.getValue() + ", Expired: " + entry.isExpired());

        // 过期缓存条目
        entry.expire();
        System.out.println("Cached Value: " + entry.getValue() + ", Expired: " + entry.isExpired());
    }
}