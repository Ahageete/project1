package project.common.ctrl;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import common.model.Result;
@RestControllerAdvice
@RequestMapping("error")
public class GlobalExceptionCtrl
{
	@ExceptionHandler(value=Exception.class)
	public Result<?> doBaseApiException(Exception e)
	{
		e.printStackTrace();
		Result<?> result=new Result<>();
		result.setCode(4399);
		result.setMessage(e.getMessage());
		return result;
	}
	@RequestMapping("403")
	public Result<?> Error403()
	{
		Result<?> result=new Result<>();
		result.setCode(1403);
		result.setMessage("权限不足");
		return result;
	}
}
