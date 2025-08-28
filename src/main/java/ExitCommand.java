import java.util.ArrayList;

public class ExitCommand extends Command{
    public ExitCommand() {
        super("");
    }

    /**
     * Exits the loop
     *
     * @param taskList the list of task
     * @param ui
     * @param storage
     */
    @Override
    public void execute(ArrayList<Task> taskList, Ui ui, Storage storage) {
        ui.exit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
