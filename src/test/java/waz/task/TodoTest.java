package waz.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void toDataString_returnsCorrectFormatWhenNotDone() {
        Todo todo = new Todo("read book");
        String result = todo.toDataString();

        assertEquals("T | 0 | read book", result);
    }

    @Test
    public void toDataString_returnsCorrectFormatWhenIsDone() {
        Todo todo = new Todo("read book");
        todo.markAsDone();
        String result = todo.toDataString();

        assertEquals("T | 1 | read book", result);
    }

    @Test
    public void toString_includeTaskType() {
        Todo todo = new Todo("Testing");
        String result = todo.toString();

        assertEquals("[T][ ] Testing", result);
    }

}
