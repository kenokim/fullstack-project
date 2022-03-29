package keno.blogProjectDb.repository;

import keno.blogProjectDb.domain.User;
import keno.blogProjectDb.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;


@SpringBootTest
@ActiveProfiles("test")
@Transactional
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void findByLoginIdTest() {
        // given
        String loginId = "tester";
        String password = "test~!1";

        User user = User.createUser(loginId, password);
        // when
        User saved = userRepository.save(user);
        User findByLoginId = userRepository.findByLoginId(loginId);
        // then

        Assertions.assertThat(saved).isEqualTo(findByLoginId);
    }


}