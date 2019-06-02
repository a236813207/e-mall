package com.ken.mall.web.admin.controller.login.resp;

/**
 * com.xfbetter.web.api.login.resp
 * author ken
 * 2018/04/24.
 */
public class ManagerInfoResp {
    private String username;
    private Long id;

    public ManagerInfoResp(String username, Long id) {
        this.username = username;
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
