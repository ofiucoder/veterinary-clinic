package dev.project.veterclinic.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class TreatmentTest {

    @Test
    void testDate() {
        Treatment treatment = new Treatment();
        LocalDate date = LocalDate.of(2023, 1, 1);
        treatment.setDate(date);
        assertEquals(date, treatment.getDate());
    }

    @Test
    void testNote() {
        Treatment treatment = new Treatment();
        String note = "Test note";
        treatment.setNote(note);
        assertEquals(note, treatment.getNote());
    }

    @Test
    void testType() {
        Treatment treatment = new Treatment();
        Treatment.TreatmentType type = Treatment.TreatmentType.SURGERY;
        treatment.setType(type);
        assertEquals(type, treatment.getType());
    }

    @Test
    void testId() {
        Treatment treatment = new Treatment();
        treatment.setId(1);
        assertEquals(1, treatment.getId());
    }
}
