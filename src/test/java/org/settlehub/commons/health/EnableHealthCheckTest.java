package org.settlehub.commons.health;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.settlehub.commons.health.annotation.EnableHealthCheck;

@SpringBootTest
class EnableHealthCheckTest {

    @SpringBootApplication
    @EnableHealthCheck
    static class TestMicroserviceApplication { }

    @Test
    void contextLoads() { }

}
