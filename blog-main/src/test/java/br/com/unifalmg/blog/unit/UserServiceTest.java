package br.com.unifalmg.blog.unit;

import br.com.unifalmg.blog.entity.User;
import br.com.unifalmg.blog.exception.UserNoFoundException;
import br.com.unifalmg.blog.repository.UserRepository;
import br.com.unifalmg.blog.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.AbstractSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository reposity;

    @Test
    @DisplayName("#findById > When the id is null > Throw an exception")
    void findByIdWhenTheIdIsNullThrowAnException(){
        assertThrows(IllegalArgumentException.class, () ->
                service.findById(null));
    }

    @Test
    @DisplayName("#findById > When the id is not null > When a user is found > Return the user")
    void findByIdUserIsfoundReturnUser(){
        when(reposity.findById(1)).thenReturn(Optional.of(User.builder() //Mockito
                .id(1)
                .name("Felipe")
                .username("feliperey")
                .build()));
        User response = service.findById(1);
        assertAll( //Assertions
                () -> assertEquals(1, response.getId()),
                () -> assertEquals("Felipe", response.getName()),
                () -> assertEquals("feliperey", response.getUsername())
        );
    }

    @Test
    @DisplayName("#findById > When the id is not null > When no user is found > Throw an exception")
    void findByIdWhenTheIdISNotNullAndUserNotFoundRThrowAnException(){
        when(reposity.findById(2)).thenReturn(Optional.empty());
        assertThrows(UserNoFoundException.class, () -> service.findById(2));
    }



    //TODO: Implement test cases For getAllUser
}
