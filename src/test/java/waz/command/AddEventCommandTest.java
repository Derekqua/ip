package waz.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import waz.exception.WazException;
import waz.storage.Storage;
import waz.task.TaskList;
import waz.ui.Ui;

public class AddEventCommandTest {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    void setup() {
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage("test.txt");
    }

    @Test
    void execute_validInput_addsEventSuccessfully() throws Exception {
        Command addEventCommand = new AddEventCommand("party /from 2pm /to 6pm");
        addEventCommand.execute(taskList, ui, storage);

        assertEquals(1, taskList.size());
        assertEquals("[E][ ] party (from: 2 to: 6pm)", taskList.getTaskList().get(0).toString());
    }

    @Test
    void execute_missingDescription_throwsException() {
        Command cmd = new AddEventCommand("/from Monday /to Tuesday");

        WazException ex = assertThrows(WazException.class, () ->
                cmd.execute(taskList, ui, storage));

        assertEquals("A event task needs a description!", ex.getMessage());
    }

    @Test
    void execute_missingTo_throwsException() {
        Command cmd = new AddEventCommand("party /from Monday");

        WazException ex = assertThrows(WazException.class, () ->
                cmd.execute(taskList, ui, storage));

        assertEquals("A event task must include /from and /to!", ex.getMessage());
    }
}
