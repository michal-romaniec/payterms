package io.github.michalromaniec.payterms;

import org.springframework.boot.SpringApplication;

public class TestPaytermsApplication {

    public static void main(String[] args) {
        SpringApplication.from(PaytermsApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
