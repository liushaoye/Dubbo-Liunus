package com.springboot.nodubbo.serviceImpl;

import com.springboot.nodubbo.mapper.UserMapper;
import com.springboot.nodubbo.model.User;
import com.springboot.nodubbo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

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

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 注入redisTemplate,存入类只能是<String,String>和<Object,Object>
     */
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;


    @Override
    public List<User> findUserByPage(Map<String, Object> paramPage) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        List<User> userList = (List<User>) redisTemplate.opsForValue().get("getUserList");

        if (null == userList) {

            synchronized (this) {
                userList = (List<User>) redisTemplate.opsForValue().get("getUserList");
                if (null == userList) {
                    userList = userMapper.selectUserByPage(paramPage);

                    redisTemplate.opsForValue().set("getUserList", userList);
                }

            }
        }

        return userList;
    }

    @Override
    public Integer countUserTotal() {

        redisTemplate.setKeySerializer(new StringRedisSerializer());


        Integer totalRows = (Integer) redisTemplate.opsForValue().get("totalRows");

        if (null == totalRows) {

            synchronized (this) {
                totalRows = (Integer) redisTemplate.opsForValue().get("totalRows");
                if (null == totalRows) {
                    totalRows = userMapper.selectUserByCount();

                    redisTemplate.opsForValue().set("totalRows", totalRows);
                }

            }
        }

        return totalRows;
    }

    @Override
    public Integer addUser(User user) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        int count = userMapper.insertSelective(user);

        if (count > 0) {
            //更新缓存的总数
            int totalRows = userMapper.selectUserByCount();

            redisTemplate.opsForValue().set("totalRows", totalRows);
        }

        return count;
    }

    @Override
    public Integer deletedUser(Integer id) {

        redisTemplate.setKeySerializer(new StringRedisSerializer());

        int count = userMapper.deleteByPrimaryKey(id);

        if (count > 0) {
            //更新缓存的总数
            int totalRows = userMapper.selectUserByCount();

            redisTemplate.opsForValue().set("totalRows", totalRows);
        }

        return count;
    }

    @Override
    public Integer updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        User user = (User) redisTemplate.opsForValue().get("getUser");

        if (null == user) {

            synchronized (this) {
                user = (User) redisTemplate.opsForValue().get("getUser");
                if (null == user) {
                    user = userMapper.selectByPrimaryKey(id);

                    redisTemplate.opsForValue().set("getUser", user);
                }

            }
        }

        return user;
    }

}
