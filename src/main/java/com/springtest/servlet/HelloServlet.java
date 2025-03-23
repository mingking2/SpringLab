package com.springtest.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(name = "HelloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

	public HelloServlet() {
		log.info("HelloServlet 생성자 호출됨 (서블릿 객체 생성)");
	}

	@Override
	public void init() {
		log.info("HelloServlet init() 호출됨 (서블릿 초기화)");
	}

	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		log.info("🔄 service() 호출됨 - 요청 메서드: {}", req.getMethod());
		super.service(req, resp); // 꼭 super 호출해야 doGet() 등으로 연결됨!
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info("doGet() 호출됨 - 처리 스레드: {}", Thread.currentThread().getName());

		String name = request.getParameter("name");
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("Hello, " + name);
	}
}
