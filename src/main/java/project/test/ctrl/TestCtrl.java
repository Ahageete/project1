package project.test.ctrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.test.svc.TestSvc;
@RestController
@RequestMapping("test")
public class TestCtrl
{
	@Autowired
	private TestSvc testSvc;
	@GetMapping("hello")
	public String hello()
	{
		return "Hello World !!!";
	}
	@GetMapping("resource")
	public String testResource()
	{
		return this.testSvc.getHelloString();
	}
	@GetMapping("select")
	public String testSelectUser()
	{
		return this.testSvc.testSelectUser();
	}
	@GetMapping("insert")
	public String testInsertUser()
	{
		return this.testSvc.testInsertUser();
	}
	@GetMapping("update")
	public String testUpdateUser()
	{
		return this.testSvc.testUpdateUser();
	}
}
