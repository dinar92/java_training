package ru.job4j.tracker;

/**
 * A role of user.
 */
public class Role {

    /**
     * A role name.
     */
    private String name;

    /**
     * An unique id.
     */
    private Integer id;

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;

        Role role = (Role) o;

        if (name != null ? !name.equals(role.name) : role.name != null) return false;
        return id != null ? id.equals(role.id) : role.id == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("Role {name='%s', id='%d'}", this.name, this.id);
    }
}
