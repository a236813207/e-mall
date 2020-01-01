package com.ken.mall.web.bind.response;


import com.ken.mall.pojo.base.BizCodeEnum;
import com.ken.mall.exception.codes.ErrorCode;

/**
 * @author Ken
 * @date 2019/4/22
 * @description
 */
public class ResBody {
    private Integer code;
    private String msg;
    private Object data;

    private ResBody() {
    }

    private ResBody(Integer code, String msg) {
        this(code, msg, null);
    }

    private ResBody(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResBody success() {
        ErrorCode bizCode = ErrorCode.OK;
        return new ResBody(bizCode.getCode(), bizCode.getMessage());
    }

    public static ResBody success(Object data) {
        return success().data(data);
    }

    public static ResBody failure() {
        ErrorCode bizCode = ErrorCode.FAIL;
        return new ResBody(bizCode.getCode(), bizCode.getMessage());
    }

    public static ResBody failure(String message) {
        ErrorCode bizCode = ErrorCode.FAIL;
        return new ResBody(bizCode.getCode(), message);
    }

    public static ResBody custom(int code, String msg) {
        return new ResBody(code, msg);
    }

    public ResBody message(String message) {
        this.msg = message;
        return this;
    }

    public ResBody code(Integer code) {
        this.code = code;
        return this;
    }

    public ResBody data(Object data) {
        this.data = data;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    public static ResBody custom(BizCodeEnum bizCodeEnum) {
        return custom(bizCodeEnum.getCode(), bizCodeEnum.getMessage());
    }
}
