import java.util.ArrayList;

public class DeleteCommand extends Command {

    public DeleteCommand(String argument) {
        super(argument);
    }

    /**
     * Delete task from the task list based on the argument (index)
     *
     * @param taskList the list of task
     * @param ui
     * @param storage
     * @throws WazException if the number is not a digit or index is out of range
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
        taskList.deleteTask(task);
        ui.showDeletedTask(task, taskList.size());
        storage.saveContent(taskList.getTaskList());
    }
}