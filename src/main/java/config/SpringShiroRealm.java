package config;
import java.util.ArrayList;
import java.util.List;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import common.model.SysRole;
import common.model.SysUser;
import common.model.SysUserRole;
import project.system.model.SysMenu;
import project.system.model.SysRoleMenu;
@Component
public class SpringShiroRealm extends AuthorizingRealm
{
	public SpringShiroRealm()
	{
		HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("md5");
		hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
		hashedCredentialsMatcher.setHashIterations(1);
		this.setCredentialsMatcher(hashedCredentialsMatcher);
	}
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
	{
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		SysUser sysUser=(SysUser)SecurityUtils.getSubject().getSession().getAttribute("loginUser");
		for(SysRole sysRole:sysUser.getSysRoles())
		{
			info.addRole(sysRole.getRoleCode());
		}
		for(SysMenu sysMenu:sysUser.getSysMenus())
		{
			info.addStringPermission(sysMenu.getMenuCode());
		}
		return info;
	}
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
	{
		String name=(String)token.getPrincipal();
		SysUser sysUser=new SysUser().selectOne(new QueryWrapper<SysUser>().eq("upper(login_name)",name).or().eq("upper(CITRIX)",name));
		if(sysUser==null)
		{
			throw new UnknownAccountException();
		}
		SimpleAuthenticationInfo simpleAuthenticationInfo=new SimpleAuthenticationInfo(name,sysUser.getLoginPassword(),this.getName());
		sysUser.setLoginPassword(null);
		sysUser.setSpwd(null);
		List<SysRole> sysRoles=Lists.newArrayList();
		List<SysMenu> SysMenusTemp=Lists.newArrayList();
		List<SysMenu> SysMenus=Lists.newArrayList();
		SysUserRole sysUserRole=new SysUserRole();
		sysUserRole.setUserId(sysUser.getUserId());
		for(SysUserRole sysUserRole2:sysUserRole.selectList(new QueryWrapper<SysUserRole>(sysUserRole)))
		{
			String roleId=sysUserRole2.getRoleId();
			sysRoles.add(new SysRole().selectById(roleId));
			SysRoleMenu sysRoleMenu=new SysRoleMenu();
			sysRoleMenu.setRoleId(roleId);
			for(SysRoleMenu roleMenu:sysRoleMenu.selectList(new QueryWrapper<SysRoleMenu>(sysRoleMenu)))
			{
				SysMenusTemp.add(new SysMenu().selectById(roleMenu.getMenuId()));
			}
		}
		ArrayList<String> sysMenuCodes=Lists.newArrayList();
		for(SysMenu sysMenu:SysMenusTemp)
		{
			String menuCode=sysMenu.getMenuCode();
			if(!sysMenuCodes.contains(menuCode))
			{
				sysMenuCodes.add(menuCode);
				SysMenus.add(sysMenu);
			}
		}
		sysUser.setSysRoles(sysRoles);
		sysUser.setSysMenus(SysMenus);
		Session session=SecurityUtils.getSubject().getSession();
		session.setAttribute("loginUser",sysUser);
		return simpleAuthenticationInfo;
	}
}
