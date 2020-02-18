package com.szymon.application.externalAPI.webclient;

import com.szymon.application.model.Example;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class WebClientExampleController {

    private final WebClient webClient;

    @Autowired
    public WebClientExampleController(WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping(value = "/weather")
    public List<Example> getWeatherLondon() {
//        WebClient client1 = WebClient.create("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");


        List<Example> result = webClient.get().uri("http://localhost:9999/show").retrieve().bodyToFlux(Example.class).collectList().block();

        System.out.println(result);

        return result;
    }
}
