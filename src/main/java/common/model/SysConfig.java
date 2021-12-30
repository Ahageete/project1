package common.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="SYS_CONFIG")
public class SysConfig
{
	@Id
	@Column(name="ID",length=64,nullable=false)
	private String id;
	@Column(name="SYS_TEAM",length=128,nullable=true)
	private String sysTeam;
	@Column(name="SYS_KEY",length=128,nullable=true)
	private String sysKey;
	@Column(name="SYS_VAL",length=128,nullable=true)
	private String sysVal;
}
