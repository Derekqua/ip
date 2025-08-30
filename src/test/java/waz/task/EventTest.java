package waz.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    void toDataString_validInput_correctFormat() {
        Event e = new Event("Meeting", "10pm", "12pm");
        String result = e.toDataString();

        assertEquals("E | 0 | Meeting | 10-12pm", result);
    }

    @Test
    void toString_validInput_correctFormat() {
        Event e = new Event("Meeting", "10am", "12pm");
        String result = e.toString();

        assertEquals("[E][ ] Meeting (from: 10am to: 12pm)", result);
    }

}
