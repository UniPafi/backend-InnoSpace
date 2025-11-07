package com.innospace.platform.core.integration.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.innospace.platform.iam.domain.model.aggregates.User;
import com.innospace.platform.iam.domain.model.commands.SignInCommand;
import com.innospace.platform.iam.domain.model.commands.SignUpCommand;
import com.innospace.platform.iam.domain.model.services.UserCommandService;
import com.innospace.platform.iam.domain.model.valueobjects.AccountType;
import com.innospace.platform.iam.interfaces.rest.resources.SignUpResource;
import com.innospace.platform.iam.interfaces.rest.resources.UserResource;
import com.innospace.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.lang.reflect.Field;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerSignUpTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @org.springframework.test.context.bean.override.mockito.MockitoBean
    private UserCommandService userCommandService;



    @Test
    @DisplayName("POST /sign-up - Debe crear un usuario y retornar 201 CREATED con UserResource")
    void signUp_ShouldReturn201Created_WhenServiceIsSuccessful() throws Exception {



        SignUpResource inputResource = new SignUpResource(

                "Student Name",
                "new.student@domain.com",
                "P4sswOrd!",
                "STUDENT"
        );

        Long SIMULATED_ID = 101L;
        User createdUser = new User(
                inputResource.email(),
                "hashed_password_mock",
                AccountType.STUDENT
        );

        try {

            Field idField = AuditableAbstractAggregateRoot.class.getDeclaredField("id");

            idField.setAccessible(true);
            idField.set(createdUser, SIMULATED_ID);
        } catch (NoSuchFieldException | IllegalAccessException e) {

            throw new RuntimeException("Fallo al inyectar ID en el Aggregate Root", e);
        }


        UserResource expectedResource = new UserResource(
                101L, // ID simulado
                inputResource.email(),
                inputResource.accountType()
        );


        Mockito.doReturn(Optional.of(createdUser))
                .when(userCommandService).handle((SignUpCommand) any());


        mockMvc.perform(post("/api/v1/authentication/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputResource)))


                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.email").value(inputResource.email()))
                .andExpect(jsonPath("$.accountType").value(inputResource.accountType()))
                .andExpect(status().isCreated());
    }

    // ---------------------------------------------------------------------


}