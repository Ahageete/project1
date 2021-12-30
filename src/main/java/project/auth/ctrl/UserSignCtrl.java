package project.auth.ctrl;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import common.model.Result;
import common.model.SysUser;
import lombok.extern.slf4j.Slf4j;
import project.auth.svc.SignUserInfoSvc;
@RestController
@RequestMapping("auth/sign")
@Slf4j
public class UserSignCtrl
{
	@Autowired
	private SignUserInfoSvc SignUserInfoSvc;
	@RequestMapping("in")
	public String userSignIn(@RequestBody SysUser user,HttpServletRequest req)
	{
		Result<?> result=new Result<>();
		String msg="";
		log.warn("登录IP:"+req.getRemoteAddr());
		Subject subject=SecurityUtils.getSubject();
		try
		{
			if(user.getLoginName()==null)
				msg="用户名不可以为空";
			if(user.getLoginPassword()==null)
				msg="密码不可以为空";
			subject.login(new UsernamePasswordToken(user.getLoginName(),user.getLoginPassword()));
			SysUser sysUser=(SysUser)subject.getSession().getAttribute("loginUser");
			this.SignUserInfoSvc.updateLoginTime(sysUser.getUserId());
			result.setCode(0000);
			result.setMessage("true");
			result.setConsoleMsg(sysUser.getUserName()+"已登录!");
		}
		catch(IncorrectCredentialsException e)
		{
			msg="密码错误!";
		}
		catch(UnknownAccountException e)
		{
			msg="账号不存在!";
		}
		catch(AuthenticationException e)
		{
			msg="认证错误";
		}
		catch(Exception e)
		{
			msg="登录错误:"+e.getMessage();
			e.printStackTrace();
		}
		log.info(msg);
		return msg;
	}
	@RequestMapping("out")
	public void userSignOut(HttpServletResponse response)
	{
		Subject subject=SecurityUtils.getSubject();
		subject.getSession().stop();
		subject.logout();
		try
		{
			response.sendRedirect("index.html");
		}
		catch(IOException e)
		{
			log.error(e.getMessage());
		}
	}
}
