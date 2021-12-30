package project.system.model;
import java.util.List;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper=true)
public class SysMenu extends Model<SysMenu>
{
	private static final long serialVersionUID=6104494268763215267L;
	@TableId(type=IdType.INPUT)
	private String menuId;
	private String menuName;
	private String parentId;
	private String menuUrl;
	private String menuCode;
	@TableField(exist=false)
	private List<SysMenu> childrenMenus;
}
