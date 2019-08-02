package com.example.siqpik;

import com.example.siqpik.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.userdetails.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
//			User user4 = new User("Lau", "Laura R.", "lau@gmail.com", "7777");
//
//			userRepo.save(user1);
//			userRepo.save(user2);
//			userRepo.save(user3);
//			userRepo.save(user4);

		};
	}

}
@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

	@Autowired
	UserRepository userRepo;

	//The User used here is from Spring Security
	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(inputName -> userRepo.findByName(inputName)
				.map(player -> new User(player.getName(), player.getPassword(),
						AuthorityUtils.createAuthorityList("USER")))
				.orElseThrow(() -> new UsernameNotFoundException("Unknown user: " + inputName)));
	}
}

@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/**")
				.permitAll()
				.anyRequest()
				.hasAuthority("USER");

		http.formLogin()
				.loginPage("/login")
				.usernameParameter("username")
				.passwordParameter("password");

		http.logout().logoutUrl("/logout");

		// turn off checking for CSRF tokens
		http.csrf().disable();

		// if user is not authenticated, just send an authentication failure response
		http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

		// if login is successful, just clear the flags asking for authentication
		http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));

		// if login fails, just send an authentication failure response
		http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

		// if logout is successful, just send a success response
		http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
	}

	private void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		}
	}
}

