package com.officePlatform.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.officePlatform.user.common.ResultObj;
import com.officePlatform.user.entity.User;
import com.officePlatform.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cq
 * @since 2021-12-31
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getCode/{email}")
    public ResultObj getCode(@PathVariable("email") String email)
    {
        boolean bo = userService.sendCode(email);
        if(bo)
        {
            return ResultObj.succ("发送成功！");
        }
        else{
            return ResultObj.fail(401,"验证码发送失败！");
        }
    }

    @PostMapping("/register")
    public ResultObj register(User user){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("email",user.getEmail());
        User user1 = userService.getOne(wrapper);
        if(user1==null){
            try {
                if(userService.save(user)) {
                    return ResultObj.succ("注册成功!");
                }else{
                    return ResultObj.fail(402,"注册失败！");
                }
            } catch (Exception e) {
                return ResultObj.fail(402,"注册失败！");
            }
        }else {
            return ResultObj.fail(403, "邮箱已被注册!");
        }
    }

}

