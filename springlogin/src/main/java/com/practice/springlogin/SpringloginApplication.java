package com.practice.springlogin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan
@SpringBootApplication
public class SpringloginApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringloginApplication.class, args);
	}

}
