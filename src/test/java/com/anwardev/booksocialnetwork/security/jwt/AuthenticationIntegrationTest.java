package com.anwardev.booksocialnetwork.security.jwt;

import com.anwardev.booksocialnetwork.config.SecurityConfiguration;
import com.anwardev.booksocialnetwork.config.SecurityJwtConfiguration;
import com.anwardev.booksocialnetwork.config.WebConfigurer;
import com.anwardev.booksocialnetwork.management.SecurityMetersService;
import com.anwardev.booksocialnetwork.web.rest.AuthenticateController;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.test.context.SpringBootTest;
import tech.jhipster.config.JHipsterProperties;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(
    properties = {
        "jhipster.security.authentication.jwt.base64-secret=fd54a45s65fds737b9aafcb3412e07ed99b267f33413274720ddbb7f6c5e64e9f14075f2d7ed041592f0b7657baf8",
        "jhipster.security.authentication.jwt.token-validity-in-seconds=60000",
    },
    classes = {
        JHipsterProperties.class,
        WebConfigurer.class,
        SecurityConfiguration.class,
        SecurityJwtConfiguration.class,
        SecurityMetersService.class,
        AuthenticateController.class,
        JwtAuthenticationTestUtils.class,
    }
)
public @interface AuthenticationIntegrationTest {
}
