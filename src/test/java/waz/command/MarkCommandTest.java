package waz.command;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import waz.exception.WazException;
import waz.storage.Storage;
import waz.task.TaskList;
import waz.task.Todo;
import waz.ui.Ui;

class MarkCommandTest {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage("test.txt");
    }

    @Test
    void testMarkTaskSuccessfully() throws Exception {
        Todo todo = new Todo("Read book");
        taskList.addTask(todo);
        Command markCommand = new MarkCommand("1");

        markCommand.execute(taskList, ui, storage);

        assertEquals("[T][X] Read book", taskList.getTaskList().get(0).toString());
    }

    @Test
    void testInvalidIndexThrowsException() {
        Command markCommand = new MarkCommand("5"); // no tasks

        WazException ex = assertThrows(WazException.class, () ->
                markCommand.execute(taskList, ui, storage));
        assertEquals("OOPS! That task number doesn't exist", ex.getMessage());
    }

    @Test
    void testNonNumericArgumentThrowsException() {
        Command markCommand = new MarkCommand("abc");

        WazException ex = assertThrows(WazException.class, () ->
                markCommand.execute(taskList, ui, storage));
        assertEquals("OOPS! Please provide a valid task number.", ex.getMessage());
    }

    @Test
    void testEmptyArgumentThrowsException() {
        MarkCommand cmd = new MarkCommand("");

        WazException ex = assertThrows(WazException.class, () ->
                cmd.execute(taskList, ui, storage));
        assertEquals("OOPS! Please provide a valid task number.", ex.getMessage());
    }
}
