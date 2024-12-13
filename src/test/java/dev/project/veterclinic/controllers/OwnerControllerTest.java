package dev.project.veterclinic.controllers;

import dev.project.veterclinic.models.Owner;
import dev.project.veterclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class OwnerControllerTest {

    @Mock
    private OwnerService ownerService;

    @InjectMocks
    private OwnerController ownerController;

    private List<Owner> mockOwnerList;

    @BeforeEach
    void setUp() {
        mockOwnerList = List.of(
                new Owner("John", "Doe", "12345678", "987654321"),
                new Owner("Jane", "Smith", "87654321", "123456789"));
    }

    @Test
    void testIndex() {
        // Arrange: Mock the service call
        when(ownerService.findAll()).thenReturn(mockOwnerList);

        // Act: Call the index method
        List<Owner> response = ownerController.index(); // Expecting List<Owner>

        // Assert: Check the response
        assertNotNull(response);
        assertEquals(2, response.size()); // Ensure there are 2 owners in the list
        assertEquals("John", response.get(0).getFirstName()); // Check the first owner's first name
        assertEquals("Jane", response.get(1).getFirstName()); // Check the second owner's first name

        // Verify that the findAll method was called once
        verify(ownerService, times(1)).findAll();
    }
}