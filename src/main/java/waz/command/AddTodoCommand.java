package waz.command;

import waz.exception.WazException;
import waz.storage.Storage;
import waz.task.Task;
import waz.task.TaskList;
import waz.task.Todo;
import waz.ui.Ui;

/**
 * Represents a command to add {@link Todo} task to the task list
 * <p>
 *     When executed, this command validates the input description, creates a new Todo task and adds it to the
 *     {@link TaskList}, updates the Ui, save the updated list to storage.
 * </p>
 */
public class AddTodoCommand extends Command {

    /**
     * Constructs an AddTodoCommand with the given task description
     * @param commandInput the description of the Todo task
     */
    public AddTodoCommand(String commandInput) {
        super(commandInput);
    }

    /**
     * Executes the command by creating a Todo task and adding it to the task list.
     * <p>
     *     The method also updates the Ui to show the newly added task and persists the updated list to the storage file
     * </p>>
     * @param tasks the list of task
     * @param ui the Ui to show feedback to the user
     * @param storage the storage to save the updated task list
     * @return a formatted string
     * @throws WazException if the task description is empty
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws WazException {
        boolean isDescriptionEmpty = commandInput.trim().isEmpty();
        assert !isDescriptionEmpty : "Description should not be empty";

        if (isDescriptionEmpty) {
            throw new WazException("A todo task needs a description!");
        }

        String description = commandInput.trim();
        Task todoTask = new Todo(description);
        tasks.addTask(todoTask);
        storage.saveContent(tasks.getTaskList());
        return ui.showAddedTask(todoTask, tasks.size());
    }
}
