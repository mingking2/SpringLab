package com.springtest.sync;

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

	@GetMapping("/process")
	public ResponseEntity<?> process(@RequestParam String request) throws InterruptedException {
		log.info("요청 처리 시작: {} - {}", request, Thread.currentThread().getName());
		Thread.sleep(3000); // 3초 동안 대기 (동기적 실행)
		log.info("요청 처리 완료: {} - {}", request, Thread.currentThread().getName());
		return ResponseEntity.ok("처리 완료! 요청: " + request);
	}
}
