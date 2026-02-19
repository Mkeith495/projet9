package com.openclassroom.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GatewaySecurityTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void shouldReturnUnauthorizedWhenUnauthenticated() {
        webTestClient.get().uri("/frontend/patients")
                .exchange()
                .expectStatus().isUnauthorized();
    }

    @Test
    void loginPageShouldBeAccessible() {
        webTestClient.get().uri("/login")
                .exchange()
                .expectStatus().isOk();
    }
}