package com.pitchbridge.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PitchbridgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PitchbridgeApplication.class, args);
	}

}
