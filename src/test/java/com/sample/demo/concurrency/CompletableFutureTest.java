package com.sample.demo.concurrency;

import java.util.concurrent.CompletableFuture;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CompletableFutureTest {
	private Runnable task = () -> {
		try {
			Thread.sleep(5 * 1_000L);
		}catch(Exception e) {
			log.error("Exception : ", e);
		}
		log.info("TASK complete");
	};

//	@Test
	public void 퓨처_테스트() throws Exception {
		CompletableFuture
		.runAsync(task)
		.thenCompose(aVoid -> CompletableFuture.runAsync(task))
		.thenAcceptAsync(aVoid -> log.info("all task complete"))
		.exceptionally(throwable -> {
			log.error("throwable : ", throwable);
			return null;
		});

		Thread.sleep(11 * 1_000L);
	}

//	@Test
	public void 퓨처_테스트2() throws Exception {
		CompletableFuture.supplyAsync(() -> {
			System.out.println("testst");
			System.out.println("testst");
			return "sdfsdfsfd";
		})
//		.thenComposeAsync(x-> {
//			System.out.println("x :" + x);
//			return "xcxcfxfvxV" + x;
//		})
		.thenApplyAsync(str->str+"+ tailed")
		.thenAcceptAsync(finalResult -> System.out.println("finalResult : " + finalResult))
		;
	}

	@Test
	public void 프로세서_수_확인_스트림_parallel갯수() throws Exception {
		//parallel의 병렬 스레드는 forkjoinpool 사용
		//Runtime.getRuntime().availableProcessors()가 반환하는 값에 상응하는 스레드로 병렬로 처리한다.
		System.out.println("프로세서 수 : " + Runtime.getRuntime().availableProcessors());
	}
}
