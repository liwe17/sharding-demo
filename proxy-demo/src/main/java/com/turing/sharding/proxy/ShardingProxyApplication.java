package com.turing.sharding.proxy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.turing.sharding.proxy.mapper")
@SpringBootApplication
public class ShardingProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingProxyApplication.class, args);
    }

}
