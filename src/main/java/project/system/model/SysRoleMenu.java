package project.system.model;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
@Data
@EqualsAndHashCode(callSuper=true)
@Accessors(chain=true)
public class SysRoleMenu extends Model<SysRoleMenu>
{
	private static final long serialVersionUID=2018055674889332226L;
	private String id;
	private String menuId;
	private String roleId;
}
