package waz.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import waz.exception.WazException;

public class EventTest {
    @Test
    void toDataString_validInput_correctFormat() throws WazException {
        Event e = new Event("Meeting", "2025-12-12 1800", "2025-12-13 1900");
        String result = e.toDataString();

        assertEquals("E | 0 | Meeting | 2025-12-12 1800~2025-12-13 1900 | ", result);
    }

    @Test
    void toString_validInput_correctFormat() throws WazException {
        Event e = new Event("Meeting", "2025-12-12 1800", "2025-12-12 1900");
        String result = e.toString();

        assertEquals("[E][ ] Meeting (from: Dec 12 2025 18:00 to: Dec 12 2025 19:00) ", result);
    }
}
