package com.sample.demo.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EntityScan(basePackages= {"com.sample.demo.vo"})
@EnableJpaRepositories(basePackages= {"com.sample.demo.biz.**.mapper", "com.sample.demo.service.*"})
@EnableTransactionManagement
public class JpaConfiguration {
	@Primary
	@Bean(name="dataSource")
	@ConfigurationProperties("spring.datasource.sampleboot2")
	public DataSource dataSource() {
		return  DataSourceBuilder.create().type(HikariDataSource.class).build();
	}

	@Primary
	@Bean(name="entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("dataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.sample.demo").build();
	}

	@Bean(name="transactionManager")
	public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);

		return transactionManager;
	}
}
