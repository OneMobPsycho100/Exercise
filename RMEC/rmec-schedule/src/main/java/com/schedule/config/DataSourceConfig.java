//package com.schedule.config;
//
//import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
//import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
//import com.remc.common.Constants;
//import org.apache.ibatis.mapping.DatabaseIdProvider;
//import org.apache.ibatis.plugin.Interceptor;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.scheduling.annotation.EnableAsync;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.annotation.Resource;
//import javax.sql.DataSource;
//
///**
// * @Author: chenmingzhe
// * @Date: 2020/4/25 19:54
// */
//@Configuration
//@EnableAsync
//@MapperScan(basePackages = {Constants.SCAN_MAPPER}, sqlSessionFactoryRef = "scheduleSqlSessionFactory")
//public class DataSourceConfig {
//
//    @Bean("scheduleSqlSessionFactory")
//    public SqlSessionFactory scheduleSqlSessionFactory( DataSource dataSource) throws Exception {
//        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
////        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
////                .getResources(ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
////                        + "com/*/mapper/**/*.xml"));
//        return bean.getObject();
//    }
//
//    @Bean("dataSource")
//    @Qualifier("dataSource")
//    @ConfigurationProperties("spring.datasource.druid")
//    public DataSource sysDataSource() {
//        return DruidDataSourceBuilder.create().build();
//    }
//
//    @Bean
//    @Resource
//    public PlatformTransactionManager libManager(@Qualifier("dataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//}
