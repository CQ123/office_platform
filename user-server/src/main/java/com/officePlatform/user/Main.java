package com.officePlatform.user;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AutoGenerator autoGenerator = new AutoGenerator();
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("password");
        dataSourceConfig.setUrl("jdbc:mysql://www.chenqi.club:3306/user_server?useUnicode=true&characterEncoding=UTF-8");
        autoGenerator.setDataSource(dataSourceConfig);
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOpen(false);
        globalConfig.setOutputDir(System.getProperty("user.dir")+"/user-server/src/main/java");
        globalConfig.setAuthor("cq");
        globalConfig.setServiceName("%sService");
        autoGenerator.setGlobalConfig(globalConfig);
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.officePlatform.user");
        packageConfig.setEntity("entity");
        packageConfig.setMapper("mapper");
        packageConfig.setController("controller");
        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.impl");
        autoGenerator.setPackageInfo(packageConfig);
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);

        List<TableFill> list = new ArrayList<>();
        TableFill tableFill1 = new TableFill("create_time", FieldFill.INSERT);
        list.add(tableFill1);
        strategyConfig.setTableFillList(list);
        autoGenerator.setStrategy(strategyConfig);

        autoGenerator.execute();
    }
}
