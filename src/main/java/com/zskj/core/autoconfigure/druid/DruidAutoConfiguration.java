package com.zskj.core.autoconfigure.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Servlet;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * druid自动配置
 *
 * @author 花开
 */
@Configuration
@ConditionalOnClass({DataSource.class, DruidDataSource.class})
@EnableConfigurationProperties({DruidDataSourceProperties.class})
@ConditionalOnExpression("'${core.datasource.druid.enabled}'=='true'")
public class DruidAutoConfiguration {

    @Autowired
    private DruidDataSourceProperties roperties;

    private static final Logger logger = LoggerFactory.getLogger(DruidAutoConfiguration.class);

    @Bean
    @ConditionalOnProperty(prefix = "core.datasource.druid", name = "servlet-enabled")
    @ConditionalOnClass(Servlet.class)
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        servletRegistrationBean.addInitParameter("loginUsername", roperties.getLoginUsername());
        servletRegistrationBean.addInitParameter("loginPassword", roperties.getLoginPassword());
        servletRegistrationBean.addInitParameter("resetEnable", roperties.isResetEnable() + "");
        return servletRegistrationBean;
    }

    @Bean
    @ConditionalOnMissingBean
    public DataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(roperties.getDriverClassName());
        druidDataSource.setUrl(roperties.getUrl());
        druidDataSource.setUsername(roperties.getUsername());
        druidDataSource.setPassword(roperties.getPassword());
        druidDataSource.setInitialSize(roperties.getInitialSize());
        druidDataSource.setMinIdle(roperties.getMinIdle());
        druidDataSource.setMaxActive(roperties.getMaxActive());
        druidDataSource.setMaxWait(roperties.getMaxWait());
        druidDataSource.setTimeBetweenConnectErrorMillis(roperties.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(roperties.getMinEvictableIdleTimeMillis());
        druidDataSource.setValidationQuery(roperties.getValidationQuery());
        druidDataSource.setTestWhileIdle(roperties.isTestWhileIdle());
        druidDataSource.setTestOnBorrow(roperties.isTestOnBorrow());
        druidDataSource.setTestOnReturn(roperties.isTestOnReturn());
        druidDataSource.setPoolPreparedStatements(roperties.isPoolPreparedStatements());
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(roperties.getMaxPoolPreparedStatementPerConnectionSize());
        druidDataSource.setUseGlobalDataSourceStat(roperties.isUseGlobalDataSourceStat());

        try {
            druidDataSource.setFilters(roperties.getFilters());
        } catch (SQLException e) {
            logger.error("设置拦截器失败", e);
            e.printStackTrace();
        }
        return druidDataSource;
    }

    @Bean
    @ConditionalOnProperty(prefix = "core.datasource.druid", name = "filter-enabled")
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
