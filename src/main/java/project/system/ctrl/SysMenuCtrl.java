package project.system.ctrl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import common.model.Result;
import project.system.model.SysMenu;
import project.system.svc.SysMenuSvc;
@RestController
@RequestMapping("system/menu")
public class SysMenuCtrl
{
	@Autowired
	private SysMenuSvc menuSvc;
	@GetMapping("all")
	public Result<List<SysMenu>> getAllMenus(Integer pageSize,Integer pageNo)
	{
		Result<List<SysMenu>> result=new Result<>();
		result=this.menuSvc.getAllMenus(pageSize,pageNo);
		return result;
	}
	@GetMapping("byUser")
	public Result<List<SysMenu>> getAllMenusByUser()
	{
		Result<List<SysMenu>> result=new Result<>();
		result=this.menuSvc.getAllMenusByUser();
		return result;
	}
}
