import java.util.ArrayList;

public abstract class Command {
    protected String argument;

    public Command(String argument) {
        this.argument = argument;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws WazException;

    /**
     * Exit loop
     *
     * @return
     */
    public boolean isExit() {
        return false;
    }
}
