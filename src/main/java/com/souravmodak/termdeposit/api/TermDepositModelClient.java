package com.souravmodak.termdeposit.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TermDepositModelClient {

    private final String termDepositBaseUrl;

    private final RestTemplate restTemplate;

    public TermDepositModelClient(@Value("${flask.base-url}") String termDepositBaseUrl) {
        this.termDepositBaseUrl = termDepositBaseUrl;
        this.restTemplate = new RestTemplate();
    }

    public Object makePrediction(Object requestData) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(requestData, headers);

        String predictUrl = termDepositBaseUrl + "/get_prediction";
        ResponseEntity<Object> response = restTemplate.postForEntity(predictUrl, requestEntity, Object.class);

        return response.getBody();
    }

    public Object trainModel(){
        String predictUrl = termDepositBaseUrl + "/train_model";
        ResponseEntity<Object> response = restTemplate.getForEntity(predictUrl, Object.class);
        return response.getBody();
    }

    public Object deleteModel(){
        String predictUrl = termDepositBaseUrl + "/delete_model";
        ResponseEntity<Object> response = restTemplate.getForEntity(predictUrl, Object.class);
        return response.getBody();
    }
}
