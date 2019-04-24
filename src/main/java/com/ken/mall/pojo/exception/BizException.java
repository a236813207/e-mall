package com.ken.mall.pojo.exception;


import com.ken.mall.pojo.exception.codes.BizCodeFace;

/**
 * 业务类异常
 * com.xfbetter.common.exception
 * author Daniel
 * 2017/12/6.
 */
public class BizException extends RuntimeException {

    private BizCodeFace.BizCode bizCode;

    public BizException(BizCodeFace.BizCode bizCode) {
        super(bizCode.getMessage());
        this.bizCode = bizCode;
    }

    public BizException() {
    }

    public BizCodeFace.BizCode getBizCode() {
        return bizCode;
    }

    public void setBizCode(BizCodeFace.BizCode bizCode) {
        this.bizCode = bizCode;
    }
}
