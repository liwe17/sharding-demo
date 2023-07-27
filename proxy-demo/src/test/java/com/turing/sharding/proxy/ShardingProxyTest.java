package com.turing.sharding.proxy;

import com.turing.sharding.proxy.entity.User;
import com.turing.sharding.proxy.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class ShardingProxyTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testQueryUser() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.err::println);
    }


}
