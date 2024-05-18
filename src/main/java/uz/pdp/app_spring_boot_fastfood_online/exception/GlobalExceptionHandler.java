package uz.pdp.app_spring_boot_fastfood_online.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.pdp.app_spring_boot_fastfood_online.payload.ApiResult;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(RestException.class)
    public ApiResult<?> restExceptionHandler(RestException restException) {
        return ApiResult.error(restException.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ApiResult<?> runtimeExceptionHandler(RuntimeException runtimeException) {
        return ApiResult.error(runtimeException.getMessage());
    }


}
