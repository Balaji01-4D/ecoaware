package com.ecoaware.tracker;

import com.ecoaware.tracker.security.JwtService;
import com.ecoaware.tracker.user.*;
import com.ecoaware.tracker.user.dao.AuthenticationResponse;
import com.ecoaware.tracker.user.dao.LoginRequest;
import com.ecoaware.tracker.user.dao.RegisterRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTests {

	@Autowired
	private UserService userService;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TestRestTemplate testRestTemplate;

	@LocalServerPort
	private int port;
	private String baseUrl = "http://localhost:";

	
	@AfterEach
	void tearDown() {
		userRepository.deleteAll();
	}

	@Test
	void canRegisterUser() {
		// given
		String email = "test1@emaple.com";
		RegisterRequest registerRequest = RegisterRequest.builder()
				.name("test")
				.email(email)
				.password("test@123")
				.role("USER")
				.build();
		// when
		AuthenticationResponse authenticationResponse = testRestTemplate.
				postForObject(baseUrl + port + "/auth/register", registerRequest, AuthenticationResponse.class);
		String usernameExpected = jwtService.extractUsername(authenticationResponse.getToken());
		// then
		assertThat(usernameExpected)
				.isEqualTo(registerRequest.getEmail());
		assertThat(userRepository.findByEmail(email)).isNotNull();
	}


	@Test
	void canAuthenticateUser() {
		// given
		String email = "test1@emaple.com";
		RegisterRequest registerRequest = RegisterRequest.builder()
				.name("test")
				.email(email)
				.password("test@123")
				.role("USER")
				.build();

		testRestTemplate.
				postForObject(baseUrl + port + "/auth/register", registerRequest, AuthenticationResponse.class);

		LoginRequest loginRequest = new LoginRequest(registerRequest.getEmail(), registerRequest.getPassword());
		// when
		AuthenticationResponse authenticationResponse = testRestTemplate.postForObject(
				baseUrl + port + "/auth/login", loginRequest, AuthenticationResponse.class
		);
		String usernameExpected = jwtService.extractUsername(authenticationResponse.getToken());
		// then
		assertThat(usernameExpected).isEqualTo(email);
		assertThat(userRepository.findByEmail(email)).isNotNull();
	}

	@Test
	void canUnauthenticateUserWhoIsNotRegistered() {
		// given
		String email = "test@emaple.com";
		Users user = new Users(
				"testSubject", email, "test@123", "USER"
		);
		LoginRequest loginRequest = new LoginRequest(user.getEmail(), user.getPassword());
		// when
		ResponseEntity<String> authenticationResponse = testRestTemplate.postForEntity(
				baseUrl + port + "/auth/login", loginRequest, String.class
		);
		System.out.println(testRestTemplate.postForEntity(baseUrl + port + "/auth/login", loginRequest, String.class));
		// then
		assertThat(authenticationResponse.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
	}


}
