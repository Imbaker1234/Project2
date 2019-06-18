package com.revature;

import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@ComponentScan
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class AppConfig implements WebMvcConfigurer, WebApplicationInitializer{

	@Value("${db.driver}")
	private String dbDriver;
	
	@Value("${db.url}")
	private String dbUrl;
	
	@Value("${db.user}")
	private String dbUsername;
	
	@Value("${db.pass}")
	private String dbPassword;
	
	// Bean for the credentials of the database
	@Bean
	public BasicDataSource dataSource() {
		System.out.println("Created BasicDataSource bean...");
		
		// Instantiate the BasicDataSource
		BasicDataSource dataSource = new BasicDataSource();
		
		// Set all the BasicDataSource parameters
		dataSource.setDriverClassName(dbDriver);
		dataSource.setUrl(dbUrl);
		dataSource.setUsername(dbUsername);
		dataSource.setPassword(dbPassword);
		
		System.out.println("BasicDataSource has been created!");
		
		// return teh dataSource with all the set parameters
		return dataSource;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		System.out.println("Creating LocalSessionFactoryBean bean...");
		
		// Create new instance of LocalSessionFactoryBean
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		
		// Set the dataSource as the dataSource() above
		sessionFactory.setDataSource(dataSource());
		
		// Tell Spring what packages to scan
		sessionFactory.setPackagesToScan("com.revature");
		
		// Set Hibernate properties (create hibernateProperties() in a private method below)
		sessionFactory.setHibernateProperties(hibernateProperties());
		
		System.out.println("LocalSessionFactoryBean bean created!");
		
		// return the LocalSessionFactoryBean
		return sessionFactory;
		
	}
	
	// Bean for the TransactionManager
	@Bean
	public PlatformTransactionManager hibernateTransactionManager() {
		System.out.println("Creating PlatformTransactionManager bean...");
		
		// Create a new instance of HibernateTransactionManager
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		
		System.out.println("PlatformTransactionManager bean created!");
		
		return transactionManager;
	}
	
	// Properties of Hibernate
	private final Properties hibernateProperties() {
		System.out.println("Loading Hibernate properties...");
		
		// Create a new Porperties instance
		Properties hibernateProperties = new Properties();
		
		// Set the properties of Hibernate (similar to the hibernate.cfg.xml)
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		hibernateProperties.setProperty("hibernate.format_sql", "true");
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		
		System.out.println("Hibernate properties loaded");
		
		// Return the properties
		return hibernateProperties;	
	}
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext container = new AnnotationConfigWebApplicationContext();
		container.register(AppConfig.class);
		
		servletContext.addListener(new ContextLoaderListener(container));
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(container));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		
	}

}
