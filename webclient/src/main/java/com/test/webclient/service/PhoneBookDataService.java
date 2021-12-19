package com.test.webclient.service;

import com.test.webclient.domain.PhoneBookData;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PhoneBookDataService {

    private WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:8080/phonebook")
            .build();


    public String getPhoneBookDataStringGET() {
        webClient.get()
                .uri("/phoneBookData/string")
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(s -> System.out.println(s));

        return null;
    }

    public List<PhoneBookData> getPhoneBookDataArrayListGET() {
        System.out.println("getPhoneBookDataArrayListGET");
        List<PhoneBookData> list = new ArrayList<>();

        webClient.get()
                .uri("/phoneBookData/list")
                .retrieve()
                .bodyToFlux(PhoneBookData.class)
                .subscribe(s -> {
                    System.out.println(s);
                });

        return list;
    }

    public Map<String, PhoneBookData> getPhoneBookDataMapGET() {
        System.out.println("getPhoneBookDataMapGET");
        Map<String, PhoneBookData> map = new HashMap<>();

        webClient.get()
                .uri("/phoneBookData/map")
                .retrieve()
                .bodyToFlux(new ParameterizedTypeReference<Map<String, PhoneBookData>>() {})
                .subscribe(s -> {
                    System.out.println(s);
                });

        return map;
    }

    public Flux<PhoneBookData> TESTA() {
        return webClient.get()
                .uri("/phoneBookData/list")
                .retrieve()
                .bodyToFlux(PhoneBookData.class);
    }

}
