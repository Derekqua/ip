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
     * @param commandInput the description of the Event task
     */
    public AddEventCommand(String commandInput) {
        super(commandInput);
    }

    /**
     * Executes the command by creating an Event task and adding it to the task list.
     * <p>
     *     The method also updates the Ui to show the newly added task and persists the updated list to the storage file
     * </p>>
     * @param tasks the list of task
     * @param ui the Ui to show feedback to the user
     * @param storage the storage to save the updated task list
     * @return a formatted string
     * @throws WazException if the description, /from, /to parts are missing or empty
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws WazException {
        String[] commandParts = commandInput.split("/from", 2);

        boolean isDescriptionEmpty = commandParts[0].trim().isEmpty();
        boolean isFromEmpty = commandParts.length < 2;

        if (isDescriptionEmpty) { // Check if /from is missing or description is empty
            throw new WazException("A event task needs a description!");
        } else if (isFromEmpty) {
            throw new WazException("A event task must include /from and /to!");
        }

        String[] time = commandParts[1].split("/to", 2); // from and to

        boolean isStartTimeEmpty = time[0].trim().isEmpty();
        boolean isTimeEmpty = time.length < 2 || isStartTimeEmpty || time[1].trim().isEmpty(); // Check start & end time

        // Check if /to is missing or description empty
        if (isTimeEmpty) {
            throw new WazException("A event task must include /from and /to!");
        }

        assert !time[0].trim().isEmpty() && !time[1].trim().isEmpty() : "/from and /to must be " + "specified";
        String description = commandParts[0].trim();
        String startTime = time[0].trim();
        String endTime = time[1].trim();

        Task eventTask = new Event(description, startTime, endTime);
        tasks.addTask(eventTask);
        storage.saveContent(tasks.getTaskList());
        return ui.showAddedTask(eventTask, tasks.size());
    }
}
