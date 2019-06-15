package com;

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
public class AppConfig implements WebMvcConfigurer, WebApplicationInitializer {

	@Value("${db.driver}")
	private String dbDriver;
	
	@Value("${db.url")
	private String dbUrl;
	
	@Value("${db.user")
	private String dbUsername;
	
	@Value("${db.pass")
	private String dbPassword;
	
	// Bean for the credentials of the database
	@Bean
	public BasicDataSource dataSource() {
		System.out.println("Creating BasicDataSource bean...");
		
		// Instantiate the BasicDataSource
		BasicDataSource dataSource = new BasicDataSource();
		
		// Set all the BasicDataSource parameters
		dataSource.setDriverClassName(dbDriver);
		dataSource.setUrl(dbUrl);
		dataSource.setUsername(dbUsername);
		dataSource.setPassword(dbPassword);
		
		System.out.println("BasicDataSource has been created!");
		
		// return the dataSource with all the set parameters
		return dataSource;
	}
	
	// Creating a LocalSessionFactoryBean to tell Spring what to scan as well as what Hibernate should do
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		System.out.println("Creating LocalSessionFactoryBean bean...");

		// Create new instance of LocalSessionFactoryBean
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		
		// Set the dataSource 
		sessionFactory.setDataSource(dataSource());
		
		// Tell Spring what packages to scan
		sessionFactory.setPackagesToScan("com");
		
		// Set Hibernate properties (create hibernateProperties() in its own method)
		sessionFactory.setHibernateProperties(hibernateProperties());
		
		System.out.println("LocalSessionFactoryBean bean created!");
		
		// Return the LocalSessionFactoryBean
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

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext container = new AnnotationConfigWebApplicationContext();
		container.register(AppConfig.class);
		
		servletContext.addListener(new ContextLoaderListener(container));
		
		// Sets up the DispatcherServlet 
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(container));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		
	}
	
	// The properties of Hibernate
	private final Properties hibernateProperties() {
		System.out.println("Loading Hibernate properties...");
		
		// Create a new Properties instance
		Properties hibernateProperties = new Properties();
		
		// Set the properties of Hibernate (similar to the hibernate.cfg.xml)
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		hibernateProperties.setProperty("hibernate.format_sql", "true");
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create");
		
		System.out.println("Hibernate properties loaded");
		
		// Return the properties
		return hibernateProperties;
	}
	
}
