package dev.project.veterclinic.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppointmentTypeTest {

    @Test
    void testFromValue() {
        assertEquals(AppointmentType.ORDINARY, AppointmentType.fromValue("ordinary"));
        assertEquals(AppointmentType.URGENT, AppointmentType.fromValue("urgent"));
        assertThrows(IllegalArgumentException.class, () -> AppointmentType.fromValue("nonexistent"));
    }

    @Test
    void testGetValue() {
        assertEquals("ordinary", AppointmentType.ORDINARY.getValue());
        assertEquals("urgent", AppointmentType.URGENT.getValue());
    }

    @Test
    void testValueOf() {
        assertEquals(AppointmentType.ORDINARY, AppointmentType.valueOf("ORDINARY"));
        assertEquals(AppointmentType.URGENT, AppointmentType.valueOf("URGENT"));
    }

    @Test
    void testValues() {
        AppointmentType[] appointmentTypes = AppointmentType.values();
        assertEquals(2, appointmentTypes.length);
        assertEquals(AppointmentType.ORDINARY, appointmentTypes[0]);
        assertEquals(AppointmentType.URGENT, appointmentTypes[1]);
    }
}
