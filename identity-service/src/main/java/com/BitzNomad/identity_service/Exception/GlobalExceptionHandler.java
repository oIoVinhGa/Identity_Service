package com.BitzNomad.identity_service.Exception;

import com.BitzNomad.identity_service.DtoReponese.ApiResponse;
import jakarta.validation.ConstraintViolation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.Objects;


@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String MIN_ATTRIBUTE = "min";
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    ResponseEntity<ApiResponse> handlingException(Exception e) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
        apiResponse.setStatus(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException e) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(e.getMessage());
        apiResponse.setStatus(1001);
        return ResponseEntity.badRequest().body(apiResponse);
    }


    //Access Denied ko co quyen truy cap API
    @ExceptionHandler(AccessDeniedException.class)
    ResponseEntity<ApiResponse> handlingAccessDeniedException(AccessDeniedException exception) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(ApiResponse.builder()
                        .status(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build());
    }

    @ExceptionHandler(AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException e) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(e.getErrorCode().getMessage());
        apiResponse.setStatus(e.getErrorCode().getCode());
        return ResponseEntity.badRequest().body(apiResponse);
    }


    //Exception xu ly message Anomation Validator
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingValidationException(MethodArgumentNotValidException e) {
        //Lay enumkey duoc truyen tu message @DobConstraint(min = 2,message = "INVALID_DOB") -> enumkey = INVALID_DOB
        String enumkey = e.getFieldError().getDefaultMessage();

        ErrorCode errorCode = ErrorCode.INVALID_KEY;
        Map<String, Object> attributes = null;

        try {
            //Find ErrorCode by enumkey value
            errorCode = ErrorCode.valueOf(enumkey);

            var constraintViolations = e.getBindingResult().getAllErrors().get(0).unwrap(ConstraintViolation.class);

            //lay ra gia tri cua annotion
             attributes = constraintViolations.getConstraintDescriptor().getAttributes();


            log.info(attributes.toString());
            //build ApiReponse thong bao loi cho client


        }catch (IllegalArgumentException err) {

        }
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(errorCode.getCode());
        apiResponse.setMessage(
                Objects.nonNull(attributes)
                        ? mapAttribute(errorCode.getMessage(), attributes)
                        : errorCode.getMessage()
        );
        return ResponseEntity.badRequest().body(apiResponse);
    }

    //Ham xu ly custom Message ErroCode
    private String mapAttribute(String message, Map<String, Object> attributes) {
        String minValue = String.valueOf(attributes.get(MIN_ATTRIBUTE));

        return message.replace("{" + MIN_ATTRIBUTE + "}", minValue);
    }
}
