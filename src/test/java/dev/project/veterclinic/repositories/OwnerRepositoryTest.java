package dev.project.veterclinic.repositories;

import dev.project.veterclinic.models.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class OwnerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OwnerRepository ownerRepository;

    private Owner owner;

    @BeforeEach
    void setUp() {
        owner = new Owner();
        owner.setFirstName("John");
        owner.setLastName("Doe");
        owner.setDni("1234x678");
        owner.setPhoneNumber("987654321");
        entityManager.persistAndFlush(owner);
    }

    @Test
    void testExistByDni() {
        boolean exists = ownerRepository.existByDni("1234x678");
        assertTrue(exists, "Owner should exist with DNI 12345678");

        boolean notExists = ownerRepository.existByDni("87654321");
        assertFalse(notExists, "Owner should not exist with DNI 87654321");
    }

    @Test
    void testFindByDni() {
        int count = ownerRepository.findByDni("1234x678");
        assertEquals(1, count, "There should be one owner with DNI 12345678");

        int noCount = ownerRepository.findByDni("876x4321");
        assertEquals(0, noCount, "There should be no owners with DNI 87654321");
    }
}
