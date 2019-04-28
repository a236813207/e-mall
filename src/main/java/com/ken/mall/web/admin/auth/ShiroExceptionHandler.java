package com.ken.mall.web.admin.auth;

import com.ken.mall.pojo.exception.codes.ErrorCode;
import com.ken.mall.web.bind.response.ResBody;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * @author Ken
 * @date 2019/4/24
 * @description
 */
@ControllerAdvice
@ResponseBody
public class ShiroExceptionHandler {

    /**
     * shiro权限
     */
    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.OK)
    public  @ResponseBody
    ResBody authorizationException(NativeWebRequest request, Exception e){
        return ResBody.failure(ErrorCode.PERMISSION_DENIED.getMessage()).code(ErrorCode.PERMISSION_DENIED.getCode());
    }
}
