package com.gometro;

import com.gometro.entity.User;
import com.gometro.entity.UserRole;
import com.gometro.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class GoMetroApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
    }

    @Test
    void testCreateUserWithCustomIdAndRetrieve() {
        User user = new User();
        user.setId(111L);
        user.setEmail("custom111@example.com");
        user.setPassword("Password@123");
        user.setFirstName("Custom");
        user.setLastName("User");
        user.setPhoneNumber("+919111111111");
        user.setRole(UserRole.ROLE_PASSENGER);

        User created = userService.create(user);
        assertNotNull(created);
        assertEquals(111L, created.getId());

        User fetched = userService.getById(111L);
        assertNotNull(fetched);
        assertEquals(111L, fetched.getId());
        assertEquals("custom111@example.com", fetched.getEmail());
    }

    @Test
    void testCreateUserWithDuplicateIdThrowsException() {
        User user1 = new User();
        user1.setId(222L);
        user1.setEmail("first222@example.com");
        user1.setPassword("Password@123");
        user1.setFirstName("First");
        user1.setLastName("User");
        user1.setPhoneNumber("+919222222221");
        user1.setRole(UserRole.ROLE_PASSENGER);
        userService.create(user1);

        User user2 = new User();
        user2.setId(222L);
        user2.setEmail("second222@example.com");
        user2.setPassword("Password@123");
        user2.setFirstName("Second");
        user2.setLastName("User");
        user2.setPhoneNumber("+919222222222");
        user2.setRole(UserRole.ROLE_PASSENGER);

        assertThrows(IllegalArgumentException.class, () -> userService.create(user2));
    }

    @Test
    void testCreateUserWithoutIdGeneratesId() {
        User user = new User();
        user.setEmail("autogen@example.com");
        user.setPassword("Password@123");
        user.setFirstName("Auto");
        user.setLastName("Gen");
        user.setPhoneNumber("+919333333333");
        user.setRole(UserRole.ROLE_PASSENGER);

        User created = userService.create(user);
        assertNotNull(created);
        assertNotNull(created.getId());
    }
}
