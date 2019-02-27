package com.sample.demo.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.ListUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ImmutableListTest {
	@Test(expected=UnsupportedOperationException.class)
	public void listTest() {
		List<String> list = new ArrayList<>(Arrays.asList("1", "2", "3"));
		list.stream().forEach(obj -> log.info("obj1 -> {}", obj));

		//jdk collection
//		List<String> unmodifyList = Collections.unmodifiableList(list);
//		unmodifyList.add("4");
//		unmodifyList.stream().forEach(obj -> log.info("obj2 -> {}", obj));

		//guava
//		List<String> unmodifyList = ImmutableList.copyOf(list);
//		unmodifyList.add("4");
//		unmodifyList.stream().forEach(obj -> log.info("obj2 -> {}", obj));

		//apache-commons
		List<String> unmodifyList = ListUtils.unmodifiableList(list);
		unmodifyList.add("4");
		unmodifyList.stream().forEach(obj -> log.info("obj2 -> {}", obj));

	}
}
