package ru.job4j.sql.jdbc;

import javax.xml.bind.annotation.*;

/**
 * The simple mapped class which contains one integer field and
 * access methods.
 */
@XmlRootElement(name = "entry")
@XmlAccessorType(XmlAccessType.FIELD)
public class Entry {

    /**
     * The integer field.
     */
    @XmlAttribute(name = "field", required = true)
    private int field;

    /**
     * Field's getter.
     *
     * @return - field's value.
     */
    public int getField() {
        return field;
    }

    /**
     * Sets the field's value.
     *
     * @param field - field's value.
     */
    public void setField(int field) {
        this.field = field;
    }
}
