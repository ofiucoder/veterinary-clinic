package dev.project.veterclinic.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PetTypeTest {

    @Test
    void testGetValue() {
        assertEquals("Cat", PetType.CAT.getValue());
        assertEquals("Dog", PetType.DOG.getValue());
    }

    @Test
    void testValueOf() {
        assertEquals(PetType.CAT, PetType.valueOf("CAT"));
        assertEquals(PetType.DOG, PetType.valueOf("DOG"));
    }

    @Test
    void testValues() {
        PetType[] petTypes = PetType.values();
        assertEquals(2, petTypes.length);
        assertEquals(PetType.CAT, petTypes[0]);
        assertEquals(PetType.DOG, petTypes[1]);
    }
}
