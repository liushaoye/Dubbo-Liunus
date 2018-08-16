package com.springboot.provider.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.springboot.provider.mapper.UserMapper;
import com.springboot.provider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.springboot.provider.model.User;

import java.util.List;
import java.util.Map;

/******************************
 * @author : liuyang
 * <p>ProjectName:Dubbo  </p>
 * @ClassName :  UserServiceImpl
 * @date : 2018/8/14 0014
 * @time : 20:31
 * @createTime 2018-08-14 20:31
 * @version : 2.0
 * @description :
 *
 *
 *
 *******************************/

@Component
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> findUserByPage(Map<String, Object> paramPage) {
        return userMapper.selectUserByPage(paramPage);
    }

    @Override
    public Integer countUserTotal() {
        return userMapper.selectUserByCount();
    }
}
