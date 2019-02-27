package com.sample.demo.list;

import java.util.List;

import org.apache.commons.collections4.ListUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MultipleItemListTest {
//	@Test
	public void 여러리스트_아이템_합치기() {
		List<Integer> list = Lists.newArrayList(1,2,3,4,5);
		List<Integer> anotherList = Lists.newArrayList(6,7,8,9,10);
		list.stream().forEach(System.out::println);
		System.out.println("----------------------------");
		//addAll
//		list.addAll(anotherList);

		//java8
		list.stream().forEachOrdered(anotherList::add);
		list.stream().forEach(System.out::println);
	}

	@Test
	public void 리스트_분할하여_처리() {
		List<Integer> list = Lists.newArrayList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20);
		List<List<Integer>> partionList = ListUtils.partition(list, 5);

//		partionList.parallelStream().forEach(subList -> {
		partionList.stream().forEach(subList -> {
			System.out.println("-------------------------------");
			for(Integer i : subList) {
				System.out.println("i : " + i);
			}

//			subList.parallelStream().forEach(obj -> {
//				log.info("obj : {}", obj);
//			});
		});
	}
}
