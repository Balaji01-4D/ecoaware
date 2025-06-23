package com.ecoaware.tracker.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;


    private UserService underTest;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new UserService(userRepository, bCryptPasswordEncoder, authenticationManager);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
        userRepository.deleteAll();
    }

    @Test
    void canRegister() {
        // given
        String email = "user@example.com";
        Users user = new Users(
                "user",
                email,
                "user@123",
                "USER"
        );
        // when
        underTest.register(user);
        // then
        ArgumentCaptor<Users> usersArgumentCaptor = ArgumentCaptor.forClass(Users.class);
        verify(userRepository).save(usersArgumentCaptor.capture());

        Users capturedUser = usersArgumentCaptor.getValue();

        assertThat(capturedUser).isEqualTo(user);

    }


    @Test
    void findUserByEmail() {
        // given
        String email = "user2@example.com";
        Users user = new Users(
                "user",
                email,
                "user@123",
                "USER"
        );
        // when
        underTest.findUserByEmail(email);
        // then
        ArgumentCaptor<String> emailArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(userRepository).findByEmail(emailArgumentCaptor.capture());
        String capturedUser = emailArgumentCaptor.getValue();
        assertThat(capturedUser).isEqualTo(user.getEmail());
    }
}