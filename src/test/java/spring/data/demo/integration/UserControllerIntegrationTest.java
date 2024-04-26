package spring.data.demo.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import spring.data.demo.controller.UserController;
import spring.data.demo.entity.User;
import spring.data.demo.service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(UserController.class)
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void addUser_ValidUser_ReturnsCreated() throws Exception {
        User validUser = User.builder()
                .firstName("John")
                .lastName("Doe")
                .username("johndoe")
                .email("johndoe@example.com")
                .password("password123")
                .build();

        when(userService.addUser(any(User.class))).thenReturn(validUser);

        var res = mockMvc.perform(MockMvcRequestBuilders.post("/user/create")
                        .content(asJsonString(validUser))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(res.getStatus(), 200);
    }

    @Test
    void getUserByUsername_ValidUsername_ReturnsUser() throws Exception {
        User user = User.builder()
                .firstName("John")
                .lastName("Doe")
                .username("johndoe")
                .email("johndoe@example.com")
                .password("password123")
                .build();

        when(userService.getUserByUsername("johndoe")).thenReturn(user);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/username/johndoe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        User returnedUser = new ObjectMapper().readValue(content, User.class);

        assertEquals(result.getResponse().getStatus(), 200);
        assertEquals(user, returnedUser);
    }

    @Test
    void getUserByEmail_ValidEmail_ReturnsUser() throws Exception {
        User user = User.builder()
                .firstName("John")
                .lastName("Doe")
                .username("johndoe")
                .email("johndoe@example.com")
                .password("password123")
                .build();

        when(userService.getUserByEmail("johndoe@example.com")).thenReturn(user);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/email/johndoe@example.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        User returnedUser = new ObjectMapper().readValue(content, User.class);

        assertEquals(result.getResponse().getStatus(), 200);
        assertEquals(user, returnedUser);
    }

    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
