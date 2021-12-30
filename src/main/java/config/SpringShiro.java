package config;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import project.system.mapper.SysMenuMapper;
import project.system.model.SysMenu;
@Configuration
public class SpringShiro
{
	@Autowired
	private SysMenuMapper sysMenuMapper;
	@Autowired
	private SpringShiroRealm springShiroRealm;
	@Bean
	public ShiroFilterFactoryBean shiroFilter()
	{
		ShiroFilterFactoryBean shiroFilter=new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(this.securityManager());
		shiroFilter.setLoginUrl("/login.html");
		shiroFilter.setSuccessUrl("/index");
		shiroFilter.setUnauthorizedUrl("/error/403");
		Map<String,String> map=new LinkedHashMap<String,String>();
		map.put("/js/**","anon");
		map.put("/css/**","anon");
		map.put("/test/**","anon");
		map.put("/error/**","anon");
		map.put("/auth/sign/in","anon");
		map.put("/system/menu/byUser","authc");
		for(SysMenu sysMenu:this.sysMenuMapper.selectList(new QueryWrapper<>()))
		{
			map.put(sysMenu.getMenuUrl(),"perms["+sysMenu.getMenuCode()+"]");
		}
		map.put("/**","authc");
		shiroFilter.setFilterChainDefinitionMap(map);
		return shiroFilter;
	}
	@Bean
	public DefaultWebSecurityManager securityManager()
	{
		DefaultWebSessionManager sessionManager=new DefaultWebSessionManager();
		sessionManager.setSessionIdCookieEnabled(true);
		sessionManager.setSessionIdCookie(new SimpleCookie("JSID"));
		sessionManager.setSessionIdUrlRewritingEnabled(false);
		sessionManager.setSessionValidationSchedulerEnabled(true);
		sessionManager.setSessionValidationInterval(1000*60);
		sessionManager.setDeleteInvalidSessions(true);
		sessionManager.setGlobalSessionTimeout(1000*60*30);
		ExecutorServiceSessionValidationScheduler scheduler=new ExecutorServiceSessionValidationScheduler();
		scheduler.enableSessionValidation();
		scheduler.setInterval(1000*60*60*8);
		scheduler.setThreadNamePrefix("shiro回话调度线程");
		sessionManager.setSessionValidationScheduler(scheduler);
		DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
		securityManager.setRealm(this.springShiroRealm);
		securityManager.setSessionManager(sessionManager);
		SecurityUtils.setSecurityManager(securityManager);
		return securityManager;
	}
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor()
	{
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor=new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
		return authorizationAttributeSourceAdvisor;
	}
}
