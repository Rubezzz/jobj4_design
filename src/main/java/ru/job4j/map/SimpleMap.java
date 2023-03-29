package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        int index = key == null ? 0 : indexFor(hash(key.hashCode()));
        boolean rsl = table[index] == null;
        if (rsl) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            if ((float) count / (float) capacity >= LOAD_FACTOR) {
                expand();
            }
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return (capacity - 1) & hash;
    }

    private void expand() {
        MapEntry<K, V>[] tmpTable = Arrays.copyOf(table, capacity);
        capacity *= 2;
        count = 0;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> entry : tmpTable) {
            if (entry != null) {
                put(entry.key, entry.value);
            }
        }
    }

    @Override
    public V get(K key) {
        V rsl = null;
        if (key == null) {
            rsl = table[0] != null && table[0].key == null ? table[0].value : null;
        } else {
            MapEntry<K, V> entry = table[indexFor(hash(key.hashCode()))];
            if (entry != null
                && entry.key != null
                && entry.key.hashCode() == key.hashCode()
                && entry.key.equals(key)) {
                rsl = entry.value;
            }
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        int index = key == null ? 0 : indexFor(hash(key.hashCode()));
        boolean rsl = (index == 0 && table[index] != null && table[index].key == null)
                      || (table[index] != null && table[index].key != null && key != null
                          && key.hashCode() == table[index].key.hashCode()
                          && key.equals(table[index].key));
        if (rsl) {
            table[index] = null;
            count--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {

            private final int expectedModCount = modCount;
            private int index;
            private int count;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < capacity && this.count < SimpleMap.this.count;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                MapEntry<K, V> entry = null;
                while (entry == null && index < capacity) {
                    entry = table[index++];
                }
                count++;
                return entry.key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}