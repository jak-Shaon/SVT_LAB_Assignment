package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

class UserManagerTest {

    @Mock
    private UserService userService; // Mock the UserService

    private UserManager userManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userManager = new UserManager(userService);
    }

    @Test
    void testRegisterUser_Success() {
        // Arrange
        String username = "validUser";
        String password = "password123";
        when(userService.usernameExists(username)).thenReturn(false); // Username does not exist
        when(userService.saveUser(username, password)).thenReturn(true); // User saving successful

        // Act
        boolean result = userManager.registerUser(username, password);

        // Assert
        assertTrue(result, "registration successful");
        verify(userService, times(1)).usernameExists(username);
        verify(userService, times(1)).saveUser(username, password);
        verifyNoMoreInteractions(userService); // Ensure no other methods were called
    }

    @Test
    void testRegisterUser_Failure_UserExists() {
        // Arrange
        String username = "existingUser";
        String password = "password123";
        when(userService.usernameExists(username)).thenReturn(true); // Username already exists

        // Act
        boolean result = userManager.registerUser(username, password);

        // Assert
        assertFalse(result, "registration fail if username already exists");
        verify(userService, times(1)).usernameExists(username);
        verify(userService, never()).saveUser(anyString(), anyString());
    }

    @Test
    void testRegisterUser_Failure_SaveUserFails() {
        // Arrange
        String username = "newUser";
        String password = "password123";
        when(userService.usernameExists(username)).thenReturn(false);
        when(userService.saveUser(username, password)).thenReturn(false);

        // Act
        boolean result = userManager.registerUser(username, password);


        assertFalse(result, "User registration should fail if saving the user fails");
        verify(userService, times(1)).usernameExists(username);
        verify(userService, times(1)).saveUser(username, password);
    }
}
