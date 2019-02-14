package com.sample.demo.common.validator;

import java.util.function.Predicate;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sample.demo.vo.mongodb.Person;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PersonValidator implements Validator {
	//IPV4 규격 여부 패턴
	private static final Pattern IPV4_PATTERN = Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");
	//이메일 체크 정규식
	private static final Pattern EMAIL_PATTERN = Pattern.compile("^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$");

	private <T> boolean isValid(T val, Predicate<T> condition) {
		return condition.test(val);
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Person.class.equals(clazz);
	}

	/* (non-Javadoc)
	 *
	 * person 객체의 필수 데이터 체크
	 *
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		Person person = (Person) target;

		log.info("person[{}]", person);

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email is null");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name is null");

		//이메일 체크
		if(isValid(person.getEmail(), email -> !EMAIL_PATTERN.matcher(email).matches())) {
			errors.rejectValue("email", "email is not email format");
		}
	}
}
