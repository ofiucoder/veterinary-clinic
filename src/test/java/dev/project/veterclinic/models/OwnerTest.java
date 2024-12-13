package dev.project.veterclinic.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OwnerTest {

    private Owner owner;

    @BeforeEach
    void setUp() {
        owner = new Owner("Carlos", "López", "12345678A", "654321987");
    }

    @Test
    void testFirstName() {
        owner.setFirstName("Ana");
        assertEquals("Ana", owner.getFirstName());
    }

    @Test
    void testLastName() {
        owner.setLastName("González");
        assertEquals("González", owner.getLastName());
    }

    @Test
    void testDni() {
        owner.setDni("87654321B");
        assertEquals("87654321B", owner.getDni());
    }

    @Test
    void testPhoneNumber() {
        owner.setPhoneNumber("678123456");
        assertEquals("678123456", owner.getPhoneNumber());
    }
}
