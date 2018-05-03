// As I say yes to life, life says yes to me.

// Deep at the center of my being is an infinite well of love.

package com.example.excelsior;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Collection;

import java.io.*;

@SpringBootApplication
public class ExcelsiorApplication {


		@Bean
		ApplicationRunner runner(PersonRepository personRepository) {
				return args -> {
						Arrays
							.asList("Dr. Johnson", "Dr. Syer", "Dr. De Volder", "Dr. Pollack")
							.forEach(name -> personRepository.save(new Person(null, name)));

						personRepository.findAll().forEach(System.out::println);
				};

		}

		public static void main(String[] args) {
				SpringApplication.run(ExcelsiorApplication.class, args);
		}
}

@RestController
class PersonRestController {

		private final PersonRepository personRepository;

		PersonRestController(PersonRepository personRepository) {
				this.personRepository = personRepository;
		}

		@GetMapping("/")
		String hello() {
				return "Hello";
		}

		@GetMapping("/people")
		Collection<Person> people() {
				return this.personRepository.findAll();
		}
}

interface PersonRepository extends JpaRepository<Person, Long> {
}

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
class Person {

		@Id
		@GeneratedValue
		private Long id;
		private String name;
}
