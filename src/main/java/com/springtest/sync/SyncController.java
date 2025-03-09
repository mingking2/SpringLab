package com.springtest.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/sync")
@Log4j2
public class SyncController {

	private final ExecutorService executor = Executors.newSingleThreadExecutor(); // 단일 스레드 실행

	/*
	 * 단일 스레드로 요청을 동기적으로 받도록 테스트
	 * */
	@GetMapping("/sync-process")
	public ResponseEntity<?> processSync(@RequestParam String request) {
		executor.submit(() -> {
			try {
				log.info("요청 처리 시작: {} - {}", request, Thread.currentThread().getName());
				Thread.sleep(3000); // 3초 대기
				log.info("요청 처리 완료: {} - {}", request, Thread.currentThread().getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		return ResponseEntity.ok("처리 완료! 요청: " + request);
	}

	/*
	 * 요청을 동시에 받고 스프링 내부에서 동기적으로 동작하는지 테스트
	 * */
	@GetMapping("/process")
	public ResponseEntity<?> process(@RequestParam String request) throws InterruptedException {
		log.info("요청 처리 시작: {} - {}", request, Thread.currentThread().getName());
		Thread.sleep(3000); // 3초 동안 대기 (동기적 실행)
		log.info("요청 처리 완료: {} - {}", request, Thread.currentThread().getName());
		return ResponseEntity.ok("처리 완료! 요청: " + request);
	}
}
