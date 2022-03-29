package keno.blogProjectDb.support;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
//@SpringBootTest
class StackExchangeDataTest {
    //@Autowired StackExchangeData gen;
    StackExchangeData gen = new StackExchangeData();
    @Test
    public void test() {
        System.out.println("Hello test!");
        gen.getStackExchange();
    }
}