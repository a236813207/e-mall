package com.ken.mall.entity.rbac.enum_;

/**
 * @author Ken
 * @date 2019/4/24
 * @description
 */
public enum ResourceType {
    menu("菜单"),button("按钮");

    private final String info;

    private ResourceType(String info){
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return this.info;
    }
}
