package com.ken.mall.web.admin.controller.login.resp;

/**
 * com.xfbetter.web.api.login.resp
 * author Daniel
 * 2017/12/14.
 */
public class ManagerInfoResp {
    private String username;
    private Integer id;

    public ManagerInfoResp(String username, Integer id) {
        this.username = username;
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
