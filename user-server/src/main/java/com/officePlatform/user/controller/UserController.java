package com.officePlatform.user.controller;


import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.officePlatform.user.common.ResultUtil;
import com.officePlatform.user.entity.User;
import com.officePlatform.user.service.MailService;
import com.officePlatform.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cq
 * @since 2022-01-02
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;
    @PostMapping("/register")
    public Map<String,Object> createAccount(@RequestBody JSONObject jsonObject,HttpSession session){
        String confirmCode = jsonObject.get("confirmCode").toString();
        User user = jsonObject.toBean(User.class);
        if(!session.getAttribute(user.getEmail()+"confirmCode").equals(confirmCode)){
            return ResultUtil.fail(501,"验证码错误");
        }
        boolean res = userService.createAcount(user);
        if(res){
            return ResultUtil.succ("注册完成");
        }else{
            return ResultUtil.fail(500,"系统异常，请联系管理员");
        }
    }
    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody User user,HttpSession session){
        if(userService.login(user)) {
            session.setAttribute(user.getEmail()+"instance",user);
            return ResultUtil.succ("登陆成功");
        }else{
            return ResultUtil.fail(502,"密码错误");
        }
    }
    @PostMapping("/getConfirmCode")
    public Map<String,Object> sendConfirmCode(@RequestBody JSONObject jsonObject, HttpSession session){
        String email = jsonObject.get("email").toString();
        String confirmCode = RandomUtil.randomString(6);
        mailService.sendConfirmCode(email,confirmCode);
        session.setAttribute(email+"confirmCode",confirmCode);
        session.setMaxInactiveInterval(900);
        return ResultUtil.succ("发送成功");
    }
    @PostMapping("/updatePassword")
    public Map<String,Object>updatePassword(@RequestBody JSONObject jsonObject, HttpSession session){
        if(userService.updatePassword(jsonObject,session)){
            return ResultUtil.succ("修改成功");
        }else{
            return ResultUtil.fail(503,"修改失败，验证码错误");
        }
    }

    /**
     * 输入id
     * @param id
     * @return
     */
    @GetMapping("/getInfo")
    public Map<String,Object>getInfoById(int id,HttpSession session){
        User user = userService.getInfoById(id);
        User sessionUser = (User) session.getAttribute(user.getEmail() + "instance");
        if(sessionUser==null)return ResultUtil.fail(505,"查找失败请先登录");
        if(user==null){
            return ResultUtil.fail(504,"查无此人");
        }
        else{
            Map<String, Object> m = ResultUtil.succ("查找成功");
            m.put("user",user);
            return m;
        }
    }

    /**
     * 输入用户id，用户名，
     * @param user
     * @return
     */
    @PostMapping("/updateInfo")
    public Map<String,Object> updateInfo(@RequestBody User user,HttpSession session){
        if(userService.updateInfo(user,session)){
            return ResultUtil.succ("修改成功");
        }else{
            return ResultUtil.fail(506,"修改失败,请先登录");
        }
    }
    @GetMapping("/logout")
    public Map<String,Object> logout(int id,HttpSession session){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id",id);
        User user = userService.getOne(wrapper);
        session.removeAttribute(user.getEmail()+"instance");
        return ResultUtil.succ("注销成功");
    }
}

