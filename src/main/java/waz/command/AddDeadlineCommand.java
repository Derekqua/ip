package waz.command;

import waz.exception.WazException;
import waz.storage.Storage;
import waz.task.Deadline;
import waz.task.Task;
import waz.task.TaskList;
import waz.ui.Ui;

/**
 * Represents a command to add {@link Deadline} task to the task list
 * <p>
 *     When executed, this command validates the input description, start time and end time. Creates a new Deadline task
 *     and adds it to the {@link TaskList}, updates the Ui, save the updated list to storage.
 * </p>
 */
public class AddDeadlineCommand extends Command {
    /**
     * Constructs an AddDeadlineCommand with the given task description, deadline
     * @param commandInput the description of the Deadline task
     */
    public AddDeadlineCommand(String commandInput) {
        super(commandInput);
    }

    /**
     * Executes the command by creating a Deadline task and adding it to the task list.
     * <p>
     *     The method also updates the Ui to show the newly added task and persists the updated list to the storage file
     * </p>>
     * @param tasks the list of task
     * @param ui the Ui to show feedback to the user
     * @param storage the storage to save the updated task list
     * @return a formatted string
     * @throws WazException if the description, /by parts are missing or empty
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws WazException {
        String[] commandParts = commandInput.split("/by", 2);

        boolean isDescriptionEmpty = commandParts[0].trim().isEmpty();
        boolean isDeadlineMissing = commandParts.length < 2 || commandParts[1].trim().isEmpty();;

        assert !isDescriptionEmpty : "Description should not be empty";
        assert commandParts.length == 2 && !commandParts[1].trim().isEmpty() : "Deadline should not be empty";

        if (isDescriptionEmpty) { // Check if description is empty
            throw new WazException("A deadline task needs a description!");
        } else if (isDeadlineMissing) { // Check if /by is missing or deadline is empty
            throw new WazException("A deadline task needs a deadline!");
        }

        String description = commandParts[0].trim();
        String deadline = commandParts[1].trim();

        Task deadlineTask = new Deadline(description, deadline); // task name, deadline by...
        tasks.addTask(deadlineTask);
        storage.saveContent(tasks.getTaskList());
        return ui.showAddedTask(deadlineTask, tasks.size());
    }
}
