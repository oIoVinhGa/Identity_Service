package com.BitzNomad.identity_service.repository.httpclient;

import com.BitzNomad.identity_service.DtoReponese.OutboundUserWithTokenReponese;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "outbound-user-client", url = "https://www.googleapis.com")
public interface OutboundUserClient {
    @GetMapping(value = "/oauth2/v1/userinfo")
    OutboundUserWithTokenReponese GetUserInfo(@RequestParam("alt") String code,
                                              @RequestParam("access_token") String accessToken
                                                );

}
