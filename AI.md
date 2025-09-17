# AI Usage Log

This file records my use of AI tools during the development of this project.

---

## Week 6

- **Tool Used:** ChatGPT
- **Tasks:**
    - Helped me refactor my `Ui` class to use **StringBuilder** instead of repeated string concatenation (improved efficiency).
    - Guided me on **OOP design choices**, such as whether to use a single `showMessage(String msg)` method vs. keeping specialized methods, and recommended a middle-ground approach.
    - Suggested improvements like extracting **constant messages into `static final` fields** for cleaner, reusable code.
    - Guided me on how to validate that an Event's end time is later than its start time using LocalDateTime.isAfter
      () in Java.
- **Observations:**
    - The AI was helpful in catching **consistency issues** (extra newlines, pluralization, formatting).
    - The explanations about trade-offs (clarity vs. simplicity) were especially useful for making design decisions.
    - Clarified that comparing datetime string directly is unreliable, and that LocalDateTIme comparison is correct.
    - Saved me about **1–2 hours** compared to figuring these improvements out alone.

---
