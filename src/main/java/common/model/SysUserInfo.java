package common.model;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="SYS_USER_INFO")
public class SysUserInfo implements Serializable
{
	private static final long serialVersionUID=3590362112209735823L;
	@NonNull
	@Id
	@Column(name="ID",length=64,insertable=true,updatable=false,nullable=false)
	private String id;
	@Column(name="PHONE",length=16,nullable=true)
	private String phone;
	@Column(name="EMAIL",length=64,nullable=true)
	private String email;
	@Column(name="COMP",length=16,nullable=true)
	private String comp;
}
