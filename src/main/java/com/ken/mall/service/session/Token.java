package com.ken.mall.service.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Ken
 * @date 2020/1/1
 * @description
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Token implements Serializable {

    private Long userId;
    private String sessionKey;
    private String sign;

}
