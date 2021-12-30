package project.auth.mapper;
import common.model.SysUser;
import common.model.SysUserInfo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import project.common.mapper.provider.CommonKeywordSearchProvider;
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser>
{
	@SelectProvider(type=CommonKeywordSearchProvider.class,method="QueryString")
	public List<SysUser> getAllUsers(String condition,Class<SysUser> paramClass);
	@SelectProvider(type=CommonKeywordSearchProvider.class,method="QueryString")
	public List<SysUserInfo> querySysUserInfo(String condition,Class<SysUserInfo> paramClass);
	@Select("select * from SYS_USER where USER_ID=#{id}")
	public SysUser getUserById(@Param("id") String id);
	@Select("select * from SYS_USER where USER_NAME=#{name}")
	public SysUser getUserByUsername(@Param("name") String name);
	@Select("select * from SYS_USER_INFO where USER_ID=#{id}")
	public SysUserInfo getUserInfoById(@Param("id") String id);
	@Select("select * from SYS_USER where upper(USER_NAME)=#{name} or upper(CITRIX)=#{name}")
	public SysUser getUserByUsernameOrCitrix(@Param("name") String name);
}
