package waz.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import waz.exception.WazException;
import waz.storage.Storage;
import waz.task.Event;
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
        Command addEventCommand = new AddEventCommand("Meetup /from 2025-12-12 1800 /to 2025-12-13 1900 ");
        addEventCommand.execute(taskList, ui, storage);

        assertEquals(1, taskList.size());
        assertEquals("[E][ ] Meetup (from: Dec 12 2025 18:00 to: Dec 13 2025 19:00) ",
                taskList.getTaskList().get(0).toString());
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
        Command cmd = new AddEventCommand("party /from Dec 12 2025 18:00");

        WazException ex = assertThrows(WazException.class, () ->
                cmd.execute(taskList, ui, storage));

        assertEquals("A event task must include /from and /to!", ex.getMessage());
    }

    @Test
    void endTimeBeforeStart_throwsWazException() throws WazException {
        String start = "2025-12-13 1800";
        String end = "2025-12-12 1900";

        assertThrows(WazException.class, () -> {
            new Event("Invalid Meeting", start, end);
        });
    }
}
