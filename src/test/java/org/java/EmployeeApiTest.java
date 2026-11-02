package org.java;

import org.java.spring.JavaLearningApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * TESTING IN SPRING BOOT
 *
 * @SpringBootTest      : loads the full context (integration test).
 * @AutoConfigureMockMvc: call controllers without starting a real server.
 * Slice tests (faster): @WebMvcTest (controller only, @MockBean services),
 *                       @DataJpaTest (repository + in-memory DB).
 * Unit tests: JUnit 5 + Mockito (@Mock, @InjectMocks, when(...).thenReturn(...), verify(...)).
 */
@SpringBootTest(classes = JavaLearningApplication.class)
@AutoConfigureMockMvc
class EmployeeApiTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void listReturnsSeededEmployees() throws Exception {
        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Asha Rao"));
    }

    @Test
    void invalidRequestReturns400() throws Exception {
        mockMvc.perform(post("/api/employees").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"\",\"email\":\"bad\",\"salary\":-1,\"department\":\"\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void unknownIdReturns404() throws Exception {
        mockMvc.perform(get("/api/employees/9999")).andExpect(status().isNotFound());
    }
}
