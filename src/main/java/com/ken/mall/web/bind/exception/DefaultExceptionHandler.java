package com.ken.mall.web.bind.exception;

import com.ken.mall.web.bind.response.ResBody;
import com.wwbetter.service.pojo.exception.BizException;
import com.wwbetter.service.pojo.exception.codes.BizCodeFace;
import com.wwbetter.service.pojo.exception.codes.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * 全局异常处理
 * 认证未通过异常、业务相关异常，未处理的其他异常
 *
 */
@ControllerAdvice
@ResponseBody
public class DefaultExceptionHandler {

    public static final Logger LOGGER = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    /**
     * 业务相关错误
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResBody processBizException(NativeWebRequest request, BizException e) {
        BizCodeFace.BizCode bizCode = e.getBizCode();
        LOGGER.warn(bizCode.getMessage(), e);
        return ResBody.failure(bizCode.getMessage()).code(bizCode.getCode());
    }

    /**
     * 校验错误
     */
    @ExceptionHandler(BindException.class)
    public @ResponseBody
    ResBody processBindException(NativeWebRequest request, BindException e) {
        FieldError fieldError = e.getFieldError();
        LOGGER.warn("数据校验失败:" + fieldError.getField() + "[" + fieldError.getDefaultMessage() + "]", e);
        return ResBody.failure(fieldError.getDefaultMessage()).code(ErrorCode.PARAM_ERROR.getCode());
    }

    /**
     * 校验错误
     */
    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResBody processIllegalArgumentException(NativeWebRequest request, Exception e) {
        LOGGER.warn("数据校验失败错误", e);
        return ResBody.failure(e.getMessage()).code(ErrorCode.PARAM_ERROR.getCode());
    }

    /**
     * 内部服务器错误
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResBody processException(NativeWebRequest request, Throwable e) {
        LOGGER.error("服务器错误", e);
        return ResBody.failure();
    }
}
