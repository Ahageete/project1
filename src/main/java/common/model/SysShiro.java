package common.model;
import javax.persistence.Table;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@Table(name="SYS_SHIRO")
@EqualsAndHashCode(callSuper=true)
public class SysShiro extends Model<SysShiro>
{
	private static final long serialVersionUID=6276139914370093436L;
	@TableId(type=IdType.INPUT)
	private String id;
	private String name;
	private String cname;
	private String url;
}
