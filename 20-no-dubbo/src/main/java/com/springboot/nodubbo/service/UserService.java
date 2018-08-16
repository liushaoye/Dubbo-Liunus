package com.springboot.nodubbo.service;

import com.springboot.nodubbo.model.User;

import java.util.List;
import java.util.Map;

/******************************
 * @author : liuyang
 * <p>ProjectName:Dubbo  </p>
 * @ClassName :  UserService
 * @date : 2018/8/14 0014
 * @time : 20:11
 * @createTime 2018-08-14 20:11
 * @version : 2.0
 * @description :
 *
 *
 *
 *******************************/

public interface UserService {

    /**
     * 分页查询用户
     *
     * @param paramPage
     * @return
     */
    List<User> findUserByPage(Map<String, Object> paramPage);


    /**
     * 查询用户总数
     *
     * @return
     */
    Integer countUserTotal();


    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    Integer addUser(User user);


    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    Integer updateUser(User user);


    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    Integer deletedUser(Integer id);

    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    User selectByPrimaryKey(Integer id);

}
