import org.junit.jupiter.api.Test;

import java.io.Serializable;

import static org.junit.jupiter.api.Assertions.*;

class DateInfoTest {

    private DateInfo date = new DateInfo(2024,1);
    @Test
    void getStartMonth() {
        assertEquals(1,date.getStartMonth());
    }

    @Test
    void getEndMonth() {
        assertEquals(12,date.getEndMonth());
    }

    @Test
    void getStartYear() {
        assertEquals(2024,date.getStartYear());
    }

    @Test
    void testToString() {
        String outcome = "1/2024 > 12/2024";
        assertEquals(outcome,date.toString());
    }
}