package com.officePlatform.user.service;

import com.officePlatform.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cq
 * @since 2021-12-31
 */
public interface UserService extends IService<User> {
    boolean sendCode(String email);
}
