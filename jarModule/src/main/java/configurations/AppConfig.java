package configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.Properties;

@Configuration
@PropertySource("classpath:properties-config.properties")
@ComponentScan(basePackages = { "dao"})
@EnableJpaRepositories(basePackages = {"repository"})
public class AppConfig {

    @Autowired
    private Environment environment;
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean lc = new LocalContainerEntityManagerFactoryBean();
        lc.setDataSource(dataSource());
        lc.setPackagesToScan("entity");
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        lc.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        lc.setJpaProperties(hibernateProperties());
        return lc;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager jTM = new JpaTransactionManager();
        jTM.setEntityManagerFactory(entityManagerFactory().getObject());
        return jTM;
    }

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dMD = new DriverManagerDataSource();
        dMD.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dMD.setUrl(environment.getRequiredProperty("jdbc.url"));
        dMD.setUsername(environment.getRequiredProperty("jdbc.username"));
        dMD.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dMD;
    }

    private Properties hibernateProperties(){
        Properties properties = new Properties();
        properties.put("hibernate.dialect",environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql",environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql",environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto",environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
