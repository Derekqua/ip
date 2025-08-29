package waz.command;

import waz.task.TaskList;
import waz.task.Task;
import waz.task.Deadline;
import waz.exception.WazException;
import waz.storage.Storage;
import waz.ui.Ui;

public class AddDeadlineCommand extends Command {

    public AddDeadlineCommand(String argument) {
        super(argument);
    }

    /**
     * Creates and adds a waz.task.Deadline waz.task.Task into the task list
     * Update the task list into the file
     *
     * @param taskList the list of task
     * @param ui
     * @param storage
     * @throws WazException if the description or deadline is missing/invalid
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws WazException {
        String[] parts = argument.split("/by", 2);

        if (parts[0].trim().isEmpty()) { // Check if description is empty
            throw new WazException("A deadline task needs a description!");
        } else if (parts.length < 2 || parts[1].trim().isEmpty()) { // Check if /by is missing or deadline is empty
            throw new WazException("A deadline task needs a deadline!");
        }

        Task deadline = new Deadline(parts[0].trim(), parts[1].trim()); // task name, deadline by...
        taskList.addTask(deadline);
        ui.showAddedTask(deadline, taskList.size());
        storage.saveContent(taskList.getTaskList());
    }
}