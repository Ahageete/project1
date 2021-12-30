package common.model;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable
{
	public Result(Integer code,String message)
	{
		super();
		this.code=code;
		this.message=message;
	}
	private static final long serialVersionUID=1L;
	private Integer code;
	private String message;
	private String consoleMsg;
	private T data;
}
