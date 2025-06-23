package com.ecoaware.tracker.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldFindUserByEmail() {
        // given
        String email = "user@example.com";
        Users user = new Users(
                "user",
                email,
                "user@123",
                "USER"
        );
        underTest.save(user);
        // when
        Users expected = underTest.findByEmail(email);
        // then
        assertThat(expected).isEqualTo(user);

    }

    @Test
    void itShouldNotFindNonExistUserByEmail() {
        // given
        String email = "user2@example.com";
        // when
        Users expected = underTest.findByEmail(email);
        // then
        assertThat(expected).isNull();

    }
}