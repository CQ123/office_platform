package com.officePlatform.user.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.officePlatform.user.entity.User;
import com.officePlatform.user.mapper.UserMapper;
import com.officePlatform.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cq
 * @since 2022-01-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public boolean createAcount(User user) {
        //String confirmCode = IdUtil.getSnowflake(1,1).nextIdStr();
        String salt = RandomUtil.randomString(6);
        String md5Pwd = SecureUtil.md5(user.getPassword()+salt);
        LocalDateTime ldt = LocalDateTime.now().plusDays(1);
        user.setSalt(salt);
        user.setPassword(md5Pwd);
        //user.setToken(confirmCode);
        user.setActivationTime(ldt);
        user.setLevel(1);
        return userMapper.insert(user)==1;
    }

    @Override
    public boolean login(User user) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("email",user.getEmail());
        User oldUser = userMapper.selectOne(wrapper);
        if(oldUser==null)return false;
        String md5Pwd = SecureUtil.md5(user.getPassword()+oldUser.getSalt());
        if(md5Pwd.equals(oldUser.getPassword())){
            return true;
        }
        else {
            return false;
        }

    }

    @Override
    public int activateAcount(String token) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("token",token);
        User user= userMapper.selectOne(wrapper);
        if(user==null||user.getToken()==null){
            return 1;
        }
        if(LocalDateTime.now().isBefore(user.getActivationTime())){
            //user.setIsValid(true);
            user.setToken(null);
            userMapper.update(user,wrapper);
        }else{
            return 2;
        }
        return 0;
    }

    @Override
    public boolean updatePassword(JSONObject jsonObject, HttpSession session) {
        String email = jsonObject.get("email").toString();
        String confirmCode = jsonObject.get("confirmCode").toString();
        String password = jsonObject.get("password").toString();
        if(session.getAttribute(email).toString().equals(confirmCode)){
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("email",email);
            User oldUser= userMapper.selectOne(wrapper);
            if(oldUser==null)return false;
            String md5Pwd = SecureUtil.md5(password+oldUser.getSalt());
            oldUser.setPassword(md5Pwd);
            userMapper.update(oldUser,wrapper);
            return true;
        }
        return false;
    }

    @Override
    public User getInfoById(int id) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id",id);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public boolean updateInfo(User user) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id",user.getId());
        User oldUser = userMapper.selectOne(wrapper);
        oldUser.setName((user.getName()));
        userMapper.update(oldUser,wrapper);
        return true;
    }

}
