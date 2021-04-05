package com.proyectofincurso.appValores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppValoresApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppValoresApplication.class, args);
		System.out.println("========================================");
		System.out.println("Api de valores a la espera de peticiones");
		System.out.println("========================================");		
	}

}
 