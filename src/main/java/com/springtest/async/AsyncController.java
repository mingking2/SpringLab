package com.springtest.async;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AsyncController {

	private final AsyncService asyncService;

	@GetMapping("/sync")
	public String syncRequest() {
		String threadName = Thread.currentThread().getName();
		log.info("Sync 요청 처리 스레드: {}", threadName);
		return "Sync Response";
	}

	@Async
	@GetMapping("/async")
	public CompletableFuture<String> asyncRequest() {
		String threadName = Thread.currentThread().getName();
		log.info("Async 요청 처리 스레드: {}", threadName);
		return CompletableFuture.completedFuture("Async Response");
	}

	@GetMapping("/async-test")
	public ResponseEntity<?> asyncCall() {
		String threadName = Thread.currentThread().getName();
		log.info("[요청 수신] 현재 실행 중인 스레드: {}", threadName);

		asyncService.asyncMethod();
		return ResponseEntity.ok("응답반환");
	}
}
