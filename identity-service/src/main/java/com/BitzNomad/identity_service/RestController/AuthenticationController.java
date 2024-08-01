package com.BitzNomad.identity_service.RestController;

import com.BitzNomad.identity_service.DtoReponese.ApiResponse;
import com.BitzNomad.identity_service.DtoReponese.AuthenticationResponse;
import com.BitzNomad.identity_service.DtoReponese.IntrospecResponsee;
import com.BitzNomad.identity_service.DtoRequest.AuthenticationRequest;
import com.BitzNomad.identity_service.DtoRequest.IntrospecRequest;
import com.BitzNomad.identity_service.DtoRequest.LogoutRequest;
import com.BitzNomad.identity_service.DtoRequest.RefeshRequest;
import com.BitzNomad.identity_service.Service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level =  AccessLevel.PRIVATE , makeFinal = true)
public class AuthenticationController {


    AuthenticationService   authenticationService;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse>  authenticate(@RequestBody AuthenticationRequest request) {

        AuthenticationResponse result = authenticationService.authenticate(request);
        return  ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/outbound/authentication")
    ApiResponse<AuthenticationResponse> outboundAuthenticate(@RequestParam("code") String code) {
        var result = authenticationService.outboundAuthenticate(code);
        return ApiResponse.<AuthenticationResponse>builder().result(result).build();
    }

    @PostMapping("/refesh")
    ApiResponse<AuthenticationResponse>  authenticate(@RequestBody RefeshRequest request) throws ParseException, JOSEException {
        AuthenticationResponse result = authenticationService.refeshToken(request);
        return  ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/logout")
    ApiResponse<Void>  logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {

        authenticationService.logout(request);
        return  ApiResponse.<Void>builder()
                .build();
    }
    @PostMapping("/instrospec")
    ApiResponse<IntrospecResponsee>  authenticate(@RequestBody IntrospecRequest request) throws ParseException, JOSEException {

        IntrospecResponsee result = authenticationService.Instropec(request);
        return  ApiResponse.<IntrospecResponsee>builder()
                .status(201)
                .result(result)
                .build();
    }

}
