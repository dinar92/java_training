package ru.job4j.model;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * It's a base class-task for accounting system.
 *
 * @author gimazetdinov
 * @version 1.0
 * @since 31.01.2017
 */
public class Item {
    /**
     * Name of the item.
     */
    private String name;
    /**
     * Description of the item.
     */
    private String description;
    /**
     * Date and time when item was created.
     */
    private Calendar create;
    /**
     * Max size of the comments list.
     */
    private final int maxSizeOfCommentsList = 30;
    /**
     * Array of comments.
     */
    private Comment[] comments = new Comment[maxSizeOfCommentsList];
    /**
     * ID.
     */
    private String id;
    /**
     * ID counter for ID generator.
     */
    private static int idCounter = 0;
    /**
     * An amount of elements in the array of comments.
     */
    private int commentCounter = 0;

    /**
     * Name setter.
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Description setter.
     *
     * @param desc description
     */
    public void setDescription(String desc) {
        this.description = desc;
    }

    /**
     * Date and time setter.
     *
     * @param millis - time in milliseconds
     */
    public void setCreate(long millis) {
        this.create = new GregorianCalendar();
        this.create.setTimeInMillis(millis);
    }

    /**
     * Add a comment to item.
     *
     * @param comment - text of the comment
     * @param author  - author's name
     * @param millis  - current time in milliseconds
     */
    public void addComment(String comment, String author, long millis) {
        if (this.commentCounter < this.comments.length) {
            this.comments[commentCounter] = new Comment();
            this.comments[commentCounter].setAuthor(author);
            this.comments[commentCounter].setText(comment);
            this.comments[commentCounter].setCreate(millis);
            this.commentCounter++;
        }
    }

    /**
     * Generates unique id for item.
     */
    public void generateId() {
        this.id = String.valueOf(++idCounter);
    }

    /**
     * Name getter.
     *
     * @return name of the item
     */
    public String getName() {
        return this.name;
    }

    /**
     * Description getter.
     *
     * @return description of the item
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Date and time getter.
     *
     * @return date and time, when the item was created
     */
    public String getCreate() {
        return new StringBuilder()
                .append(this.create.get(Calendar.HOUR_OF_DAY))
                .append(":")
                .append(this.create.get(Calendar.MINUTE))
                .append(" ")
                .append(this.create.get(Calendar.DAY_OF_MONTH))
                .append("/")
                .append(this.create.get(Calendar.MONTH) + 1)
                .append("/")
                .append(this.create.get(Calendar.YEAR))
                .toString();
    }

    /**
     * Comments getter.
     *
     * @return comments - array of item's comments
     */
    public Comment[] getComments() {
        return Arrays.copyOf(this.comments, commentCounter);
    }

    /**
     * ID getter.
     *
     * @return id
     */
    public String getId() {
        return this.id;
    }

    /**
     * ID setter.
     *
     * @param id - ID.
     */
    public void setId(String id) {
        this.id = id;
    }
}
