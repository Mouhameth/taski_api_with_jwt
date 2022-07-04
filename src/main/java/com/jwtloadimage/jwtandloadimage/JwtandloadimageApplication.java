package com.jwtloadimage.jwtandloadimage;

import com.jwtloadimage.jwtandloadimage.entities.Role;
import com.jwtloadimage.jwtandloadimage.entities.User;
import com.jwtloadimage.jwtandloadimage.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class JwtandloadimageApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtandloadimageApplication.class, args);
	}


	@Bean
	BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
