package com.news.snapNews.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;


@Service
public class HuggingFaceSummaryService {

    private final WebClient webClient;

    public HuggingFaceSummaryService(@Value("${huggingface.api-key}") String apiKey) {
        this.webClient = WebClient.builder()
                .baseUrl("https://api-inference.huggingface.co/meodels/knkarthick/MEETING_SUMMARY")
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .defaultHeader("content-Type", "application/json")
                .build();
    }

    public String summarize(String content) {
        Map<String, String> request = Map.of("inputs", content);

        return webClient.post()
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String.class)
                .onErrorReturn("[요약 실패]")
                .block();
    }
}
