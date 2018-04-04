package ru.job4j.sql.jdbc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * The simple mapped class which contains the list of entries (List<Entry>).
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Entries {

    /**
     * The entries list.
     */
    @XmlElement(name = "entry")
    private List<Entry> entries = new ArrayList<>();

    /**
     * The list's getter.
     *
     * @return - the list of entries.
     */
    public List<Entry> getEntries() {
        return entries;
    }

    /**
     * Sets the list of entries.
     *
     * @param entries - the list of entries.
     */
    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }
}
