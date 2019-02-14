package com.sample.demo.biz.mongodb.mapper;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sample.demo.vo.mongodb.Person;

@Repository
public interface PersonRepository extends MongoRepository<Person, String>{

	Person findByEmail(final String email) throws Exception;
}
