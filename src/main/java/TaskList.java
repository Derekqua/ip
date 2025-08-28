import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task the Task to add
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Removes a task from the list by task object.
     *
     * @param task the Task object to be deleted
     * @return the removed Task
     */
    public boolean deleteTask(Task task) {
        return taskList.remove(task);
    }

    /**
     * Retrieves a task by its index.
     *
     * @param index the 0-based index of the task
     * @return the Task at the given index
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
