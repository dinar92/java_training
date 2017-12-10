package ru.job4j.multithread;

/**
 * The thread safety exception.
 * Throws when the changed object has already been changed.
 */
public class OptimisticException extends RuntimeException {

    /**
     * The name of the exception.
     *
     * @return - name.
     */
    @Override
    public String toString() {
        return new String("Optimistic exception");
    }
}
