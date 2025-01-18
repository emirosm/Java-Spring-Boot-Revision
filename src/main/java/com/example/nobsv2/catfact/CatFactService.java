package com.example.nobsv2.catfact;

import com.example.nobsv2.Query;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class CatFactService implements Query<Integer, CatFactDTO> {
    private final RestTemplate restTemplate;
    private final String url = "https://catfact.ninja/fact";
    private final String MAX_LENGTH = "max_length";

    public CatFactService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<CatFactDTO> execute(Integer input) {
        URI uri = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam(MAX_LENGTH, input)
                .build()
                .toUri();

        HttpHeaders header = new HttpHeaders();
        header.add("Accept", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(header);

        try{
            ResponseEntity<CatFactResponse> response = restTemplate.exchange(uri, org.springframework.http.HttpMethod.GET, entity, CatFactResponse.class);
            CatFactDTO catFactDTO = new CatFactDTO(response.getBody().getFact());
            return ResponseEntity.ok(catFactDTO);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get cat fact");
        }
    }
}
