package com.turing.sharding.jdbc.horizontal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.turing.sharding.jdbc.horizontal.entity.Order;
import com.turing.sharding.jdbc.horizontal.vo.OrderVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderMapper extends BaseMapper<Order> {

    @Select("SELECT a.order_no,SUM(b.count * b.price) AS amount FROM t_order a JOIN t_order_item b ON a.order_no = b.order_no GROUP BY a.order_no")
    List<OrderVO> queryOrderAmount();

}