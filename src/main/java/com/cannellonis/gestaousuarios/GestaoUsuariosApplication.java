package com.cannellonis.gestaousuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class GestaoUsuariosApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestaoUsuariosApplication.class, args);
    }

}
