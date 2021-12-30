package common.model;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
@Data
@EqualsAndHashCode(callSuper=true)
@Accessors(chain=true)
public class SysUserRole extends Model<SysUserRole>
{
	private static final long serialVersionUID=-3585721390754236797L;
	@TableId(type=IdType.INPUT)
	private String id;
	private String userId;
	private String roleId;
}
