package com.gometro.config;

import com.gometro.entity.User;
import com.gometro.entity.UserRole;
import com.gometro.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DemoDataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;

    public DemoDataSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private User createUser(String firstName, String lastName, String email, String phone, UserRole role) {
        User u = new User();
        u.setFirstName(firstName);
        u.setLastName(lastName);
        u.setEmail(email);
        u.setPassword("Password@123");
        u.setPhoneNumber(phone);
        u.setRole(role);
        u.setActive(true);
        return userRepository.save(u);
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() > 0) {
            return;
        }

        createUser("Aarav", "Sharma", "aarav.sharma@example.com", "+919820011223", UserRole.ROLE_PASSENGER);
        createUser("Priya", "Patel", "priya.patel@example.com", "+919820022334", UserRole.ROLE_PASSENGER);
        createUser("Rohan", "Verma", "rohan.verma@example.com", "+919820033445", UserRole.ROLE_PASSENGER);
        createUser("Sneha", "Kulkarni", "sneha.kulkarni@example.com", "+919820044556", UserRole.ROLE_ADMIN);
        createUser("Aditya", "Mehta", "aditya.mehta@example.com", "+919820055667", UserRole.ROLE_OPERATOR);
    }
}
