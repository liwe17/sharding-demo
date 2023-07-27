package com.turing.sharding.jdbc.rw;

import com.turing.sharding.jdbc.vertical.entity.Order;
import com.turing.sharding.jdbc.vertical.entity.User;
import com.turing.sharding.jdbc.vertical.mapper.OrderMapper;
import com.turing.sharding.jdbc.vertical.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class ShardingJdbcVerticalTest {

    @Resource
    private UserMapper userMapper;

    @Resource
    private OrderMapper orderMapper;

    @Test
    public void testInsertOrderAndUser() {
        User user = new User();
        user.setUname("强哥");
        userMapper.insert(user);

        Order order = new Order();
        order.setOrderNo("NO_1");
        order.setUserId(user.getId());
        orderMapper.insert(order);
    }

    @Test
    public void testQueryFromOrderAndUser(){
        List<User> users = userMapper.selectList(null);
        List<Order> orders = orderMapper.selectList(null);
        users.forEach(System.out::println);
        orders.forEach(System.out::println);
    }

}
