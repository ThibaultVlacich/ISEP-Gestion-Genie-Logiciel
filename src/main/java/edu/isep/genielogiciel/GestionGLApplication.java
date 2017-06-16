package edu.isep.genielogiciel;

import edu.isep.genielogiciel.config.MVCConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionGLApplication {

	public static void main(String[] args) {
		SpringApplication.run(MVCConfig.class, args);
	}
}
