package com.sample.demo.service.mongodb;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.demo.biz.mongodb.service.PersonService;
import com.sample.demo.common.validator.PersonValidator;
import com.sample.demo.vo.mongodb.Person;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/person")
public class PersonController {
	private final PersonService personService;
	private final PersonValidator personValidator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(personValidator);
	}

	@PostMapping(value="/insert", consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	private Person insert(@Valid @RequestBody final Person person, BindingResult bindingResult) throws Exception {
		log.info("/insert person[{}]", person);

		if(bindingResult.hasErrors()) {
			throw new Exception(String.join("-", "Error Count ", String.valueOf(bindingResult.getErrorCount())) );
		}

		return personService.insert(person);
	}
}
