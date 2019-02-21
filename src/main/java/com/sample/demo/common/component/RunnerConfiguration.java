package com.sample.demo.common.component;

import java.util.Arrays;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RunnerConfiguration implements CommandLineRunner, ApplicationRunner {


	/* (non-Javadoc)
	 *
	 * ApplicationRunner
	 *
	 *
	 * @see org.springframework.boot.ApplicationRunner#run(org.springframework.boot.ApplicationArguments)
	 */
	@Order(1)
	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("ApplicationRunner args : {}", Arrays.toString(args.getSourceArgs()));
	}


	/* (non-Javadoc)
	 *
	 * CommandLineRunner
	 *
	 * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
	 */
	@Order(2)
	@Override
	public void run(String... args) throws Exception {
		log.info("CommandLineRunner args : {}", Arrays.toString(args));
	}

}
