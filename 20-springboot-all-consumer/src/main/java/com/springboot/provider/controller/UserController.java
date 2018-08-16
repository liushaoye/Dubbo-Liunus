package com.springboot.provider.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.springboot.provider.service.UserService;
import com.springboot.provider.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/******************************
 * @author : liuyang
 * <p>ProjectName:Dubbo  </p>
 * @ClassName :  UserController
 * @date : 2018/8/15 0015
 * @time : 10:41
 * @createTime 2018-08-15 10:41
 * @version : 2.0
 * @description :
 *
 *
 *
 *******************************/


@Controller
public class UserController {

    /**
     * 引入远程连接,用reference
     */
    @Reference(version="2.6.0",timeout=100000)
    private UserService userService;

    @RequestMapping("/")
    public String index(Model model, @RequestParam(value = "currentPage", required = false) Integer currentPage) {

        if (null == currentPage) {
            currentPage = 1;
        }

        //每页固定十条数据
        int size = 10;

        // 总页
        int totalRows = userService.countUserTotal();

        //分页计算
        int totalPage = totalRows / size;

        //余数结果
        int left = totalRows % size;

        if (left > 0) {
            totalPage = totalPage + 1;
        }

        // 计算查询的开始行
        int page = (currentPage - 1) * totalPage;

        Map<String, Object> paramMap = new ConcurrentHashMap<>();

        paramMap.put("page", page);
        paramMap.put("size", size);

        List<User> userList = userService.findUserByPage(paramMap);

        model.addAttribute("userList", userList);

        return "index";
    }


}
