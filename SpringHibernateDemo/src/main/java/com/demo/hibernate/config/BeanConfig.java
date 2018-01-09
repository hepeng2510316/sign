package com.demo.hibernate.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@ComponentScan
@PropertySource({"classpath:database.properties", "classpath:hibernate.properties"})
public class BeanConfig {
    @Value("${jdbc.user}")
    private String user;

    @Value("${jdbc.password}")
    private String password;

    @Value("${jdbc.driverClass}")
    private String driverClass;

    @Value("${jdbc.jdbcUrl}")
    private String jdbcUrl;

    @Value("${jdbc.initPoolSize}")
    private int initPoolSize;

    @Value("${jdbc.maxPoolSize}")
    private int maxPoolSize;


    @Value("${hibernate.dialect}")
    String dialect;

    @Value("${hibernate.show_sql}")
    String showSql;

    @Value("${hibernate.format_sql}")
    String formatSql;

    @Value("${hibernate.hbm2ddl.auto}")
    String hbm2ddlAuto;

    @Value("${hibernate.annotatedPackages}")
    String annotatedPackages;

    /**
     * 配置数据源属性
     *
     * @return 数据源
     * @throws PropertyVetoException PropertyVetoException
     */
    @Bean
    @Primary
    public DataSource getDataSource() throws PropertyVetoException, SQLException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setInitialPoolSize(initPoolSize);
        dataSource.setMaxPoolSize(maxPoolSize);
        return dataSource;
    }

    /**
     * 配置 Hibernate 的 SessionFactory 实例
     *
     * @param dataSource 数据源
     * @return SessionFactory
     */
    @Bean
    @Primary
    @Autowired
    public LocalSessionFactoryBean getSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setHibernateProperties(hibernateProperties());
        factoryBean.setPackagesToScan(annotatedPackages);
        return factoryBean;
    }

    /**
     * 配置事务管理器
     *
     * @param sessionFactory SessionFactory
     * @return 事务管理器
     */
    @Bean("transactionManager")
    @Primary
    @Autowired
    public PlatformTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }

    /**
     * 事务属性
     *
     * @param transactionManager 事务管理器
     * @return TransactionInterceptor
     */
    @Bean
    @Primary
    @Autowired
    public TransactionInterceptor getTransactionInterceptor(PlatformTransactionManager transactionManager) {
        TransactionInterceptor txAdvice = new TransactionInterceptor();
        txAdvice.setTransactionManager(transactionManager);
        txAdvice.setTransactionAttributes(transactionProperties());
        return txAdvice;
    }

    /**
     * 配置事务切点 并把切点和事务属性关联起来
     *
     * @param txAdvice 事务属性
     * @return Advisor
     */
    @Bean
    @Primary
    @Autowired
    public Advisor txAdviceAdvisor(TransactionInterceptor txAdvice) {
        AspectJExpressionPointcut txPointcut = new AspectJExpressionPointcut();
        txPointcut.setExpression("execution(* com.demo.hibernate.service.*.*(..))");
        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor(txPointcut, txAdvice);
        System.out.println(defaultPointcutAdvisor);
        return defaultPointcutAdvisor;
    }


    private Properties transactionProperties() {
        return new Properties() {
            {
                setProperty("*", "PROPAGATION_REQUIRED");
            }
        };
    }

    private Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.dialect", dialect);
                setProperty("hibernate.show_sql", showSql);
                setProperty("hibernate.format_sql", formatSql);
                setProperty("hibernate.hbm2ddl.auto", hbm2ddlAuto);
            }
        };
    }

}
