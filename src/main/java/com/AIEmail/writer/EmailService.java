package com.AIEmail.writer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.rmi.server.ExportException;
import java.util.Map;
import java.util.Objects;

@Service

public class EmailService {

    private final WebClient webClient;
    @Value("${gemini.api.url}")
    private String geminiapiUrl;
    @Value("${gemini.api.key}")
    private String geminiapiKey;

    public EmailService(WebClient.Builder webClientbuilder) {
        this.webClient = webClientbuilder.build();
    }

    public String generateEmailReply(EmailReq emailReq){
        //build prompt
        String prompt= buildprompt(emailReq);
        //craft a requet
        Map<String, Object> reqBody=Map.of(
                "contents",new Object[]{
                  Map.of("parts", new Object[]{
                          Map.of("text",prompt)
                  })
                }
        );
        //do request and get response
        String response = webClient.post()
                .uri(geminiapiUrl + geminiapiKey)
                .header("Content-Type", "application/json")
                .bodyValue(reqBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();




        //return resoposne
        return extractResponseContent(response);
    }

    private String extractResponseContent(String response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootnode = mapper.readTree(response);
            return rootnode
                    .path("candidates").get(0)
                    .path("content")
                    .path("parts").get(0)
                    .path("text").asText();
        } catch (Exception e) {
            return "Error";
        }
    }
    private String buildprompt(EmailReq emailReq) {
        StringBuilder prompt= new StringBuilder();
        prompt.append("Generate a professional email rply for the following email content. please do not generate a subject line");
        if(emailReq.getTone()!=null && emailReq.getTone().isEmpty()){
            prompt.append("Use a").append(emailReq.getTone()).append("tone");

        }
        prompt.append("\nOrignal Email: \n").append(emailReq.getEmailContent());
        return prompt.toString();
    }
}
