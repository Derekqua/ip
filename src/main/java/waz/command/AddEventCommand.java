package waz.command;

import waz.task.TaskList;
import waz.task.Task;
import waz.task.Event;
import waz.exception.WazException;
import waz.storage.Storage;
import waz.ui.Ui;

public class AddEventCommand extends Command {

    public AddEventCommand(String argument) {
        super(argument);
    }

    /**
     * Creates and add an waz.task.Event task into the task list
     * Update the task list into the file
     * @param taskList the list of task
     * @param ui
     * @param storage
     * @throws WazException if the description, /from, /to parts are missing or empty
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws WazException {
        String[] event = argument.split("/from", 2);

        if(event[0].trim().isEmpty()) { // Check if /from is missing or description is empty
            throw new WazException("A event task needs a description!");
        } else if (event.length < 2) {
            throw new WazException("A event task must include /from and /to!");
        }

        String[] time = event[1].split("/to", 2); // from and to

        // Check if /to is missing or description empty
        if (time.length < 2 || time[0].trim().isEmpty() || time[1].trim().isEmpty()) {
            throw new WazException("A event task must include /from and /to!");
        }

        Task deadline = new Event(event[0], time[0].trim(), time[1].trim()); // task name, from, to
        taskList.addTask(deadline);
        ui.showAddedTask(deadline, taskList.size());
        storage.saveContent(taskList.getTaskList());
    }
}