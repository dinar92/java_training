package ru.job4j.tracker;

/**
 * Checks credentials.
 */
public interface Credential<T, U> {

    boolean isCredential(T t, U u) throws Exception;
}
