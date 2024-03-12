package com.example.jediscache;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class JediscacheApplication implements ApplicationRunner {

	private final UserRepository userRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		userRepository.save(User.builder().name("name1").email("email1").build());
		userRepository.save(User.builder().name("name2").email("email2").build());
		userRepository.save(User.builder().name("name3").email("email3").build());
		userRepository.save(User.builder().name("name4").email("email4").build());
	}

	public static void main(String[] args) {
		SpringApplication.run(JediscacheApplication.class, args);
	}

}
