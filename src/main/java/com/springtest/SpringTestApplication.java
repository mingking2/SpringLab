package com.springtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.springtest.servlet.HelloServlet;

@SpringBootApplication
public class SpringTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTestApplication.class, args);
	}

	// 내장 톰캣에 서블릿 등록
	@Bean
	public ServletRegistrationBean<HelloServlet> helloServlet() {
		ServletRegistrationBean<HelloServlet> servletBean =
			new ServletRegistrationBean<>(new HelloServlet(), "/hello");
		return servletBean;
	}
}
