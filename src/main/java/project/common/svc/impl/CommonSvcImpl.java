package project.common.svc.impl;
import java.util.List;
import org.springframework.stereotype.Service;
import project.common.svc.CommonSvc;
@Service
public class CommonSvcImpl implements CommonSvc
{
	@Override
	public <T> List<T> getObjectBySelectObject(T t)
	{
		return null;
	}
	@Override
	public <T> List<T> getObjectByCondition(T t)
	{
		return null;
	}
}
