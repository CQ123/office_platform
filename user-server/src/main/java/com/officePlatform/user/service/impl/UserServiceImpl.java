package com.officePlatform.user.service.impl;

import com.officePlatform.user.entity.User;
import com.officePlatform.user.mapper.UserMapper;
import com.officePlatform.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cq
 * @since 2021-12-31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public boolean sendCode(String email) {

        return false;
    }
}
