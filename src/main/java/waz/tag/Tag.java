package waz.tag;

/**
 * Represents a tag that can be assigned to a task.
 * <p>
 * A tag has a name which is normalized to lowercase and trimmed of leading/trailing spaces.
 * Two tags are considered equal if their names are the same, ignoring case differences.
 * </p>
 */
public class Tag {
    private final String name;

    /**
     * Constructs a new Tag with the given name.
     * The name is normalized by trimming whitespace and converting to lowercase.
     *
     * @param name the name of the tag
     */
    public Tag(String name) {
        this.name = name.trim().toLowerCase(); // normalize tags
    }
    /**
     * Returns a string representation of the tag.
     * The string is prefixed with "#" for display purposes.
     *
     * @return the string representation of the tag
     */
    @Override
    public String toString() {
        return "#" + name;
    }
    /**
     * Compares this tag with another object by tagName
     *
     * @param obj the object to compare with
     * @return {@code true} if the given object is a Tag with the same name (case-insensitive),
     *         {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
        Tag other = (Tag) obj;
        return this.name.equalsIgnoreCase(other.name);
    }
}
