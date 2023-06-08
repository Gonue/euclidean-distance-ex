package com.template.server.domain.kakao.service;

import com.template.server.domain.kakao.dto.response.KakaoTotalResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class KakaoAddressSearchService {

    @Value("${kakao.rest.api.key}")
    private String kakaoRestApiKey;
    private final RestTemplate restTemplate;

    private static final String KAKAO_LOCAL_SEARCH_ADDRESS_URL = "https://dapi.kakao.com/v2/local/search/address.json";


    public KakaoAddressSearchService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public KakaoTotalResponse requestAddressSearch(String address){

        if(ObjectUtils.isEmpty(address)) return null;
        URI uri = createURI(address);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "KakaoAK" + kakaoRestApiKey);

        HttpEntity httpEntity = new HttpEntity<>(headers);
        return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, KakaoTotalResponse.class).getBody();
    }

    public URI createURI(String address){
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(KAKAO_LOCAL_SEARCH_ADDRESS_URL);
        uriComponentsBuilder.queryParam("query", address);
        return uriComponentsBuilder.build().encode().toUri();
    }
}
