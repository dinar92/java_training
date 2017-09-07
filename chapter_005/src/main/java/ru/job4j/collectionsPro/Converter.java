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

            Iterator<Integer> inner = it.next();

            @Override
            public boolean hasNext() {
                boolean has = true;
                if (!inner.hasNext()) {
                    has = it.hasNext();
                }
                return has;
            }

            @Override
            public Integer next() {
                Integer integer = 0;
                while (this.inner.hasNext() || it.hasNext()) {
                    try {
                        integer = this.inner.next();
                        break;
                    } catch (NoSuchElementException ex) {
                        inner = it.next();
                    }
                }
                return integer;
            }
        };
    }
}
