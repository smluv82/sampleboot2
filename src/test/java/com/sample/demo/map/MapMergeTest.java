package com.sample.demo.map;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.util.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sample.demo.vo.websocket.HelloMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MapMergeTest {
	@Test
	public void 맵_합치기() {
		Map<String, HelloMessage> testMap1 = Maps.newHashMap("1", new HelloMessage("11111111111111"));
		testMap1.put("2", new HelloMessage("2222222222222"));
		Map<String, HelloMessage> testMap2 = Maps.newHashMap("3", new HelloMessage("333333333333333333"));
		testMap2.put("4", new HelloMessage("4444444444444"));

		Map<String, HelloMessage> resultMap = Stream.concat(testMap1.entrySet().stream(), testMap2.entrySet().stream())
//				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (value1, value2) -> new HelloMessage(value1.getName())));
//				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (value1, value2) -> new HelloMessage(value2.getName())));
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		System.out.println(resultMap);
	}
}
