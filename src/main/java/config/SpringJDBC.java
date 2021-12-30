package config;
import javax.sql.DataSource;
import org.apache.ibatis.session.AutoMappingBehavior;
import org.apache.ibatis.session.AutoMappingUnknownColumnBehavior;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
@Configuration
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass=true)
@MapperScan("project.*.mapper")
@ComponentScan({"project.*.svc","project.*.dao"})
public class SpringJDBC
{
	@Bean
	public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean(DataSource dataSource)
	{
		MybatisSqlSessionFactoryBean bean=new MybatisSqlSessionFactoryBean();
		try
		{
			MybatisConfiguration configuration=new MybatisConfiguration();
			MybatisPlusInterceptor mybatisPlusInterceptor=new MybatisPlusInterceptor();
			mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
			configuration.addInterceptor(mybatisPlusInterceptor);
			configuration.setAutoMappingBehavior(AutoMappingBehavior.FULL);
			configuration.setAutoMappingUnknownColumnBehavior(AutoMappingUnknownColumnBehavior.WARNING);
			configuration.setMapUnderscoreToCamelCase(true);
			configuration.setUseActualParamName(true);
			// configuration.setLogImpl(StdOutImpl.class);
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
}
