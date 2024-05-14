package uz.pdp.app_spring_boot_fastfood_online.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public  class ApiResult<T> {

    private T data;

    private boolean success;

    private String errorMsg;

    private List<FieldErrorDTO> fieldErrors;

    public static<T> ApiResult<T> success(T data){
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setSuccess(true);
        apiResult.setData(data);
        return apiResult;
    }

    public static<T> ApiResult<T> error(String errorMsg){
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setSuccess(false);
        apiResult.setErrorMsg(errorMsg);
        return apiResult;
    }

    public static<T> ApiResult<T> error(List<FieldErrorDTO> fieldErrors){
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setSuccess(false);
        apiResult.setFieldErrors(fieldErrors);
        return apiResult;
    }



}
