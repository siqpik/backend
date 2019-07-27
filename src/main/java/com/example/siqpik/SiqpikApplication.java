package com.example.siqpik;

import com.example.siqpik.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

@Controller
@SpringBootApplication
public class SiqpikApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiqpikApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(UserRepository userRepo) {
		return (args) -> {

//			User user1 = new User("RDave", "Ronnie Dave", "dave@gnmail.com", "1234");
//			User user2 = new User("Yery", "Yeray Rodriguez", "yeray@gmail.com", "5678");
//			User user3 = new User("Captain America", "Pancho Viz", "p.viz@gmail.com", "6789");
//			User user4 = new User("Layra", "Laura R.", "lau@gmail.com", "7777");
//
//			userRepo.save(user1);
//			userRepo.save(user2);
//			userRepo.save(user3);
//			userRepo.save(user4);

		};
	}

}
