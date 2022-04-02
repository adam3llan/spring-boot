package net.codejava.CodeJavaApp;

import static org.assertj.core.api.Assertions.assertThat;

import net.codejava.CodeJavaApp.User.Products;
import net.codejava.CodeJavaApp.User.User;
import net.codejava.CodeJavaApp.repository.ProductRepository;
import net.codejava.CodeJavaApp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class UserRepositoryTests {

    @Autowired
    private ProductRepository productRepository;


    @Test
    public void testCreateProducts() {


    }



}
