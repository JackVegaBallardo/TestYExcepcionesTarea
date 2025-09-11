package com.nttdata.compositepedido;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CompositePedidoApplication {
    public static void main(String[] args) {
        SpringApplication.run(CompositePedidoApplication.class, args);
    }
}