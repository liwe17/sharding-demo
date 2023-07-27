package com.turing.sharding.jdbc.horizontal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.turing.sharding.jdbc.horizontal.mapper")
@SpringBootApplication
public class ShardingJdbcHorizontalDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbcHorizontalDemoApplication.class, args);
    }

}
