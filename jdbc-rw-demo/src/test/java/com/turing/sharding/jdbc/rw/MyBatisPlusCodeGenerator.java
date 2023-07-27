package com.turing.sharding.jdbc.rw;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;

import java.sql.Types;
import java.util.List;
import java.util.Map;

public class MyBatisPlusCodeGenerator {

    public static void main(String[] args) {
        String trainOrder = "jdbc:mysql://localhost:3306/t_train_order?characterEncoding=utf8";
        FastAutoGenerator.create(trainOrder, "root", "123456")
                .globalConfig(builder -> builder
                        .author("LiWe17")
//                            .enableSwagger()
                        .outputDir("/Users/liwei/learnProject/sharding-demo/jdbc-demo/src/main/java"))
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    if (metaInfo.getJdbcType().TYPE_CODE == Types.SMALLINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);
                }))
                .packageConfig(builder -> builder.parent("com.turing.sharding")
                        .moduleName("jdbc")
                        .pathInfo(Map.of(OutputFile.xml, "/Users/liwei/learnProject/sharding-demo/jdbc-demo/src/main/resources/mapper")))
                .strategyConfig(builder -> builder
                        .addInclude(List.of("train_order","train_order_detail"))
                        .addTablePrefix("t_"))
                .execute();
    }
}
