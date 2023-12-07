package br.com.unifalmg.blog.integration;

import br.com.unifalmg.blog.entity.User;
import br.com.unifalmg.blog.exception.UserNoFoundException;
import br.com.unifalmg.blog.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class UserServiceIntegrationTest {

    @Autowired
    private UserService service;

    @Test
    @DisplayName("findById > When the id is null > Throw an exception")
    void findByIdWhenTheIdIsNullThrowAnException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.findById(null));
    }

    @Test
    @DisplayName("findById > When the id is not null > When a user is found > Return the user")
    void findByIdWhenTheIsNotNUllReturnTheUser() {
        User user = service.findById(6);
        Assertions.assertAll(
                () ->Assertions.assertEquals(6, user.getId()),
                () -> Assertions.assertEquals("Mrs. Dennis Schulist", user.getName())
        );
    }

    @Test
    @DisplayName("#findById > When the id is not null > When no user is found > Throw an exception")
    void findByIdWhenTheIdIsNotNullAnIsNotFoundThrowAnException() {
        Assertions.assertThrows(UserNoFoundException.class, () -> service.findById(11));
    }

}
