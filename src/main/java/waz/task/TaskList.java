package waz.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks
 * <p>
 * The {@code TaskList} class manages a collection of {@link Task} objects,
 * providing operations to add, delete, retrieve, and inspect tasks.
 * </p>
 *
 * <p>Responsibilities include:</p>
 * <ul>
 *     <li>Adding tasks to the list</li>
 *     <li>Deleting tasks from the list</li>
 *     <li>Retrieving tasks by index</li>
 *     <li>Retrieve the total number of tasks</li>
 *     <li>Retrieve the full list of tasks if needed</li>
 * </ul>
 */
public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given list of tasks
     * @param tasks an ArrayList of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task the {@link Task} to add
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Removes a task from the list by task object.
     *
     * @param task the {@link Task} object to remove
     * @return {@code true} if the task was successfully removed, {@code false} otherwise
     */
    public boolean deleteTask(Task task) {
        return taskList.remove(task);
    }

    /**
     * Retrieves a task by its index.
     *
     * @param index the 0-based index of the task
     * @return the {@link Task} at the given index
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return size of the task list
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns the internal list of tasks.
     *
     * @return the ArrayList of tasks
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }
}
