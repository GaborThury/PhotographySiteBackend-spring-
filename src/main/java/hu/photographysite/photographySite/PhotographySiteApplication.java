package hu.photographysite.photographySite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class PhotographySiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotographySiteApplication.class, args);
	}

}

