package config;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11Nio2Protocol;
import org.apache.shiro.spring.config.web.autoconfigure.ShiroWebFilterConfiguration;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.filter.DelegatingFilterProxy;
import cn.hutool.extra.spring.EnableSpringUtil;
@SpringBootApplication(exclude={HibernateJpaAutoConfiguration.class,ShiroWebFilterConfiguration.class})
@EnableAspectJAutoProxy
@EnableAsync
@EnableScheduling
@EnableSpringUtil
@ComponentScan({"config","common.utils"})
public class SpringRun implements ApplicationRunner
{
	public static void main(String[] args)
	{
		SpringApplication.run(SpringRun.class,args);
	}
	@Override
	public void run(ApplicationArguments args) throws Exception
	{
	}
	@Bean
	public FilterRegistrationBean<DelegatingFilterProxy> shiroFilterRegistration()
	{
		FilterRegistrationBean<DelegatingFilterProxy> shiroFilterRegistration=new FilterRegistrationBean<>();
		shiroFilterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
		shiroFilterRegistration.addInitParameter("targetFilterLifecycle","true");
		shiroFilterRegistration.setEnabled(true);
		shiroFilterRegistration.addUrlPatterns("/*");
		shiroFilterRegistration.setOrder(0);
		return shiroFilterRegistration;
	}
	@Bean
	public TomcatServletWebServerFactory servletContainer()
	{
		TomcatServletWebServerFactory tomcat=new TomcatServletWebServerFactory()
		{
			@Override
			protected void postProcessContext(Context context)
			{
				SecurityConstraint securityConstraint=new SecurityConstraint();
				securityConstraint.setUserConstraint("CONFIDENTIAL");
				SecurityCollection collection=new SecurityCollection();
				collection.addPattern("/*");
				securityConstraint.addCollection(collection);
				context.addConstraint(securityConstraint);
			}
		};
		Connector connector=new Connector(new Http11Nio2Protocol());
		connector.setScheme("http");
		connector.setPort(80);
		connector.setSecure(false);
		connector.setRedirectPort(443);
		tomcat.addAdditionalTomcatConnectors(connector);
		tomcat.setRegisterDefaultServlet(true);
		return tomcat;
	}
}
