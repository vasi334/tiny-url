package controller;

import com.tiny_url.hashservice.controller.HashController;
import com.tiny_url.hashservice.service.HashRetrieveService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HashController.class)
public class HashControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HashRetrieveService hashRetrieveService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRetrieveOneHash() throws Exception {
        String expectedHash = "sampleHash";
        when(hashRetrieveService.retrieveOne()).thenReturn(expectedHash);

        mockMvc.perform(get("/hash/retrieve"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", is(expectedHash)));
    }

    @Test
    public void testMarkAsUsed() throws Exception {
        doNothing().when(hashRetrieveService).markHashAsUsed(anyString());

        mockMvc.perform(get("/hash/sampleHash"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response", is(200)));
    }

    @Test
    public void testMarkAsUnused() throws Exception {
        doNothing().when(hashRetrieveService).markHashAsUnused(anyString());

        mockMvc.perform(get("/hash/unused/sampleHash"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response", is(200)));
    }

    @Test
    public void testServiceTest() throws Exception {
        mockMvc.perform(get("/hash/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("hash service")));
    }
}
