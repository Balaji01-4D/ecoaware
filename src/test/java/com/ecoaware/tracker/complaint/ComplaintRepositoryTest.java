package com.ecoaware.tracker.complaint;

import com.ecoaware.tracker.category.Category;
import com.ecoaware.tracker.category.CategoryRepository;
import com.ecoaware.tracker.category.CategoryResponse;
import com.ecoaware.tracker.enums.Status;
import com.ecoaware.tracker.user.UserRepository;
import com.ecoaware.tracker.user.Users;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ComplaintRepositoryTest {

    @Autowired
    private ComplaintRepository underTest;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findByCreatedById() {
        // given
        String email = "user@example.com";
        Users user = new Users(
                "user",
                email,
                "user@123",
                "USER"
        );
        userRepository.save(user);
        Category category = Category.builder().name("water").build();
        categoryRepository.save(category);

        Complaint complaint = Complaint.builder()
                .id(12L)
                .title("example issue")
                .description("this is caused by the example")
                .createdAt(LocalDateTime.now())
                .createdBy(user)
                .status(Status.PENDING)
                .imagePath("/downloads/example.png")
                .category(category)
                .build();
        underTest.save(complaint);
        // when

        // then
        List<Complaint> expectedComplaint = underTest.findByCreatedById(12L);
        assertThat(expectedComplaint).isEqualTo(complaint);
        assertThat(userRepository.findAll().size()).isEqualTo(1);
    }

}