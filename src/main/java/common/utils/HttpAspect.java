package common.utils;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
@Aspect
@Component
public class HttpAspect
{
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	@Pointcut("execution(* project.*.ctrl.*.*(..))")
	public void log()
	{
	}
	@Around("log()")
	public Object doBefore(ProceedingJoinPoint joinPoint) throws Throwable
	{
		ServletRequestAttributes attributes=(ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request=attributes.getRequest();
		logger.info("url:{},type:{},method:{},args:\n{}",request.getRequestURI(),request.getMethod(),joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName(),Arrays.toString(joinPoint.getArgs()));
		Object proceed=joinPoint.proceed();
		return proceed;
	}
	public void doAfterReturning(Object object)
	{
		logger.debug("response:{}",object);
	}
}
