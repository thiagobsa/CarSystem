package com.example.carsystem.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.carsystem.dto.UserRequest;
import com.example.carsystem.dto.UserResponse;
import com.example.carsystem.dto.UserResponseCreate;
import com.example.carsystem.service.UserService;

public class UserControllerTest {

	@Mock
    private UserService userService;

    @InjectMocks
    private UserControllerImpl userController;

    private UserResponseCreate testUserResponse;
    
    private UserRequest userRequest;

    @BeforeEach
    public void setUp() {
        
        testUserResponse = new UserResponseCreate();
        testUserResponse.setId(1L);
        
        testUserResponse.setUsername("TestUser");
    }

    @SuppressWarnings("unchecked")
	@Test
    public void testCreateUser_Success() {
    	when(((OngoingStubbing) userService.create(userRequest)).thenReturn(null));


        UserRequest request = new UserRequest();
        ResponseEntity<UserResponseCreate> response = userController.create(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(testUserResponse, response.getBody());
    }

    @Test
    public void testFindAllPaged_Success() {
        Page<UserResponse> page = mock(Page.class);
        when(userService.findAllPaged(any())).thenReturn(page);

        Pageable pageable = mock(Pageable.class);
        ResponseEntity<Page<UserResponse>> response = userController.findAllPaged(pageable);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(page, response.getBody());
    }

    @Test
    public void testFindById_Success() {
        UserResponse userResponse = mock(UserResponse.class);
        when(userService.findById(1L)).thenReturn(userResponse);

        ResponseEntity<UserResponse> response = userController.findById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userResponse, response.getBody());
    }

    @Test
    public void testFindById_UserNotFound() {
        when(userService.findById(1L)).thenReturn(null);

        ResponseEntity<UserResponse> response = userController.findById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testUpdateUser_Success() {
        UserResponse updatedUserResponse = mock(UserResponse.class);
        when(userService.update(1L, any())).thenReturn(updatedUserResponse);

        UserRequest request = new UserRequest();
        ResponseEntity<UserResponse> response = userController.update(1L, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedUserResponse, response.getBody());
    }

    @Test
    public void testUpdateUser_UserNotFound() {
        when(userService.update(1L, any())).thenReturn(null);

        UserRequest request = new UserRequest();
        ResponseEntity<UserResponse> response = userController.update(1L, request);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteUser_Success() {
        ResponseEntity<Void> response = userController.delete(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userService).delete(1L);
    }


    @Test
    public void testDeleteAllUsers_Success() {
        ResponseEntity<Void> response = userController.deleteAll();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userService).deleteAll();
    }

    @Test
    public void testAuthenticateUser_Success() {
        
    }

    @Test
    public void testFindAuthenticateUser_Success() {
        
    }

    @Test
    public void testFindAuthenticateUser_InvalidToken() {
        
    }
}
