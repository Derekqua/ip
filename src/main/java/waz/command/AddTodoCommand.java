package waz.command;

import waz.task.TaskList;
import waz.task.Task;
import waz.task.Todo;
import waz.exception.WazException;
import waz.storage.Storage;
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
     * @param argument the description of the Todo task
     */
    public AddTodoCommand(String argument) {
        super(argument);
    }

    /**
     * Executes the command by creating a Todo task and adding it to the task list.
     * <p>
     *     The method also updates the Ui to show the newly added task and persists the updated list to the storage file
     * </p>>
     * @param taskList the list of task
     * @param ui the Ui to show feedback to the user
     * @param storage the storage to save the updated task list
     * @throws WazException if the task description is empty
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws WazException {
        if (argument.trim().isEmpty()) {
            throw new WazException("A todo task needs a description!");
        }
        Task todo = new Todo(argument.trim());
        taskList.addTask(todo);
        ui.showAddedTask(todo, taskList.size());
        storage.saveContent(taskList.getTaskList());
    }
}