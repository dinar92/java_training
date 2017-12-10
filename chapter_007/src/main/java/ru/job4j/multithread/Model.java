package ru.job4j.multithread;

/**
 * An item for cache.
 */
public class Model {
    /**
     * Version of the model for correct concurrency working.
     */
    private volatile Integer version = 0;
    /**
     * The title of the model.
     */
    private String title;
    /**
     * The description of the model.
     */
    private String description;

    /**
     * The version getter.
     *
     * @return - version.
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * The version setter.
     *
     * @param version - version.
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * The title getter.
     *
     * @return - title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * The title setter.
     *
     * @param title - title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * The description getter.
     *
     * @return - description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * The description setter.
     *
     * @param description - description.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
