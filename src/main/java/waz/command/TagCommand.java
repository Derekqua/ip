package waz.command;

import waz.exception.WazException;
import waz.storage.Storage;
import waz.tag.Tag;
import waz.task.Task;
import waz.task.TaskList;
import waz.ui.Ui;

/**
 * Represents a command to add a {@link Tag} to a {@link Task} in the {@link TaskList}.
 * <p>
 *     The command takes an input of the form "tag tasknumber #taskname"
 *     It validates the task number and tag format, checks if the task already has the tag,
 *     and adds the tag if it does not exist yet.
 * </p>
 */
public class TagCommand extends Command {
    /**
     * Constructs a TagCommand with the given command input.
     *
     * @param commandInput the raw input string containing task number and tag
     */
    public TagCommand(String commandInput) {
        super(commandInput);
    }
    /**
     * Executes the tag command by adding a tag to a specific task.
     * <p>
     *     The method performs the following steps:
     *     <ul>
     *         <li>Validates that the command input contains both a task number and a tag starting with "#".</li>
     *         <li>Validates that the task number is a positive integer and within the valid range of the task list.
     *         </li>
     *         <li>Checks whether the task already has the tag.</li>
     *         <li>Adds the tag to the task and persists the updated task list using {@link Storage}.</li>
     *     </ul>
     * </p>
     *
     * @param tasks the {@link TaskList} containing all tasks
     * @param ui the {@link Ui} used to display feedback messages to the user
     * @param storage the {@link Storage} used to save updated task data
     * @return a formatted message indicating the tag has been added
     * @throws WazException if the task number or tag format is invalid,
     *                      or if the task already has the given tag
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws WazException {
        // tag 2 #tttt needs to be digit and include #
        String[] commandParts = commandInput.trim().split("\\s+#", 2);

        boolean isTagExist = commandParts.length < 2 || commandParts[1].trim().isEmpty();
        if (isTagExist) {
            throw new WazException("OOPS! Please provide a tag starting with #.");
        }

        String taskIndexString = commandParts[0].trim(); // task index but in string type
        boolean isDigit = taskIndexString.matches("\\d+");
        if (!isDigit) {
            throw new WazException("OOPS! Please provide a valid task number.");
        }

        int taskIndex = Integer.parseInt(taskIndexString) - 1;
        String tagName = commandParts[1].trim();

        // Check if task index is in the list
        boolean isNegativeNumber = taskIndex < 0;
        boolean isOutOfRange = taskIndex >= tasks.size();
        boolean isIndexOutOfRange = isNegativeNumber || isOutOfRange;
        if (isIndexOutOfRange) {
            throw new WazException("Invalid task number");
        }

        Task task = tasks.getTask(taskIndex);
        Tag newTag = new Tag(tagName);

        if (!task.hasTag(newTag)) {
            task.addTag(newTag);
            storage.saveContent(tasks.getTaskList());
            return ui.showAddTag(taskIndex + 1, tagName);
        } else {
            return ui.showTagExist(tagName);
        }
    }
}
