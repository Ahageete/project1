package project.test.run;
import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import common.emum.SystemEmum;
import common.model.SysShiro;
import common.model.SysUser;
import common.utils.UUIDGenerator;
import project.test.mapper.TestMapper;
public class TestRun
{
	public static void main(String[] args)
	{
		try(AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(SpringTestConfig.class);)
		{
			TestMapper mapper=ctx.getBean(TestMapper.class);
			SysUser sysUser=new SysUser();
			sysUser.setIsActive(SystemEmum.YES.getValue());
			sysUser.setLoginName("test1");
			sysUser.setUserName("test1");
			List<SysUser> selectList=mapper.selectList(new QueryWrapper<>(sysUser));
			for(SysUser sysUser2:selectList)
			{
				System.out.println(sysUser2);
			}
			System.out.println("=====================================================");
			SysShiro sysShiro=new SysShiro();
			sysShiro.setCname("系统管理员");
			sysShiro.setName("administrator");
			sysShiro.setId(UUIDGenerator.getUUID());
			sysShiro.setUrl("system");
			// boolean insertOrUpdate=sysShiro.insertOrUpdate();
			// System.out.println(insertOrUpdate);
			System.out.println("=====================================================");
			Page<SysUser> selectPage=mapper.selectPage(new Page<SysUser>(1,2),new QueryWrapper<>());
			List<SysUser> records=selectPage.getRecords();
			System.out.println("数量:"+records.size());
			for(SysUser sysUser2:records)
			{
				System.out.println(sysUser2);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
