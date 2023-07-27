package com.turing.sharding.jdbc.vertical;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.turing.sharding.jdbc.vertical.mapper")
@SpringBootApplication
public class ShardingJdbcVerticalDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbcVerticalDemoApplication.class, args);
    }

}
