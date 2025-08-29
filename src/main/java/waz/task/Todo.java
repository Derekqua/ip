package waz.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Format the waz.task.Task object into String to be saved in the file
     * @return a formatted waz.task.Task string
     */
    @Override
    public String toDataString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
