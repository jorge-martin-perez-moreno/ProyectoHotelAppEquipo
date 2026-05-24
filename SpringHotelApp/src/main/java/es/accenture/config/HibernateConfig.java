package es.accenture.config; //copiada de otro proyecto y consultado si estaba bien con chat gpt

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Clase de configuracion de Spring-Hibernate.
 * 
 * @author jorge martin perez moreno
 * @author javier roldan pomareta
 * @version 1.0
 */
//Anotacion que dice a Spring que esta clase es de configuracion, la lee al arrancar y registra todos sus metodos @Bean
@Configuration
//Anotacion que activa el soporte de las transacciones
@EnableTransactionManagement
//Anotacion que carga el application.properties
@PropertySource("classpath:application.properties")
public class HibernateConfig {

<<<<<<< HEAD
	@Autowired //Anotación para la injection de dependencias para leer el application properties
    private Environment env;
=======
//	Anotacion para inyectar el objeto Enviroment
	@Autowired
	private Environment env;
>>>>>>> 7c695dd8556d114af01ffa98e5cadfadae4e3b28

	/**
	 * Metodo que crea la conexion fisica con BBDD.
	 * 
	 * @return objeto DataSource con la configuracion de conexion a la BBDD.
	 */
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(env.getProperty("db.driver"));
		ds.setUrl(env.getProperty("db.url"));
		ds.setUsername(env.getProperty("db.username"));
		ds.setPassword(env.getProperty("db.password"));
		return ds;
	}

<<<<<<< HEAD
    @Bean // Conecta con BBDD
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
        factory.setDataSource(dataSource());
        factory.setPackagesToScan("es.accenture.entity");
=======
	/**
	 * Metodo que configura el SessionFactory para gestionar operaciones CRUD.
	 * 
	 * @return objeto LocalSessionFactoryBean configurado para gestionar las
	 *         operaciones CRUD con la BBDD.
	 */
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
		factory.setDataSource(dataSource());
		factory.setPackagesToScan("es.accenture.entity");
>>>>>>> 7c695dd8556d114af01ffa98e5cadfadae4e3b28

		Properties props = new Properties();
		props.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		props.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
		props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));

		factory.setHibernateProperties(props);

		return factory;
	}

	/**
	 * Metodo que configura la gestion de transacciones de Hibernate.
	 * 
	 * @param sessionFactory, objeto SessionFactory sobre el que gestiona las
	 *                        transacciones
	 * @return objeto HibernateTransactionManager configurado con el SessionFactory
	 */
	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager tx = new HibernateTransactionManager();
		tx.setSessionFactory(sessionFactory);
		return tx;
	}
}