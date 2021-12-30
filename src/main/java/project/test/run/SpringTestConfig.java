package project.test.run;
import java.io.IOException;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.ibatis.session.AutoMappingBehavior;
import org.apache.ibatis.session.AutoMappingUnknownColumnBehavior;
import org.hibernate.SessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.IllegalSQLInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.zaxxer.hikari.HikariDataSource;
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass=true)
@MapperScan("project.test.mapper")
@ComponentScan({"project.test.svc","project.test.dao"})
public class SpringTestConfig
{
	@Bean
	public DataSource dataSource()
	{
		HikariDataSource hds=new HikariDataSource();
		hds.setJdbcUrl("jdbc:mysql://rm-2zeo34j5916ust3fd9o.mysql.rds.aliyuncs.com:3306/project2?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT-8");
		hds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		hds.setUsername("ahageete");
		hds.setPassword("Ahageete@211314");
		return hds;
	}
	@Bean
	public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean(DataSource dataSource)
	{
		MybatisSqlSessionFactoryBean bean=new MybatisSqlSessionFactoryBean();
		try
		{
			MybatisConfiguration configuration=new MybatisConfiguration();
			MybatisPlusInterceptor mybatisPlusInterceptor=new MybatisPlusInterceptor();
			mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
			mybatisPlusInterceptor.addInnerInterceptor(new IllegalSQLInnerInterceptor());
			configuration.addInterceptor(mybatisPlusInterceptor);
			configuration.setAutoMappingBehavior(AutoMappingBehavior.FULL);
			configuration.setAutoMappingUnknownColumnBehavior(AutoMappingUnknownColumnBehavior.WARNING);
			configuration.setMapUnderscoreToCamelCase(true);
			bean.setConfiguration(configuration);
			bean.setDataSource(dataSource);
			bean.setTransactionFactory(new SpringManagedTransactionFactory());
			bean.afterPropertiesSet();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return bean;
	}
	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource)
	{
		LocalSessionFactoryBean bean=new LocalSessionFactoryBean();
		Properties prop=new Properties();
		bean.setDataSource(dataSource);
		prop.setProperty("hibernate.dialect","org.hibernate.dialect.MySQL8Dialect");
		prop.setProperty("hibernate.hbm2ddl.auto","update");
		prop.setProperty("hibernate.show_sql","true");
		prop.setProperty("hibernate.format_sql","true");
		bean.setHibernateProperties(prop);
		bean.setPackagesToScan("common.model");
		try
		{
			bean.afterPropertiesSet();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return bean;
	}
	@Bean
	public HibernateTransactionManager HibernateTransactionManager(SessionFactory sessionFactory)
	{
		return new HibernateTransactionManager(sessionFactory);
	}
	@Bean
	public HibernateTemplate template(SessionFactory sessionFactory)
	{
		return new HibernateTemplate(sessionFactory);
	}
}
