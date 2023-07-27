package com.turing.sharding.jdbc.rw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.turing.sharding.jdbc.rw.mapper")
@SpringBootApplication
public class ShardingJdbcRwApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbcRwApplication.class, args);
    }

}
