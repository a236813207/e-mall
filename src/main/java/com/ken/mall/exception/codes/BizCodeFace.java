package com.ken.mall.exception.codes;


import com.ken.mall.pojo.base.BizCodeEnum;

import java.io.Serializable;

/**
 * 错误码Code接口
 * @author Ken
 * @date 2019/4/22
 * @description
 */
public class BizCodeFace{

    private BizCodeFace(){}

    public static BizCode createBizCode(BizCodeEnum code){
        return new BizCode(code.getCode(),code.getMessage());
    }

    public static BizCode createBizCode(Integer code,String message){
        return new BizCode(code,message);
    }

    public static class BizCode implements Serializable{
        private Integer code;
        private String message;

        private BizCode(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public BizCode message(String message){
            this.message = message;
            return this;
        }

        public BizCode code(Integer code){
            this.code = code;
            return this;
        }
    }
}
