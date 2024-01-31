package com.drawingreferenceorganizer;

import com.drawingreferenceorganizer.models.Reference;
import com.drawingreferenceorganizer.repositories.ReferenceRepository;
import com.drawingreferenceorganizer.services.ReferenceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class ReferenceServiceUnitTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ReferenceService referenceService;

    @MockBean
    private ReferenceRepository referenceRepository;

    @Test
    @WithMockUser
    public void getReferenceFlow() throws Exception {
        Reference mockedReference = new Reference();
        mockedReference.setId(1L);
        mockedReference.setDescription("mock");
        mockedReference.setUrl("https://mockTest.com");
        Mockito.when(referenceRepository.findById(1L)).thenReturn(Optional.of(mockedReference));

        mvc.perform(get("/api/references/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.description", is("mock")))
                .andExpect(jsonPath("$.url", is("https://mockTest.com")));

        Mockito.verify(referenceRepository).findById(1L);
    }
}
