package com.ken.mall.exception.codes;


import com.ken.mall.pojo.base.BizCodeEnum;

/**
 * @author Ken
 * @date 2017/12/6
 * @description
 */
public enum ErrorCode implements BizCodeEnum<ErrorCode> {
    /**
     * 错误码
     */
    PARAM_ERROR(1,"接口参数错误"),
    OK(2,"接口调用成功"),
    PERMISSION_EXPIRED(401,"权限授权过期"),
    PERMISSION_DENIED(403,"接口权限不足"),
    FAIL(5,"服务器繁忙"),
    DATE_NULL(6,"数据异常"),
    AUTH_FAIL(7,"账号密码错误"),
    ACCOUNT_NO_EXISTIS(8,"账号不存在"),
    ACCOUNT_LOCKED(9,"账号被冻结,请联系客服400-900-5879"),
    WALLET_BALANCE_NOT_ENOUGH(10,"钱包余额不足");

    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public int getStart() {
        return 0;
    }
}
