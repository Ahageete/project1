package project.system.svc;
import java.util.List;
import common.model.Result;
import project.system.model.SysMenu;
public interface SysMenuSvc
{
	public Result<List<SysMenu>> getAllMenus(Integer pageSize,Integer pageNo);
	public Result<List<SysMenu>> getAllMenusByUser();
}
