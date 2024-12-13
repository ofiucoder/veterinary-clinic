package dev.project.veterclinic.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppointmentStatusTest {

    @Test
    void testFromValue() {
        assertEquals(AppointmentStatus.PENDING, AppointmentStatus.fromValue("pending"));
        assertEquals(AppointmentStatus.PASSED, AppointmentStatus.fromValue("passed"));
        assertThrows(IllegalArgumentException.class, () -> AppointmentStatus.fromValue("nonexistent"));
    }

    @Test
    void testGetValue() {
        assertEquals("pending", AppointmentStatus.PENDING.getValue());
        assertEquals("passed", AppointmentStatus.PASSED.getValue());
    }

    @Test
    void testValueOf() {
        assertEquals(AppointmentStatus.PENDING, AppointmentStatus.valueOf("PENDING"));
        assertEquals(AppointmentStatus.PASSED, AppointmentStatus.valueOf("PASSED"));
    }

    @Test
    void testValues() {
        AppointmentStatus[] appointmentStatuses = AppointmentStatus.values();
        assertEquals(2, appointmentStatuses.length);
        assertEquals(AppointmentStatus.PENDING, appointmentStatuses[0]);
        assertEquals(AppointmentStatus.PASSED, appointmentStatuses[1]);
    }
}
