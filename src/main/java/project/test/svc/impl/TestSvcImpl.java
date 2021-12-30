package project.test.svc.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import common.emum.SystemEmum;
import common.model.SysUser;
import common.utils.MD5Utils;
import common.utils.UUIDGenerator;
import project.test.mapper.TestMapper;
import project.test.svc.TestSvc;
@Service
public class TestSvcImpl implements TestSvc
{
	public static int i=2;
	@Autowired
	private TestMapper testMapper;
	@Override
	public String getHelloString()
	{
		return "Hello world";
	}
	@Override
	public String testInsertUser()
	{
		SysUser sysUser=new SysUser();
		sysUser.setUserId(UUIDGenerator.getUUID());
		sysUser.setIsActive(SystemEmum.YES.getValue());
		sysUser.setLoginName("test"+i);
		sysUser.setLoginPassword(MD5Utils.md5("test"+i));
		sysUser.setSpwd("test"+i);
		sysUser.setUserName("test"+i);
		int insert=this.testMapper.insert(sysUser);
		return insert+","+i;
	}
	@Override
	public String testUpdateUser()
	{
		SysUser sysUser=new SysUser();
		sysUser.setUserId("59D28DFC10124AAAB8F85D871253764A");
		sysUser.setLoginPassword(MD5Utils.md5("123456"));
		sysUser.setSpwd("123456");
		sysUser.setCitrix("");
		SysUser updateUser=new SysUser();
		BeanUtil.copyProperties(sysUser,updateUser,CopyOptions.create().ignoreNullValue().ignoreError());
		int updateById=this.testMapper.updateById(updateUser);
		return updateById+"";
	}
	@Override
	public String testSelectUser()
	{
		SysUser user=new SysUser();
		QueryWrapper<SysUser> query=Wrappers.query(user);
		List<SysUser> selectList=this.testMapper.selectList(query);
		return selectList.toString();
	}
}
