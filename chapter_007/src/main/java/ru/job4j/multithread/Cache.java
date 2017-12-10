package ru.job4j.multithread;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 * The cache implementation, that contains models.
 * Models sets by ID.
 */
public class Cache {
    /**
     * The store of models.
     */
    private final Map<Integer, Model> map = new ConcurrentHashMap<>();

    /**
     * Adds model  with specified ID to the cache.
     *
     * @param id    - id.
     * @param model - model.
     */
    public void add(Integer id, Model model) {
        this.map.put(id, model);
    }

    /**
     * Updates the model with specified ID.
     * If the old model is changed during the time of this code, then will be thrown OptimisticException.
     *
     * @param id       - old model's ID.
     * @param newModel - new model, can be filled all fields besides version.
     */
    public void update(Integer id, Model newModel) {
        Integer version = map.get(id).getVersion();
        BiFunction<Integer, Model, Model> updater = (key, oldValue) -> {
            if (!oldValue.getVersion().equals(version)) {
                throw new OptimisticException();
            }
            newModel.setVersion(version + 1);
            return newModel;
        };
        this.map.computeIfPresent(id, updater);
    }

    /**
     * Removes the model with specified ID from the cache.
     */
    public void delete(Integer id) {
        this.map.remove(id);
    }

    /**
     * Returns the model by the ID, or null if not contains such model.
     *
     * @param id - id.
     * @return - model.
     */
    public Model get(Integer id) {
        return this.map.get(id);
    }
}
