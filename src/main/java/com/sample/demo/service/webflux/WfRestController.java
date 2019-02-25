package com.sample.demo.service.webflux;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.demo.vo.mongodb.Person;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/webflux/rest")
public class WfRestController {
	@GetMapping("/hello/{text}")
	private Mono<Person> helloMono(@PathVariable final String text) {
		log.info("helloMono in");

		Person person = new Person("tester1", "tester@yopmail.com", text, "programmer", 38);
		log.info("person[{}]", person);

//		return Mono.justOrEmpty(person);
		return Mono.justOrEmpty(Optional.ofNullable(person));
	}


}
