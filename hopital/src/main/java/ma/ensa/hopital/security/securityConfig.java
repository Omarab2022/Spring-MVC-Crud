package ma.ensa.hopital.security;
import ma.ensa.hopital.security.encoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.jaas.memory.InMemoryConfiguration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.bind.annotation.RequestMapping;

@Configuration
@EnableWebSecurity
public class securityConfig {


	@Autowired
	public PasswordEncoder passwordEncoder ;


	@Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsManager(){

		return new InMemoryUserDetailsManager(


				User.withUsername("user1").password(passwordEncoder.encode("1234")).roles("USER").build(),
				User.withUsername("user2").password(passwordEncoder.encode("4444")).roles("USER").build(),
				User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("USER","ADMIN").build()

		);
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.formLogin(formLogin ->
				formLogin.permitAll()
		);



		httpSecurity.authorizeHttpRequests(authorizeRequests ->
				authorizeRequests.anyRequest().authenticated()


		);


		return httpSecurity.build();
	}


}