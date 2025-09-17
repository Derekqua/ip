package waz.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import waz.tag.Tag;

/**
 * Represents a generic task
 * <p>
 * The {@code Task} class serves as the base class for all types of tasks,
 * such as {@link Todo}, {@link Event}, and {@link Deadline}. It stores
 * a description and completion status.
 * </p>
 *
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    private final List<Tag> tags = new ArrayList<>();

    /**
     * Constructs a new Task with the given description
     * The task is defaulted to not done. (isDone = false)
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon which represent whether the task is done
     * @return "X" if done, " " otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Format task into String to be saved in the file
     * @return a formatted string for storage
     */
    public abstract String toDataString();

    /**
     * Returns a string representation of this task to be displayed to the user
     * @return a string in the format "[ ] description" or "[X] description"
     */
    @Override
    public String toString() {
        String formattedString = "[" + getStatusIcon() + "] " + description;
        return formattedString;
    }
    /**
     * Returns a formatted string representation of the tags for this task.
     * <p>
     * If the task has no tags, an empty string is returned.
     * Otherwise, each tag is converted to its string form and concatenated,
     * separated by a space, with a leading space added before the first tag.
     * </p>
     *
     * @return a string representing all tags of the task, formatted for display
     */
    public String getTagsString() {
        String tagString = tags.isEmpty() ? "" : tags.stream()
                        .map(Tag::toString)
                        .collect(Collectors.joining(" "));
        return tagString;
    }
    /**
     * Add a tag to task
     * @param tag the tag object to add
     */
    public void addTag(Tag tag) {
        tags.add(tag);
    }
    /**
     * add a list of tags to task
     * @param newTags the list of new tags to add to task object
     */
    public void addTags(List<Tag> newTags) {
        tags.addAll(newTags);
    }
    /**
     * Checks if the task already has a tag with the same name.
     * @param tag the tag to check for
     * @return true if a tag with the same name exists, false otherwise
     */
    public boolean hasTag(Tag tag) {
        return tags.contains(tag);
    }

}
