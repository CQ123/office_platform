package com.officePlatform.user.service;

import cn.hutool.json.JSONObject;
import com.officePlatform.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.officePlatform.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cq
 * @since 2022-01-02
 */
public interface UserService extends IService<User> {

    boolean createAcount(User user);
    boolean login(User user);
    int activateAcount(String token);
    boolean updatePassword(JSONObject jsonObject, HttpSession session);
    User getInfoById(int id);
    boolean updateInfo(User user);
}
