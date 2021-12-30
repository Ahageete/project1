package project.system.svc.impl;
import java.util.List;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import common.model.Result;
import common.model.SysUser;
import lombok.extern.slf4j.Slf4j;
import project.system.model.SysMenu;
import project.system.svc.SysMenuSvc;
@Service
@Slf4j
public class SysMenuSvcImpl implements SysMenuSvc
{
	@Override
	public Result<List<SysMenu>> getAllMenus(Integer pageSize,Integer pageNo)
	{
		Result<List<SysMenu>> result=new Result<>();
		try
		{
			if(pageSize==null)
			{
				pageSize=1;
			}
			if(pageNo==null)
			{
				pageNo=1;
			}
			SysMenu sysMenu=new SysMenu();
			IPage<SysMenu> selectPage=sysMenu.selectPage(new Page<SysMenu>(pageNo,pageSize),new QueryWrapper<SysMenu>());
			result.setData(selectPage.getRecords());
			result.setCode(0000);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			result.setCode(9999);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	@Override
	public Result<List<SysMenu>> getAllMenusByUser()
	{
		Result<List<SysMenu>> result=new Result<>();
		try
		{
			List<SysMenu> sysMenus=((SysUser)SecurityUtils.getSubject().getSession().getAttribute("loginUser")).getSysMenus();
			List<SysMenu> parentMenus=Lists.newArrayList();
			for(SysMenu sysMenu:sysMenus)
			{
				if(sysMenu.getParentId()==null||sysMenu.getParentId().equals("0")||sysMenu.getParentId().equals(""))
				{
					sysMenu.setMenuUrl(sysMenu.getMenuUrl().replace("/**",""));
					parentMenus.add(sysMenu);
				}
			}
			for(SysMenu parentMenu:parentMenus)
			{
				List<SysMenu> childrenmaMenus=Lists.newArrayList();
				for(SysMenu childrenmaMenu:sysMenus)
				{
					if(parentMenu.getMenuId().equals(childrenmaMenu.getParentId()))
					{
						childrenmaMenu.setMenuUrl(childrenmaMenu.getMenuUrl().replace("/**",""));
						childrenmaMenus.add(childrenmaMenu);
					}
				}
				parentMenu.setChildrenMenus(childrenmaMenus);
			}
			result.setData(parentMenus);
			result.setCode(0000);
			log.error("用户的菜单:"+sysMenus);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			result.setCode(9999);
			result.setConsoleMsg(e.getMessage());
			result.setMessage("获取菜单时发生错误!");
		}
		return result;
	}
}
