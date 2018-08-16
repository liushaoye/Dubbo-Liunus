package com.springboot.nodubbo.controller;

import com.springboot.nodubbo.model.User;
import com.springboot.nodubbo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index(Model model, @RequestParam(value = "currentPage", required = false) Integer currentPage) {

        if (null == currentPage || currentPage < 1) {
            currentPage = 1;
        }

        //每页固定十条数据
        int size = 5;

        // 总页
        int totalRows = userService.countUserTotal();

//        int totalRows = 100;

        //分页计算
        int totalPage = totalRows / size;

        //余数结果
        int left = totalRows % size;

        if (left > 0) {
            totalPage = totalPage + 1;
        }

        if (currentPage > totalPage) {
            currentPage = totalPage;
        }

        // 计算查询的开始行
        int page = (currentPage - 1) * totalPage;

        Map<String, Object> paramMap = new ConcurrentHashMap<>();

        paramMap.put("page", page);
        paramMap.put("size", size);

        List<User> userList = userService.findUserByPage(paramMap);

        model.addAttribute("userList", userList);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", totalPage);

        return "index";
    }

    /**
     * 跳转添加
     *
     * @return
     */
    @RequestMapping("/user/toAddUser")
    public String toAddUser() {
        return "addUser";
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @RequestMapping("/user/addUser")
    public String addUser(User user) {

        if (null == user.getId()) {
            userService.addUser(user);
        } else {
            userService.updateUser(user);
        }

        return "redirect:/index";
    }

    @RequestMapping("/user/toUpdateUser")
    public String toUpdateUser(Model model, @RequestParam(value = "id") Integer id) {

        User user = userService.selectByPrimaryKey(id);

        model.addAttribute("user", user);

        return "addUser";
    }

    @RequestMapping("/user/deletedUser")
    public String deletedUser(@RequestParam(value = "id") Integer id) {

        userService.deletedUser(id);

        return "redirect:/index";
    }

}
