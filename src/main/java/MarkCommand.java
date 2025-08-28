import java.util.ArrayList;

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
    public void execute(ArrayList<Task> taskList, Ui ui, Storage storage) throws WazException {
        if (argument.isEmpty() || !argument.matches("\\d+")) {
            throw new WazException("OOPS! Please provide a valid task number.");
        }

        int index = Integer.parseInt(argument) - 1;
        if (index < 0 || index >= taskList.size()) {
            throw new WazException("OOPS! That task number doesn't exist");
        }

        Task task =  taskList.get(index);
        task.markAsDone();
        ui.showMarkTask(task);
        storage.saveContent(taskList);
    }
}
