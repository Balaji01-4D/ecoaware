package com.ecoaware.tracker.user;

import com.ecoaware.tracker.security.JwtService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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


    @Test
    void authenticateRegisteredUser() {
        // given
        String email = "user4@example.com";
        Users user = new Users(
                "user",
                email,
                "user@123",
                "USER"
        );
        Authentication authentication = new  UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        authentication.setAuthenticated(true);
        // when
        boolean expected = underTest.authenticate(user);
        // then
        assertThat(expected).isTrue();
    }

    @Test
    void unAuthenticateNonRegisteredUser() {
        // given
        String email = "user1@example.com";
        Users user = new Users(
                "user1",
                email,
                "user@1234",
                "USER"
        );
        // when
        boolean expected = underTest.authenticate(user);
        // then
        assertThat(expected).isFalse();
    }


}