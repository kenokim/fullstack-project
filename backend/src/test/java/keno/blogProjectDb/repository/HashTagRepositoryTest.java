package keno.blogProjectDb.repository;

import keno.blogProjectDb.repository.HashTagRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class HashTagRepositoryTest {
    @Autowired
    HashTagRepository hashTagRepository;

    @Test
    public void saveTest() {

    }
}