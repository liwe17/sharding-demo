package com.turing.sharding.jdbc.rw;

import com.turing.sharding.jdbc.rw.entity.User;
import com.turing.sharding.jdbc.rw.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class ShardingJdbcRwTest {

    @Resource
    private UserMapper userMapper;

    /**
     * 写入数据测试
     */
    @Test
    public void testInsert() {
        User user = new User();
        user.setUname("王铁锤");
        userMapper.insert(user);
    }


    /**
     * 读取查询
     */
    @Test
    public void testQuery(){
        List<User> users = userMapper.selectList(null);
        users.forEach(System.err::println);
    }

    /**
     * 事务测试,读写在主库
     */
    @Test
    @Transactional
    public void testInsertQuery(){
        testInsert();
        testQuery();
    }

    /**
     * 负载均衡查询
     */
    @Test
    public void algQuery(){
        List<User> users = userMapper.selectList(null);
        List<User> users2 = userMapper.selectList(null);
        List<User> users3 = userMapper.selectList(null);
        List<User> users4 = userMapper.selectList(null);
    }
}
