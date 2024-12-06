package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginManagerTest {

    @Mock
    private AuthenticationService authenticationService;

    private LoginManager loginManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        loginManager = new LoginManager(authenticationService);
    }

    @Test
    void testLogin_ValidCredentials() throws Exception {

        String username = "validUser";
        String password = "validPassword";
        when(authenticationService.authenticate(username, password)).thenReturn(true);

        boolean result = loginManager.login(username, password);

        assertTrue(result, "Login succeed");
        verify(authenticationService, times(1)).authenticate(username, password);
    }

    @Test
    void testLogin_InvalidCredentials() throws Exception {

        String username = "invalidUser";
        String password = "wrongPassword";
        when(authenticationService.authenticate(username, password)).thenReturn(false);

        boolean result = loginManager.login(username, password);

        assertFalse(result, "Login fail");
        verify(authenticationService, times(1)).authenticate(username, password);
    }

    @Test
    void testLogin_NullUsername() {

        String username = null;
        String password = "somePassword";

        Exception exception = assertThrows(Exception.class, () -> {
            loginManager.login(username, password);
        }, "Exception for null username");
        assertEquals("Username & Password can not be null", exception.getMessage());
        verify(authenticationService, never()).authenticate(anyString(), anyString());
    }

    @Test
    void testLogin_NullPassword() {

        String username = "someUser";
        String password = null;

        Exception exception = assertThrows(Exception.class, () -> {

            loginManager.login(username, password);
        }, "Exception for null password");
        assertEquals("Username & Password can not be null", exception.getMessage());
        verify(authenticationService, never()).authenticate(anyString(), anyString());
    }

    @Test
    void testLogin_NullUsernameAndPassword() {
        String username = null;
        String password = null;

        Exception exception = assertThrows(Exception.class, () -> {
            loginManager.login(username, password);
        }, "Exception for null username and password");
        assertEquals("Username & Password can not be null", exception.getMessage());
        verify(authenticationService, never()).authenticate(anyString(), anyString());
    }
}
