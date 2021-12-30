package project.test.mapper;
import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import common.model.SysShiro;
@Mapper
@InterceptorIgnore(illegalSql="true")
public interface TestSysShiroMapper extends BaseMapper<SysShiro>
{
}
