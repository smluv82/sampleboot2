package com.sample.demo.biz.oauth.service;

import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sample.demo.biz.oauth.mapper.OauthMemberRepository;
import com.sample.demo.vo.oauth.OauthMember;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class OauthMemberService {
	private final OauthMemberRepository oauthMemberRepository;

	private void testHystrix() throws Exception {
		log.info("testHystrix start");
		Thread.sleep(4000);
		log.info("testHystrix end");
	}

	//Hystrix timeout default는 1초임.
	//전체 메서드에 설정할때 class에다 설정해도 됨.
//	@HystrixCommand
//	public OauthMember findByEmail(final String email) throws Exception {
//		log.info("findByEmail : {}", email);
//		testHystrix();
//		return oauthMemberRepository.findByEmail(email);
//	}

//	@HystrixCommand(
//			//히스트릭스를 사용자 정의하기 위해 추가 매개변수 전달
//			commandProperties= {
//					@HystrixProperty(
//							//회로 차단기의 타임아웃 시간(단위 : 미리세컨)을 설정하는데 사용 프로퍼티
//							name="execution.isolation.thread.timeoutInMilliseconds", value="3000"
//					)
//			},
//			//fallbackMethod 속성으로 히스트릭스에서 호출이 실패할 때 불러오는 클래스 메서드
//			fallbackMethod="findByFallback"
//			)
//	public OauthMember findByEmail(final String email) throws Exception {
//		log.info("findByEmail : {}", email);
//		testHystrix();
//		return oauthMemberRepository.findByEmail(email);
//	}

	@HystrixCommand(
			//히스트릭스를 사용자 정의하기 위해 추가 매개변수 전달
			commandProperties= {
					@HystrixProperty(
							//회로 차단기의 타임아웃 시간(단위 : 미리세컨)을 설정하는데 사용 프로퍼티
							name="execution.isolation.thread.timeoutInMilliseconds", value="3000"
							)
			},
			//fallbackMethod 속성으로 히스트릭스에서 호출이 실패할 때 불러오는 클래스 메서드
			fallbackMethod="findByFallback",
			//threadPoolKey 속성은 스레드 풀의 고유 이름 지정
			threadPoolKey="findByThreadPool",
			//threadPoolProperties 속성은 threadPool 동작을 정의하고 설정
			threadPoolProperties= {
					//coreSize 속성은 스레드 풀의 개수 정의
					@HystrixProperty(name="coreSize", value="30"),
					//스레드가 전부 job 수행 시 maxQueueSize는 스레드 풀 앞에 배치 할 큐와 큐에 넣을 요청 수 정의
					//maxQueueSize가 -1이면 SynchronousQueue 사용, 1 이상이면 LinkedBlockingQueue를 사용
					@HystrixProperty(name="maxQueueSize", value="10")
			}
			)
	public OauthMember findByEmail(final String email) throws Exception {
		log.info("findByEmail : {}", email);
		testHystrix();
		return oauthMemberRepository.findByEmail(email);
	}

	private OauthMember findByFallback(final String email) throws Exception {
		log.info("fallback email : {}", email);
		OauthMember oauthMember = new OauthMember();
		oauthMember.setEmail("fail@fail.com");
		return oauthMember;
	}
}
