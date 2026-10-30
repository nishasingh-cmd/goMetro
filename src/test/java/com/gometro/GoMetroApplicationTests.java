package com.gometro;

import com.gometro.entity.Role;
import com.gometro.entity.User;
import com.gometro.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.matchesPattern;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GoMetroApplicationTests {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private MockMvc mockMvc;

        // Helper method that replicates the create logic from the controller
        // for tests that need to create users programmatically.
        private User createUserDirectly(User user) {
                if (user.getRole() == null) {
                        throw new IllegalArgumentException("Role is required");
                }
                String prefix = switch (user.getRole()) {
                        case PASSENGER -> "PASS";
                        case ADMIN -> "ADMIN";
                        case TICKET_AGENT, MAINTENANCE_STAFF -> "EMP";
                };
                user.setUserId(prefix + "-" + UUID.randomUUID().toString());
                if (user.getIsActive() == null) {
                        user.setIsActive(true);
                }
                return userRepository.save(user);
        }

        @Test
        void contextLoads() {
        }

        @Test
        void testCreateUserPassengerGeneratesPassIdAndRetrieve() {
                User user = new User();
                user.setPassword("Password@123");
                user.setFirstName("Passenger");
                user.setLastName("User");
                user.setContact("+919111111111");
                user.setRole(Role.PASSENGER);

                User created = createUserDirectly(user);
                assertNotNull(created);
                assertNotNull(created.getUserId());
                assertTrue(created.getUserId().matches("^PASS-[0-9a-fA-F-]{36}$"));

                User fetched = userRepository.findById(created.getUserId())
                        .orElseThrow(() -> new RuntimeException("User not found"));
                assertNotNull(fetched);
                assertEquals(created.getUserId(), fetched.getUserId());
                assertEquals("+919111111111", fetched.getContact());
        }

        @Test
        void testCreateUserWithoutRoleThrowsException() {
                User user = new User();
                user.setPassword("Password@123");
                user.setFirstName("NoRole");
                user.setLastName("User");
                user.setContact("+919222222221");

                assertThrows(IllegalArgumentException.class, () -> createUserDirectly(user));
        }

        @Test
        void testCreateUserIgnoresSuppliedIdInService() {
                User user = new User();
                user.setUserId("CLIENT-PASS-999");
                user.setPassword("Password@123");
                user.setFirstName("Ignore");
                user.setLastName("Id");
                user.setContact("+919333333333");
                user.setRole(Role.PASSENGER);

                User created = createUserDirectly(user);
                assertNotNull(created);
                assertNotNull(created.getUserId());
                assertTrue(created.getUserId().matches("^PASS-[0-9a-fA-F-]{36}$"));
                assertFalse("CLIENT-PASS-999".equals(created.getUserId()));
        }

        @Test
        void testGetUsersUnauthorizedWithoutCredentials() throws Exception {
                mockMvc.perform(get("/api/users"))
                                .andExpect(status().isUnauthorized());
        }

        @Test
        void testGetUsersAuthorizedWithAdminCredentials() throws Exception {
                mockMvc.perform(get("/api/users").with(httpBasic("admin", "admin123")))
                                .andExpect(status().isOk());
        }

        @Test
        void testSwaggerDocumentationPermitAll() throws Exception {
                mockMvc.perform(get("/v3/api-docs"))
                                .andExpect(status().isOk());
        }

        @Test
        void testCreateUserPassengerGeneratesPassPrefixViaApi() throws Exception {
                String jsonPayload = """
                                {
                                  "firstName": "Passenger",
                                  "lastName": "One",
                                  "password": "Password@123",
                                  "role": "PASSENGER",
                                  "contact": "+919876540001"
                                }
                                """;

                mockMvc.perform(post("/api/users")
                                .with(httpBasic("admin", "admin123"))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonPayload))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.userId").value(matchesPattern("^PASS-[0-9a-fA-F-]{36}$")))
                                .andExpect(jsonPath("$.role").value("PASSENGER"))
                                .andExpect(jsonPath("$.contact").value("+919876540001"))
                                .andExpect(jsonPath("$.isActive").value(true))
                                .andExpect(jsonPath("$.password").doesNotExist())
                                .andExpect(jsonPath("$.id").doesNotExist())
                                .andExpect(jsonPath("$.active").doesNotExist());
        }

        @Test
        void testCreateUserAdminGeneratesAdminPrefixViaApi() throws Exception {
                String jsonPayload = """
                                {
                                  "firstName": "Admin",
                                  "lastName": "One",
                                  "password": "Password@123",
                                  "role": "ADMIN",
                                  "contact": "+919876540002"
                                }
                                """;

                mockMvc.perform(post("/api/users")
                                .with(httpBasic("admin", "admin123"))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonPayload))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.userId").value(matchesPattern("^ADMIN-[0-9a-fA-F-]{36}$")))
                                .andExpect(jsonPath("$.role").value("ADMIN"))
                                .andExpect(jsonPath("$.contact").value("+919876540002"))
                                .andExpect(jsonPath("$.password").doesNotExist())
                                .andExpect(jsonPath("$.id").doesNotExist())
                                .andExpect(jsonPath("$.active").doesNotExist());
        }

        @Test
        void testCreateUserTicketAgentGeneratesEmpPrefixViaApi() throws Exception {
                String jsonPayload = """
                                {
                                  "firstName": "Ticket",
                                  "lastName": "Agent",
                                  "password": "Password@123",
                                  "role": "TICKET_AGENT",
                                  "contact": "+919876540003"
                                }
                                """;

                mockMvc.perform(post("/api/users")
                                .with(httpBasic("admin", "admin123"))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonPayload))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.userId").value(matchesPattern("^EMP-[0-9a-fA-F-]{36}$")))
                                .andExpect(jsonPath("$.role").value("TICKET_AGENT"))
                                .andExpect(jsonPath("$.contact").value("+919876540003"))
                                .andExpect(jsonPath("$.password").doesNotExist())
                                .andExpect(jsonPath("$.id").doesNotExist())
                                .andExpect(jsonPath("$.active").doesNotExist());
        }

        @Test
        void testCreateUserMaintenanceStaffGeneratesEmpPrefixViaApi() throws Exception {
                String jsonPayload = """
                                {
                                  "firstName": "Maintenance",
                                  "lastName": "Staff",
                                  "password": "Password@123",
                                  "role": "MAINTENANCE_STAFF",
                                  "contact": "+919876540004"
                                }
                                """;

                mockMvc.perform(post("/api/users")
                                .with(httpBasic("admin", "admin123"))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonPayload))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.userId").value(matchesPattern("^EMP-[0-9a-fA-F-]{36}$")))
                                .andExpect(jsonPath("$.role").value("MAINTENANCE_STAFF"))
                                .andExpect(jsonPath("$.contact").value("+919876540004"))
                                .andExpect(jsonPath("$.password").doesNotExist())
                                .andExpect(jsonPath("$.id").doesNotExist())
                                .andExpect(jsonPath("$.active").doesNotExist());
        }

        @Test
        void testCreateUserIgnoresClientSuppliedUserIdViaApi() throws Exception {
                String jsonPayload = """
                                {
                                  "userId": "CLIENT-SUPPLIED-12345",
                                  "firstName": "Client",
                                  "lastName": "Attempt",
                                  "password": "Password@123",
                                  "role": "PASSENGER",
                                  "contact": "+919876540005"
                                }
                                """;

                mockMvc.perform(post("/api/users")
                                .with(httpBasic("admin", "admin123"))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonPayload))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.userId").value(matchesPattern("^PASS-[0-9a-fA-F-]{36}$")))
                                .andExpect(jsonPath("$.userId").value(not("CLIENT-SUPPLIED-12345")));
        }

        @Test
        void testCreateUserWithIsActiveFalsePreservedViaApi() throws Exception {
                String jsonPayload = """
                                {
                                  "firstName": "Inactive",
                                  "lastName": "User",
                                  "password": "Password@123",
                                  "role": "PASSENGER",
                                  "contact": "+919876540006",
                                  "isActive": false
                                }
                                """;

                mockMvc.perform(post("/api/users")
                                .with(httpBasic("admin", "admin123"))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonPayload))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.userId").value(matchesPattern("^PASS-[0-9a-fA-F-]{36}$")))
                                .andExpect(jsonPath("$.isActive").value(false));
        }

        @Test
        void testCreateUserWithDefaultIsActiveWhenOmittedViaApi() throws Exception {
                String jsonPayload = """
                                {
                                  "firstName": "Default",
                                  "lastName": "Active",
                                  "password": "Password@123",
                                  "role": "PASSENGER",
                                  "contact": "+919876540007"
                                }
                                """;

                mockMvc.perform(post("/api/users")
                                .with(httpBasic("admin", "admin123"))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonPayload))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.userId").value(matchesPattern("^PASS-[0-9a-fA-F-]{36}$")))
                                .andExpect(jsonPath("$.isActive").value(true));
        }

        @Test
        void testUpdateUserPreservesDatabaseValuesAndDoesNotRegenerateIdViaApi() throws Exception {
                User initialUser = new User();
                initialUser.setFirstName("Update");
                initialUser.setLastName("Target");
                initialUser.setPassword("Password@123");
                initialUser.setRole(Role.TICKET_AGENT);
                initialUser.setContact("+919876540008");
                initialUser.setIsActive(false);
                User created = createUserDirectly(initialUser);
                String originalId = created.getUserId();
                assertNotNull(originalId);
                assertTrue(originalId.startsWith("EMP-"));

                String partialUpdatePayload = """
                                {
                                  "firstName": "UpdatedName"
                                }
                                """;

                mockMvc.perform(put("/api/users/" + originalId)
                                .with(httpBasic("admin", "admin123"))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(partialUpdatePayload))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.userId").value(originalId))
                                .andExpect(jsonPath("$.firstName").value("UpdatedName"))
                                .andExpect(jsonPath("$.isActive").value(false))
                                .andExpect(jsonPath("$.role").value("TICKET_AGENT"))
                                .andExpect(jsonPath("$.id").doesNotExist())
                                .andExpect(jsonPath("$.active").doesNotExist());

                User afterPartial = userRepository.findById(originalId)
                        .orElseThrow(() -> new RuntimeException("User not found"));
                assertNotNull(afterPartial);
                assertEquals(originalId, afterPartial.getUserId());
                assertFalse(afterPartial.getIsActive());

                String statusUpdatePayload = """
                                {
                                  "isActive": true,
                                  "role": "ADMIN"
                                }
                                """;

                mockMvc.perform(put("/api/users/" + originalId)
                                .with(httpBasic("admin", "admin123"))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(statusUpdatePayload))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.userId").value(originalId))
                                .andExpect(jsonPath("$.isActive").value(true))
                                .andExpect(jsonPath("$.role").value("ADMIN"))
                                .andExpect(jsonPath("$.id").doesNotExist())
                                .andExpect(jsonPath("$.active").doesNotExist());

                User persisted = userRepository.findById(originalId)
                        .orElseThrow(() -> new RuntimeException("User not found"));
                assertNotNull(persisted);
                assertEquals(originalId, persisted.getUserId());
                assertTrue(persisted.getIsActive());
                assertEquals(Role.ADMIN, persisted.getRole());
        }

        @Test
        void testCreateUserWithLegacyPropertiesIgnored() throws Exception {
                String jsonPayload = """
                                {
                                  "userId": "IGNORED-ID",
                                  "id": "string",
                                  "firstName": "Legacy",
                                  "lastName": "Test",
                                  "password": "Password@123",
                                  "role": "PASSENGER",
                                  "contact": "+919876540009",
                                  "isActive": false,
                                  "active": true
                                }
                                """;

                mockMvc.perform(post("/api/users")
                                .with(httpBasic("admin", "admin123"))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonPayload))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.userId").value(matchesPattern("^PASS-[0-9a-fA-F-]{36}$")))
                                .andExpect(jsonPath("$.isActive").value(false))
                                .andExpect(jsonPath("$.role").value("PASSENGER"))
                                .andExpect(jsonPath("$.id").doesNotExist())
                                .andExpect(jsonPath("$.active").doesNotExist());
        }

        @Test
        void testOpenApiSchemaUserIdIsReadOnlyAndNotRequired() throws Exception {
                mockMvc.perform(get("/v3/api-docs"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.components.schemas.User.properties.userId.readOnly").value(true))
                                .andExpect(jsonPath("$.components.schemas.User.required", not(hasItem("userId"))))
                                .andExpect(jsonPath("$.components.schemas.User.properties.id").doesNotExist())
                                .andExpect(jsonPath("$.components.schemas.User.properties.active").doesNotExist());
        }

        @Test
        void testPatchUser() throws Exception {
                User user = new User();
                user.setFirstName("PatchFirst");
                user.setLastName("PatchLast");
                user.setPassword("Secret@123");
                user.setRole(Role.PASSENGER);
                user.setContact("+919999900001");
                user.setIsActive(true);
                User created = createUserDirectly(user);
                String userId = created.getUserId();

                String patchPayload = """
                                {
                                  "contact": "+919999900002",
                                  "isActive": false
                                }
                                """;

                mockMvc.perform(patch("/api/users/" + userId)
                                .with(httpBasic("admin", "admin123"))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(patchPayload))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.userId").value(userId))
                                .andExpect(jsonPath("$.firstName").value("PatchFirst"))
                                .andExpect(jsonPath("$.contact").value("+919999900002"))
                                .andExpect(jsonPath("$.isActive").value(false));

                User updated = userRepository.findById(userId)
                        .orElseThrow(() -> new RuntimeException("User not found"));
                assertEquals("+919999900002", updated.getContact());
                assertFalse(updated.getIsActive());
                assertEquals("PatchFirst", updated.getFirstName());
        }
}
