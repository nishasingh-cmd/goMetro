package com.gometro.config;

import com.gometro.entity.Role;
import com.gometro.entity.User;
import com.gometro.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class DemoDataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;

    public DemoDataSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private User createUser(String idPrefix, String firstName, String lastName, String contact, Role role, String stationId, LocalDate hireDate) {
        User u = new User();
        u.setUserId(idPrefix + "-" + UUID.randomUUID().toString());
        u.setFirstName(firstName);
        u.setLastName(lastName);
        u.setPassword("Password@123");
        u.setContact(contact);
        u.setRole(role);
        u.setIsActive(true);
        u.setRegistrationDate(LocalDate.now());
        u.setStationId(stationId);
        u.setHireDate(hireDate);
        return userRepository.save(u);
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() > 0) {
            return;
        }

        createUser("PASS", "Aarav", "Sharma", "+919820011223", Role.PASSENGER, null, null);
        createUser("PASS", "Priya", "Patel", "+919820022334", Role.PASSENGER, null, null);
        createUser("PASS", "Rohan", "Verma", "+919820033445", Role.PASSENGER, null, null);
        createUser("ADMIN", "Sneha", "Kulkarni", "+919820044556", Role.ADMIN, null, LocalDate.now().minusYears(2));
        createUser("EMP", "Aditya", "Mehta", "+919820055667", Role.TICKET_AGENT, "STN-001", LocalDate.now().minusYears(1));
    }
}
