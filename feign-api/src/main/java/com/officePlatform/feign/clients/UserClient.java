package com.officePlatform.feign.clients;


import com.officePlatform.feign.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user-server")
public interface UserClient {
    @GetMapping("/user/{id}")
    User findById(@PathVariable("id") int id);
}
