package com.turing.sharding.jdbc.horizontal;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.turing.sharding.jdbc.horizontal.entity.Dict;
import com.turing.sharding.jdbc.horizontal.entity.Order;
import com.turing.sharding.jdbc.horizontal.entity.OrderItem;
import com.turing.sharding.jdbc.horizontal.mapper.DictMapper;
import com.turing.sharding.jdbc.horizontal.mapper.OrderItemMapper;
import com.turing.sharding.jdbc.horizontal.mapper.OrderMapper;
import com.turing.sharding.jdbc.horizontal.mapper.UserMapper;
import com.turing.sharding.jdbc.horizontal.vo.OrderVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.LongStream;

@SpringBootTest
public class ShardingJdbcHorizontalTest {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private OrderItemMapper orderItemMapper;

    @Resource
    private DictMapper dictMapper;

    @Test
    public void testInsertOrder() {
        Order order = new Order();
        order.setOrderNo("NO_2");
        order.setUserId(1L);
        order.setAmount(BigDecimal.ONE);
        orderMapper.insert(order);
    }

    /**
     * 分库插入测试
     */
    @Test
    public void testInsertOrderDatabaseStrategy() {
        LongStream.iterate(5, i -> i + 1).limit(4).forEach(i -> {
            Order order = new Order();
            order.setOrderNo("NO_2");
            order.setUserId(i);
            order.setAmount(BigDecimal.ONE);
            orderMapper.insert(order);
        });
    }

    /**
     * 分表插入测试
     */
    @Test
    public void testInsertOrderTableStrategy() {
        LongStream.iterate(101, i -> i + 1).limit(4).forEach(i -> {
            Order order = new Order();
            order.setOrderNo("NO_" + i);
            order.setUserId(1L);
            order.setAmount(BigDecimal.ONE);
            orderMapper.insert(order);
            int hashMod = order.getOrderNo().hashCode() % 2;
            System.err.println(hashMod);
        });
    }

    @Test
    public void testInsertOrderStrategy() {
        LongStream.iterate(1, i -> i + 1).limit(4).forEach(i -> {
            Order order = new Order();
            order.setOrderNo("NO_" + i);
            order.setUserId(1L);
            order.setAmount(BigDecimal.ONE);
            orderMapper.insert(order);
        });

        LongStream.iterate(5, i -> i + 1).limit(4).forEach(i -> {
            Order order = new Order();
            order.setOrderNo("NO_" + i);
            order.setUserId(2L);
            order.setAmount(BigDecimal.ONE);
            orderMapper.insert(order);
        });
    }

    /**
     * 水平分片,测试查询
     */
    @Test
    public void testShardingQueryAll() {
        List<Order> orders = orderMapper.selectList(null);
        orders.forEach(System.err::println);
    }


    @Test
    public void testShardingQueryByUserId() {
        List<Order> orders = orderMapper.selectList(Wrappers.<Order>lambdaQuery().eq(Order::getUserId, 1L));
        orders.forEach(System.err::println);
    }

    /**
     * 测试关联表插入
     */
    @Test
    public void testInsertOrderAndItem() {

        for (int i = 1; i < 5; i++) {
            Order order = new Order();
            order.setOrderNo("NO_" + i);
            order.setUserId(1L);
            orderMapper.insert(order);

            for (int j = 0; j < 3; j++) {
                OrderItem orderItem = new OrderItem()
                        .setOrderNo("NO_" + i)
                        .setUserId(1L)
                        .setCount(2)
                        .setPrice(BigDecimal.TEN);
                orderItemMapper.insert(orderItem);
            }
        }

        for (int i = 5; i < 9; i++) {
            Order order = new Order();
            order.setOrderNo("NO_" + i);
            order.setUserId(2L);
            orderMapper.insert(order);
            for (int j = 0; j < 3; j++) {
                OrderItem orderItem = new OrderItem()
                        .setOrderNo("NO_" + i)
                        .setUserId(2L)
                        .setCount(2)
                        .setPrice(BigDecimal.ONE);
                orderItemMapper.insert(orderItem);
            }
        }
    }

    /**
     * 分片字段关联表查询
     */
    @Test
    public void testQueryOrderAmount() {
        List<OrderVO> data = orderMapper.queryOrderAmount();
        data.forEach(System.err::println);
    }

    /**
     * 广播表插入
     */
    @Test
    public void testInsertBroadDict() {
        Dict dict = new Dict();
        dict.setDictType("type1");
        dictMapper.insert(dict);
    }

    @Test
    public void testQueryBrodDict() {
        List<Dict> dicts = dictMapper.selectList(null);
        dicts.forEach(System.err::println);
    }

}
