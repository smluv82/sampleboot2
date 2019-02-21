package com.sample.demo.vo.mongodb;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="person_collection")
public class Person {
	private String id;
	private String email;
	private String name;
	private String job;
	private Integer age;
}
