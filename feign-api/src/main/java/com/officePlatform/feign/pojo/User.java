package com.officePlatform.feign.pojo;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class User {
    private Integer id;
    private String name;
    private String password;
    private String salt;
    private String email;
    private String token;
    private Integer level;
    private LocalDateTime createTime;
    private LocalDateTime activationTime;
}
