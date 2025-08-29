package waz.command;

import waz.task.TaskList;
import waz.task.Task;
import waz.exception.WazException;
import waz.storage.Storage;
import waz.ui.Ui;

public class MarkCommand extends Command {

    public MarkCommand(String argument) {
        super(argument);
    }


    /**
     * Marks the task in the task list based on argument (index)
     *
     * @param taskList the list of task
     * @param ui
     * @param storage
     * @throws WazException if the index is invalid or out of range
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws WazException {
        if (argument.isEmpty() || !argument.matches("\\d+")) {
            throw new WazException("OOPS! Please provide a valid task number.");
        }

        int index = Integer.parseInt(argument) - 1;
        if (index < 0 || index >= taskList.size()) {
            throw new WazException("OOPS! That task number doesn't exist");
        }

        Task task =  taskList.getTask(index);
        task.markAsDone();
        ui.showMarkTask(task);
        storage.saveContent(taskList.getTaskList());
    }
}
