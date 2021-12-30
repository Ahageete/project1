package common.model;
import java.util.List;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper=true)
public class SysRole extends Model<SysRole>
{
	private static final long serialVersionUID=-7094237845967500020L;
	@TableId(type=IdType.INPUT)
	private String roleId;
	private String roleCode;
	private String roleName;
	private String remark;
	private String isActive;
	@TableField(exist=false)
	private List<SysUser> sysUsers;
}
