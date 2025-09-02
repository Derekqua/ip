package waz.command;

import waz.exception.WazException;
import waz.storage.Storage;
import waz.task.Event;
import waz.task.Task;
import waz.task.TaskList;
import waz.ui.Ui;

/**
 * Represents a command to add {@link Event} task to the task list
 * <p>
 *     When executed, this command validates the input description, start time and end time. Creates a new Event task
 *     and adds it to the {@link TaskList}, updates the Ui, save the updated list to storage.
 * </p>
 */
public class AddEventCommand extends Command {
    /**
     * Constructs an AddEventCommand with the given task description, start time, end time
     * @param argument the description of the Event task
     */
    public AddEventCommand(String argument) {
        super(argument);
    }

    /**
     * Executes the command by creating an Event task and adding it to the task list.
     * <p>
     *     The method also updates the Ui to show the newly added task and persists the updated list to the storage file
     * </p>>
     * @param taskList the list of task
     * @param ui the Ui to show feedback to the user
     * @param storage the storage to save the updated task list
     * @throws WazException if the description, /from, /to parts are missing or empty
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws WazException {
        String[] event = argument.split("/from", 2);

        if (event[0].trim().isEmpty()) { // Check if /from is missing or description is empty
            throw new WazException("A event task needs a description!");
        } else if (event.length < 2) {
            throw new WazException("A event task must include /from and /to!");
        }

        String[] time = event[1].split("/to", 2); // from and to

        // Check if /to is missing or description empty
        if (time.length < 2 || time[0].trim().isEmpty() || time[1].trim().isEmpty()) {
            throw new WazException("A event task must include /from and /to!");
        }

        Task deadline = new Event(event[0].trim(), time[0].trim(), time[1].trim()); // task name, from, to
        taskList.addTask(deadline);
        ui.showAddedTask(deadline, taskList.size());
        storage.saveContent(taskList.getTaskList());
    }
}
