package com.sample.demo;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ListNullRemoveTest {
	@Test
	public void null_삭제() {
		//1. using guava
//		List<Integer> list = Lists.newArrayList(1,2,null,3,4,5,null);
//		list.stream().forEach(System.out::println);
//		System.out.println("----------------------------");
//		Iterables.removeIf(list, Predicates.isNull());
//		list.stream().forEach(System.out::println);

		//2. using apache commons collection
//		List<Integer> list = Lists.newArrayList(1,2,null,3,4,5,null);
//		list.stream().forEach(System.out::println);
//		System.out.println("----------------------------");
//		CollectionUtils.filter(list, PredicateUtils.notNullPredicate());
//		list.stream().forEach(System.out::println);

		//3. using java8
		List<Integer> list = Lists.newArrayList(1,2,null,3,4,5,null);
		list.stream().forEach(System.out::println);
		System.out.println("----------------------------");
		//stream
		List<Integer> list2 = list.parallelStream().filter(Objects::nonNull).collect(Collectors.toList());
		list2.stream().forEach(System.out::println);

		//removeIf
		System.out.println("+++++++++++++++++++++++++++++++++");
		list.stream().forEach(System.out::println);
		System.out.println("----------------------------");
		list.removeIf(Objects::isNull);
		list.stream().forEach(System.out::println);
	}
}
