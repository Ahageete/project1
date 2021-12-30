package common.utils;
import common.emum.ResultEnum;
import common.model.Result;
public class ResultUtil
{
	public static Result<Object> success(Object object)
	{
		Result<Object> result=new Result<>(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMessage());
		result.setData(object);
		return result;
	}
	public static Result<?> success()
	{
		return success("");
	}
	public static Result<?> error(ResultEnum resultEnum)
	{
		Result<?> result=new Result<>(resultEnum.getCode(),resultEnum.getMessage());
		return result;
	}
	public static Result<?> error(String msg)
	{
		Result<?> result=new Result<>();
		result.setCode(ResultEnum.UNKOWN.getCode());
		result.setMessage(msg);
		return result;
	}
	public static Result<?> error(Integer code,String message)
	{
		Result<?> result=new Result<>(code,message);
		return result;
	}
}
