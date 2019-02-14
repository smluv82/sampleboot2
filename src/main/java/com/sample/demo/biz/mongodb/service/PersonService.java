package com.sample.demo.biz.mongodb.service;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.sample.demo.biz.mongodb.mapper.PersonRepository;
import com.sample.demo.vo.mongodb.Person;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class PersonService {
	private final PersonRepository personRepository;

	/**
	 * insert person
	 *
	 * @param person
	 * @return
	 * @throws Exception
	 */
	public Person insert(final Person person) throws Exception {
		log.info("insert person[{}]", person);

		if(Objects.nonNull(personRepository.findByEmail(person.getEmail()))) {
			log.error("email[{}] is already exist", person.getEmail());
			throw new Exception();
		}

		return personRepository.insert(person);
	}
}
