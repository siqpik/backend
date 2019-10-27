package com.example.siqpik;

import com.example.siqpik.domain.Admirer;
import com.example.siqpik.domain.User;
import com.example.siqpik.repositories.AdmirerRepository;
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
									  AdmirerRepository admirerRepo) {
		return (args) -> {
//
//			User ronn = new User("RDave", "Ronnie Dave", "dave@gnmail.com", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2323"));
//			User yeray = new User("Yery", "Yeray Rodriguez", "yeray@gmail.com", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2323"));
//			User pancho = new User("CaptainAmerica", "Pancho Viz", "p.viz@gmail.com", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2323"));
//			User laura = new User("Lau", "Laura R.", "lau@gmail.com", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2323"));
//			User jordan = new User("Jordan", "Laura R.", "jordan@gmail.com", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2323"));
//			User lebron = new User("Lebron", "Laura R.", "lb@gmail.com", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2323"));
//			User kobe = new User("Kobe", "Laura R.", "kobe@gmail.com", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2323"));
//			User iverson = new User("Iverson", "Laura R.", "iverson@gmail.com", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2323"));
//			User duncan = new User("Duncan", "Laura R.", "tim@gmail.com", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2323"));
//			User messi = new User("Messi", "Laura R.", "leo@gmail.com", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2323"));
//			User xavi = new User("Xavi", "Laura R.", "xavi@gmail.com", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2323"));
//			User oldDirty = new User("OldDirtyBastard", "Laura R.", "odb@gmail.com", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2323"));
//			User paulPierce = new User("Pierce", "Laura R.", "ppierce@gmail.com", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2323"));
//			User durant = new User("Durant", "Laura R.", "kd@gmail.com", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2323"));
//			User rakim = new User("Rakim", "Laura R.", "rakim@gmail.com", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2323"));
//			User biggie = new User("Biggie", "Laura R.", "big@gmail.com", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2323"));
//			User preemo = new User("Preemo", "Laura R.", "premier@gmail.com", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2323"));
//			User jayZ = new User("JayZ", "Laura R.", "jayz@gmail.com", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2323"));
//			User bReal = new User("BReal", "Laura R.", "breal@gmail.com", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2323"));
//			User methodMan = new User("MethodMan", "Laura R.", "methodman@gmail.com", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("2323"));
//
//										//RDave is admiring:
//			//Yeray
//			Admirer admirer1 = new Admirer(ronn, yeray);
//			//Admiring Method Man
//			Admirer admirer2 = new Admirer(ronn, methodMan);
//			//Admiring Biggie
//			Admirer admirer3 = new Admirer(ronn, biggie);
//
//
//										//Yeray is admiring:
//			//Pancho
//			Admirer admirer4 = new Admirer(yeray, pancho);
//			//Laura
//			Admirer admirer5 = new Admirer(yeray, laura);
//			//Iverson
//			Admirer admirer6 = new Admirer(yeray, iverson);
//
//										//Pancho is admiring:
//			//Ronn
//			Admirer admirer7 = new Admirer(pancho, ronn);
//			//Messi
//			Admirer admirer8 = new Admirer(pancho, messi);
//			//Lebron
//			Admirer admirer9 = new Admirer(pancho, lebron);
//
//										//Laura is admiring:
//			//Yeray
//			Admirer admirer10 = new Admirer(laura, yeray);
//			//Biggie
//			Admirer admirer11 = new Admirer(laura, biggie);
//			//Jordan
//			Admirer admirer12 = new Admirer(laura, jordan);
//
//
//			userRepo.save(ronn);
//			userRepo.save(yeray);
//			userRepo.save(pancho);
//			userRepo.save(laura);
//			userRepo.save(jordan);
//			userRepo.save(lebron);
//			userRepo.save(kobe);
//			userRepo.save(iverson);
//			userRepo.save(duncan);
//			userRepo.save(messi);
//			userRepo.save(xavi);
//			userRepo.save(oldDirty);
//			userRepo.save(paulPierce);
//			userRepo.save(durant);
//			userRepo.save(rakim);
//			userRepo.save(preemo);
//			userRepo.save(jayZ);
//			userRepo.save(bReal);
//			userRepo.save(methodMan);
//			userRepo.save(biggie);
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

//			////Test for CI/CD

		};
	}

}
