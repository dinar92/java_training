package sqlru.jobparser;

import java.time.LocalDateTime;

/**
 * A job offer's object model implementation from the sql.ru.
 */
public class Offer {

    /**
     * A nickname of offer's author.
     */
    private String author;

    /**
     * A title of the offer.
     */
    private String title;

    /**
     * A content of the offer.
     */
    private String content;

    /**
     * Date and time of offer.
     */
    private LocalDateTime postTimeDate;

    private String url;

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPostTimeDate(LocalDateTime postTimeDate) {
        this.postTimeDate = postTimeDate;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getPostTimeDate() {
        return postTimeDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Returns text representation of the object.
     *
     * @return - text representation.
     */
    @Override
    public String toString() {
        return "Offer{"
                + "author='"
                + author
                + '\''
                + ", title='"
                + title + '\''
                + ", content='"
                + content + '\''
                + ", postTimeDate="
                + postTimeDate
                + ", url='"
                + url
                + '\''
                + '}';
    }

    /**
     * Determines the equivalence of objects.
     *
     * @param o - another object.
     * @return - true - if equivalent, false - otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Offer)) {
            return false;
        }

        Offer offer = (Offer) o;

        if (author != null ? !author.equals(offer.author) : offer.author != null) {
            return false;
        }
        if (title != null ? !title.equals(offer.title) : offer.title != null) {
            return false;
        }
        if (content != null ? !content.equals(offer.content) : offer.content != null) {
            return false;
        }
        if (postTimeDate != null ? !postTimeDate.equals(offer.postTimeDate) : offer.postTimeDate != null) {
            return false;
        }
        return url != null ? url.equals(offer.url) : offer.url == null;
    }

    /**
     * Returns hashcode of current object.
     *
     * @return - hashcode.
     */
    @Override
    public int hashCode() {
        int result = author != null ? author.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (postTimeDate != null ? postTimeDate.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
