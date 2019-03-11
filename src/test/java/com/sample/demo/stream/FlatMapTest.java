package com.sample.demo.stream;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class FlatMapTest {
	@Test
	public void flatMap_테스트() {
		//그냥 map
		List<String> myList = Stream.of("a", "b")
				.map(String::toUpperCase)
				.collect(Collectors.toList())
				;

		myList.stream().forEach(System.out::println);

		List<List<String>> listlist = Lists.newArrayList(
				Lists.newArrayList("c")
				, Lists.newArrayList("d")
				);

		listlist
		.stream()
		.forEach(System.out::println);

		List<String> listlist2 = listlist
		.stream()
		.flatMap(Collection::stream)
		.map(String::toUpperCase)
		.collect(Collectors.toList())
		;

		listlist2.stream().forEach(System.out::println);
	}
}
