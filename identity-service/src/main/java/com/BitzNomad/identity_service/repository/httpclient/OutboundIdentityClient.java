package com.BitzNomad.identity_service.repository.httpclient;


import com.BitzNomad.identity_service.DtoReponese.ExchangeTokenReponese;
import com.BitzNomad.identity_service.DtoRequest.ExchangeTokenRequest;
import feign.QueryMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "outbound-identity", url = "https://oauth2.googleapis.com")
public interface OutboundIdentityClient {
    @PostMapping(value = "/token", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ExchangeTokenReponese exchangeToken(@QueryMap ExchangeTokenRequest request);
}
