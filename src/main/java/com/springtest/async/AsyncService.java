package com.springtest.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AsyncService {

	@Async("customTaskExecutor")
	public void asyncMethod() {
		String threadName = Thread.currentThread().getName();
		log.info("[비동기 실행] 현재 실행 중인 스레드: {}", threadName);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		log.info("Async 작업 완료");
	}

}
