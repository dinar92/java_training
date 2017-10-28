package ru.job4j.collections.mapCollection;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A simple implementation of the map base on open addressing method.
 *
 * @param <K> key.
 * @param <V> value.
 */
public class Directory<K, V> implements Iterable {

    /**
     * A store.
     */
    Entry<K, V>[] store;

    /**
     * Sets a size of the store.
     * The size must be a prime number.
     *
     * @param size size.
     */
    public Directory(int size) {
        this.store = new Entry[size];
    }

    /**
     * Returns a cell's index of key.
     *
     * @param key - the key.
     * @return the cell's index.
     */
    private int hash(K key) {
        return key.hashCode() % this.store.length;
    }

    /**
     * Inserts a value with a specified key in the directory.
     *
     * @param key   - the key.
     * @param value - the value.
     * @return success of insertion: true - inserted, false - failure.
     */
    public boolean insert(K key, V value) {
        int hashKey = hash(key);
        boolean result = true;
        if (store[hashKey] != null) {
            result = false;
        } else {
            store[hashKey] = new Entry<>(key, value);
        }
        return result;
    }

    /**
     * Returns a value with a specified key,
     * or null if such key is not exist.
     *
     * @param key - the key.
     * @return value or null.
     */
    public V get(K key) {
        return store[hash(key)] == null ? null : store[hash(key)].getValue();
    }


    /**
     * Removes a value with a specified key from the directory.
     * Returns true if the value was delete, or false
     * such key not found.
     *
     * @param key - key.
     * @return true - was delete, false - the key not found.
     */
    public boolean delete(K key) {
        int hashKey = hash(key);
        boolean result = true;
        if (store[hashKey] == null) {
            result = false;
        } else {
            store[hashKey] = null;
        }
        return result;
    }

    /**
     * An instance of iterator of directory.
     * Provides access to Directory.Entry class, which contains
     * key and value of any item.
     * @return an iterator.
     */
    @Override
    public Iterator iterator() {
        return new DirectoryIterator();
    }

    /**
     * An Iterator of the Directory.
     */
    private class DirectoryIterator implements Iterator<Entry<K, V>> {

        /**
         * A position of the current entry.
         */
        int currentEntry = 0;

        /**
         * Sets a position to first not null position.
         */
        DirectoryIterator() {
            this.currentEntry = getNextNotNull(this.currentEntry);
        }

        /**
         * Returns true if the iteration has more elements.
         *
         * @return true | false;
         */
        @Override
        public boolean hasNext() {
            return currentEntry != store.length;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public Directory.Entry next() {
            if (this.currentEntry == store.length) {
                throw new NoSuchElementException();
            }
            Directory.Entry entry = Directory.this.new Entry<>((K) store[currentEntry].key, (V) store[currentEntry].value);
            this.currentEntry = this.getNextNotNull(this.currentEntry + 1);
            return entry;
        }

        /**
         * Returns the position of the next entry.
         * @param currentEntry - start position to search.
         * @return - the next position of the next entry.
         * @throws NoSuchElementException - The Directory has no more entries.
         */
        private int getNextNotNull(int currentEntry) throws NoSuchElementException {
            while ((currentEntry < store.length) && (store[currentEntry] == null)) {
                currentEntry++;
            }
            return currentEntry;
        }
    }

    /**
     * A container for the pair key-value.
     * @param <K> - the key.
     * @param <V> - the value.
     */
    final class Entry<K, V> {

        /**
         * The key.
         */
        private K key;

        /**
         * The value.
         */
        private V value;

        /**
         * Sets pair key-value.
         * @param key - the key.
         * @param value - the value.
         */
        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Getter of the key.
         * @return - the key.
         */
        public K getKey() {
            return key;
        }

        /**
         * Getter of the value.
         * @return - the value.
         */
        public V getValue() {
            return value;
        }
    }
}
