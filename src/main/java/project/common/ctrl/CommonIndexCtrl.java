package project.common.ctrl;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("")
public class CommonIndexCtrl
{
	@RequestMapping("")
	public void gotoIndex(HttpServletResponse response) throws Exception
	{
		response.sendRedirect("/index.html");
	}
	@RequestMapping("index")
	public void gotoIndexWithPath(HttpServletResponse response) throws Exception
	{
		Session session=SecurityUtils.getSubject().getSession();
		System.out.println(session.getId());
		response.sendRedirect("/index.html");
	}
}
