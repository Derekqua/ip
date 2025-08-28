import java.util.ArrayList;

public class AddTodoCommand extends Command {

    public AddTodoCommand(String argument) {
        super(argument);
    }

    /**
     * Creates and add a Todo task into the task list
     * Update the task list into the file
     * @param taskList the list of task
     * @param ui
     * @param storage
     * @throws WazException if the description is empty
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws WazException {
        if (argument.trim().isEmpty()) {
            throw new WazException("A todo task needs a description!");
        }
        Task todo = new Todo(argument);
        taskList.addTask(todo);
        ui.showAddedTask(todo, taskList.size());
        storage.saveContent(taskList.getTaskList());
    }
}