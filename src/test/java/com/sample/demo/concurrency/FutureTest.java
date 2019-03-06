package com.sample.demo.concurrency;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class FutureTest {
	@Test
	public void testParallelExecute() throws Exception {
		// 10개의 Thread를 가진 ThreadPool생성
		ExecutorService threadPool = Executors.newFixedThreadPool(10);
		// Thread들이 비동기로 수행되면 그 결과를 담을 Futrure 객체
		List<Future<String>> futureList = new ArrayList<>();

		for (int i=0; i<100 ;i++) {
			// callable 객체를 통해 어떤 일을 수행할지 결정한다.
//			Callable<HelloMessage> callable = () -> {
//				System.out.println("Time : " + new Date() + " -Thread Name : " + Thread.currentThread().getName()
//						+ " - Text : " + sample);
//				// 테스트겸 3번만 0.5초간 중지
//				if (Thread.currentThread().getName().endsWith("thread-3")) {
//					Thread.sleep(500);
//				}
//				HelloMessage HelloMessage = new HelloMessage();
//				HelloMessage.setName(sample);
//				return HelloMessage;
//			};

			// 생성된 callable들을 threadpool에서 수행시키고 결과는 Future 목록에 담는다.
			futureList.add(threadPool.submit(() -> {
				System.out.println("Time : " + new Date() + " -Thread Name : " + Thread.currentThread().getName());
				// 테스트겸 2번만 0.5초간 중지
				if (Thread.currentThread().getName().endsWith("thread-2")) {
					Thread.sleep(5000);
				}
				return Thread.currentThread().getName();
			}));
		}
		// 수행중인 callable들이 다 끝나면 threadpool을 종료시킨다.(반드시 해야함)
		threadPool.shutdown();

		for (Future<String> future : futureList) {
			System.out.println(future.get());
		}
	}

}
