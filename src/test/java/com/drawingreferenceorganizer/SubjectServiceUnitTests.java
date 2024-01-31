package com.drawingreferenceorganizer;

import com.drawingreferenceorganizer.models.Subject;
import com.drawingreferenceorganizer.repositories.SubjectRepository;
import com.drawingreferenceorganizer.services.SubjectService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class SubjectServiceUnitTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private SubjectService subjectService;

    @MockBean
    private SubjectRepository subjectRepository;

    @Test
    @WithMockUser
    public void getSubjectFlow() throws Exception {
        Subject mockedSubject = new Subject();
        mockedSubject.setId(1L);
        mockedSubject.setTitle("Mock");
        Mockito.when(subjectRepository.findById(1L)).thenReturn(Optional.of(mockedSubject));

        mvc.perform(get("/api/subjects/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Mock")))
                .andExpect(jsonPath("$.references", is(empty())));

        Mockito.verify(subjectRepository).findById(1L);
    }
}
