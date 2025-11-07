package com.innospace.platform.core.entities.unit.test;


import com.innospace.platform.studentprojects.application.internal.commandservices.ProjectCommandServiceImpl;
import com.innospace.platform.studentprojects.domain.model.aggregates.Project;
import com.innospace.platform.studentprojects.domain.model.commands.CreateProjectCommand;
import com.innospace.platform.studentprojects.domain.model.valueobjects.ProjectStatus;
import com.innospace.platform.studentprojects.infrastructure.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class ProjectCommandServiceImplTest {


    @Mock
    private ProjectRepository projectRepository;


    @InjectMocks
    private ProjectCommandServiceImpl projectCommandService;


    private CreateProjectCommand validCommand;

    @BeforeEach
    void setUp() {

        validCommand = new CreateProjectCommand(
                1L,
                "Título del Proyecto",
                "Descripción detallada del proyecto.",
                "Resumen conciso.",
                "Tecnología"
        );
    }

    // ---------------------------------------------------------------------

    @Test
    @DisplayName("Debe crear y guardar un proyecto correctamente al recibir un comando válido")
    void handle_ShouldCreateAndSaveProject_WhenValidCommandIsProvided() {

        when(projectRepository.save(any(Project.class)))
                .thenAnswer(invocation -> {
                    Project projectToSave = invocation.getArgument(0);

                    return projectToSave;
                });


        // ACT
        Optional<Project> result = projectCommandService.handle(validCommand);

        // ASSERT


        assertTrue(result.isPresent(), "El resultado debe contener un proyecto.");
        Project createdProject = result.get();


        verify(projectRepository, times(1)).save(any(Project.class));


        assertEquals(validCommand.studentId(), createdProject.getStudentId(), "El studentId debe coincidir.");
        assertEquals(validCommand.title(), createdProject.getTitle(), "El título debe coincidir.");
        assertEquals(ProjectStatus.DRAFT, createdProject.getStatus(), "El estado debe ser DRAFT por defecto.");
    }
}