package ru.job4j.collectionsPro;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by pacman on 07.09.17.
 * Converts the Iterator of Iterators<Integer> to the Iterator<Integer>.
 */
public class Converter {

    /**
     * Converts the nested iterator to iterator of numbers.
     *
     * @param it the iterator of iterators of integers.
     * @return The iterator of integers.
     */
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {

            Iterator<Integer> inner;

            @Override
            public boolean hasNext() {
                if (inner == null) {
                    inner = it.next();
                }
                boolean has = true;

                if (!inner.hasNext()) {
                    try {
                        inner = it.next();
                        has = inner.hasNext();
                    } catch (NoSuchElementException ex) {
                        has = false;
                    }
                }
                return has;
            }

            @Override
            public Integer next() {
                if (this.hasNext()) {
                    return this.inner.next();
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }
}
