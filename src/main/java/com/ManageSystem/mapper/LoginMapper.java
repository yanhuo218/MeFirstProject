package com.ManageSystem.mapper;

import com.ManageSystem.pojo.DetailData;
import com.ManageSystem.pojo.User;

public interface LoginMapper {
    /**
     * 查询已注册人数
     */
    Integer selectMaxUser();

    /**
     * 按账号密码查询
     */
    User loginUserPass(User user);

    /**
     * 按账号查询
     */
    User loginUser(User user);

    /**
     * 注册账号
     */
    void addUser(User user);

    void addData(DetailData data);

    /*
     *查询以detaildata查询详细信息id
     */
    Integer selectId(User user);

    /*
     *查询以detaildata查询详细信息id
     */
    DetailData selectData(Integer id);

    void updataUser(DetailData data);
}
