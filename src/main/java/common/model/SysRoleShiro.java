package common.model;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper=true)
public class SysRoleShiro extends Model<SysRoleShiro>
{
	private static final long serialVersionUID=8806052646561796233L;
	@TableId(type=IdType.INPUT)
	private String id;
	private String roleId;
	private String shiroId;
}
