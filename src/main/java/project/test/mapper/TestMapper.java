package project.test.mapper;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import common.model.SysUser;
@Mapper
@InterceptorIgnore(illegalSql="true")
public interface TestMapper extends BaseMapper<SysUser>
{
}
