package com.company.warehouse.repository;

import com.company.warehouse.domain.Article;
import com.company.warehouse.domain.Product;
import com.company.warehouse.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTests {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByUsername_UserDataComplete_UserIsFound() {
        testEntityManager.persistAndFlush(new User("userTest", "passwordTest", "user"));
        User user = userRepository.findByUsername("userTest");
        assertEquals("userTest", user.getUsername());
        assertEquals("passwordTest", user.getPassword());
        assertEquals("user", user.getRole());
    }
}
