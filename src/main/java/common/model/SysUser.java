package common.model;
import java.util.Date;
import java.util.List;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import project.system.model.SysMenu;
@Data
@EqualsAndHashCode(callSuper=true)
@Accessors(chain=true)
public class SysUser extends Model<SysUser>
{
	private static final long serialVersionUID=7536205006669728775L;
	@TableId(value="USER_ID",type=IdType.INPUT)
	private String userId;
	private String userName;
	private String loginName;
	private String loginPassword;
	private String spwd;
	private String email;
	private String phone;
	private Date lastLogin;
	private String isActive;
	private String citrix;
	private Date createTime;
	private Date updateTime;
	@TableField(exist=false)
	private List<SysRole> sysRoles;
	@TableField(exist=false)
	private List<SysMenu> sysMenus;
	@TableField(exist=false)
	private SysUserInfo UserInfo;
}
