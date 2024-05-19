package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class TestControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private UserDetailsService userDetailsService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testAllAccess() throws Exception {
        mockMvc.perform(get("/api/test/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Public Content."));
    }

    @Test
    @WithMockUser(roles = {"USER"})
    public void testUserAccessWithUserRole() throws Exception {
        mockMvc.perform(get("/api/test/user")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("User Content."));
    }

    @Test
    @WithMockUser(roles = {"MODERATOR"})
    public void testUserAccessWithModeratorRole() throws Exception {
        mockMvc.perform(get("/api/test/user")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("User Content."));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void testUserAccessWithAdminRole() throws Exception {
        mockMvc.perform(get("/api/test/user")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("User Content."));
    }

    @Test
    @WithMockUser(roles = {"MODERATOR"})
    public void testModeratorAccess() throws Exception {
        mockMvc.perform(get("/api/test/mod")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Moderator Board."));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void testAdminAccess() throws Exception {
        mockMvc.perform(get("/api/test/admin")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Admin Board."));
    }

    @Test
    @WithMockUser(roles = {"USER"})
    public void testModeratorAccessDeniedForUserRole() throws Exception {
        mockMvc.perform(get("/api/test/mod")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"USER"})
    public void testAdminAccessDeniedForUserRole() throws Exception {
        mockMvc.perform(get("/api/test/admin")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"MODERATOR"})
    public void testAdminAccessDeniedForModeratorRole() throws Exception {
        mockMvc.perform(get("/api/test/admin")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }
}
