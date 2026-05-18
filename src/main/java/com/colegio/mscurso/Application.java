package com.colegio.mscurso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.colegio.mscursos")
@EnableFeignClients(basePackages = "com.colegio.mscursos") // <-- Esto activa la magia de Feign
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}