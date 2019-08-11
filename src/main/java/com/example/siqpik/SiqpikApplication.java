package com.example.siqpik;

import com.example.siqpik.repositories.AdmirerRepository;
import com.example.siqpik.repositories.AdmiringRepository;
import com.example.siqpik.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Controller;

@Controller
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SiqpikApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiqpikApplication.class, args);
	}

	@SuppressWarnings("Duplicates")
	@Bean
	public CommandLineRunner initData(UserRepository userRepo,
									  AdmirerRepository admirerRepo,
									  AdmiringRepository admiringRepo) {
		return (args) -> {
//
//			User user1 = new User("RDave", "Ronnie Dave", "dave@gnmail.com", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2323"));
//			User user2 = new User("Yery", "Yeray Rodriguez", "yeray@gmail.com", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2323"));
//			User user3 = new User("CaptainAmerica", "Pancho Viz", "p.viz@gmail.com", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2323"));
//			User user4 = new User("Lau", "Laura R.", "lau@gmail.com", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2323"));
//			User user5 = new User("Jordan", "Laura R.", "lau@gmail.com", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2323"));
//			User user6 = new User("Lebron", "Laura R.", "lau@gmail.com", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2323"));
//			User user7 = new User("Kobe", "Laura R.", "lau@gmail.com", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2323"));
//			User user8 = new User("Iverson", "Laura R.", "lau@gmail.com", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2323"));
//			User user9 = new User("Duncan", "Laura R.", "lau@gmail.com", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2323"));
//			User user10 = new User("Messi", "Laura R.", "lau@gmail.com", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2323"));
//			User user11 = new User("Xavi", "Laura R.", "lau@gmail.com", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2323"));
//			User user12 = new User("OldDirtyBastard", "Laura R.", "lau@gmail.com", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2323"));
//			User user13 = new User("Pierce", "Laura R.", "lau@gmail.com", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2323"));
//			User user14 = new User("Durant", "Laura R.", "lau@gmail.com", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2323"));
//			User user15 = new User("Rakim", "Laura R.", "lau@gmail.com", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2323"));
//			User user16 = new User("Biggie", "Laura R.", "lau@gmail.com", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2323"));
//			User user17 = new User("Preemo", "Laura R.", "lau@gmail.com", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2323"));
//			User user18 = new User("JayZ", "Laura R.", "lau@gmail.com", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2323"));
//			User user19 = new User("BReal", "Laura R.", "lau@gmail.com", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2323"));
//			User user20 = new User("MethodMan", "Laura R.", "lau@gmail.com", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2323"));
//
//										//RDave admiring
//			// Admiring Yeray
//			Admiring admiring1 = new Admiring(user1, user2);
//			Admirer admirer1 = new Admirer(user2, user1);
//			//Admiring Method Man
//			Admiring admiring2 = new Admiring(user1, user20);
//			Admirer admirer2 = new Admirer(user20, user1);
//			//Admiring Biggie
//			Admiring admiring3 = new Admiring(user1, user16);
//			Admirer admirer3 = new Admirer(user16, user1);
//
//										//Yeray admiring
//			//Admiring Pancho
//			Admiring admiring4 = new Admiring(user2, user3);
//			Admirer admirer4 = new Admirer(user3, user2);
//			//Admiring Laura
//			Admiring admiring5 = new Admiring(user2, user4);
//			Admirer admirer5 = new Admirer(user4, user2);
//			//Admiring Iverson
//			Admiring admiring6 = new Admiring(user2, user8);
//			Admirer admirer6 = new Admirer(user8, user2);
//
//										//Pancho admiring
//			//Admiring Ronn
//			Admiring admiring7 = new Admiring(user3, user1);
//			Admirer admirer7 = new Admirer(user1, user3);
//			//Admiring Messi
//			Admiring admiring8 = new Admiring(user3, user10);
//			Admirer admirer8 = new Admirer(user10, user3);
//			//Admiring Lebron
//			Admiring admiring9 = new Admiring(user3, user6);
//			Admirer admirer9 = new Admirer(user6, user3);
//
//										//Laura admiring
//			//Admiring Yeray
//			Admiring admiring10 = new Admiring(user4, user2);
//			Admirer admirer10 = new Admirer(user2, user4);
//			//Admiring Biggie
//			Admiring admiring11 = new Admiring(user4, user16);
//			Admirer admirer11 = new Admirer(user16, user4);
//			//Admiring Jordan
//			Admiring admiring12 = new Admiring(user4, user5);
//			Admirer admirer12 = new Admirer(user5, user4);
//
//
//			userRepo.save(user1);
//			userRepo.save(user2);
//			userRepo.save(user3);
//			userRepo.save(user4);
//			userRepo.save(user5);
//			userRepo.save(user6);
//			userRepo.save(user7);
//			userRepo.save(user8);
//			userRepo.save(user9);
//			userRepo.save(user10);
//			userRepo.save(user11);
//			userRepo.save(user12);
//			userRepo.save(user13);
//			userRepo.save(user14);
//			userRepo.save(user15);
//			userRepo.save(user16);
//			userRepo.save(user17);
//			userRepo.save(user18);
//			userRepo.save(user19);
//			userRepo.save(user20);
//
//			admiringRepo.save(admiring1);
//			admiringRepo.save(admiring2);
//			admiringRepo.save(admiring3);
//			admiringRepo.save(admiring4);
//			admiringRepo.save(admiring5);
//			admiringRepo.save(admiring6);
//			admiringRepo.save(admiring7);
//			admiringRepo.save(admiring8);
//			admiringRepo.save(admiring9);
//			admiringRepo.save(admiring10);
//			admiringRepo.save(admiring11);
//			admiringRepo.save(admiring12);
//
//			admirerRepo.save(admirer1);
//			admirerRepo.save(admirer2);
//			admirerRepo.save(admirer3);
//			admirerRepo.save(admirer4);
//			admirerRepo.save(admirer5);
//			admirerRepo.save(admirer6);
//			admirerRepo.save(admirer7);
//			admirerRepo.save(admirer8);
//			admirerRepo.save(admirer9);
//			admirerRepo.save(admirer10);
//			admirerRepo.save(admirer11);
//			admirerRepo.save(admirer12);

		};
	}

}
