package com.test.webclient;

import com.test.webclient.domain.PhoneBookData;
import com.test.webclient.service.PhoneBookDataService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Mono;

import java.util.List;

@SpringBootApplication
public class WebclientApplication {

	public static void main(String[] args) {

		SpringApplication.run(WebclientApplication.class, args);

		Mono<String> mono;

		PhoneBookDataService phoneBookDataService = new PhoneBookDataService();

		phoneBookDataService.getPhoneBookDataStringGET();

		phoneBookDataService.getPhoneBookDataArrayListGET();

		phoneBookDataService.getPhoneBookDataMapGET();

		phoneBookDataService.TESTA().subscribe(s -> System.out.println(s));
	}



}
