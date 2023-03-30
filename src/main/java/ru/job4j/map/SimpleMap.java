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
        int index = indexFor(key);
        boolean rsl = table[index] == null;
        if (rsl) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            if (count >= capacity * LOAD_FACTOR) {
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

    private int indexFor(K key) {
        return key == null ? 0 : indexFor(hash(key.hashCode()));
    }

    private boolean keysEquals(K key1, K key2) {
        return Objects.hashCode(key1) == Objects.hashCode(key2) && Objects.equals(key1, key2);
    }

    private void expand() {
        MapEntry<K, V>[] tmpTable = Arrays.copyOf(table, capacity);
        capacity *= 2;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> entry : tmpTable) {
            if (entry != null) {
                table[indexFor(entry.key)] = entry;
            }
        }
    }

    @Override
    public V get(K key) {
        MapEntry<K, V> entry = table[indexFor(key)];
        return entry != null && keysEquals(entry.key, key) ? entry.value : null;
    }

    @Override
    public boolean remove(K key) {
        int index = indexFor(key);
        boolean rsl = table[index] != null && keysEquals(key, table[index].key);
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

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < capacity && table[index] == null) {
                    index++;
                }
                return index < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
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