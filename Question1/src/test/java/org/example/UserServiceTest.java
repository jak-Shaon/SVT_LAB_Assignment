package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;


    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class); // Mock the UserRepository
        userService = new UserService(userRepository); // Inject the mock into UserService
    }

    @Test
    void testFindUserById() {
        // Arrange
        int userId = 1;
        User mockUser = new User(userId, "John Doe");
        when(userRepository.findById(userId)).thenReturn(mockUser); // Mock behavior

        // Act
        User result = userService.findUserById(userId);

        // Assert
        assertNotNull(result, "User should not be null");
        assertEquals(userId, result.getId(), "User ID should match");
        assertEquals("John Doe", result.getName(), "User name should match");

        // Verify interactions
        verify(userRepository, times(1)).findById(userId);
        verifyNoMoreInteractions(userRepository);
    }
}
