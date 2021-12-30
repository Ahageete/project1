package project.common.svc;
import java.util.List;
public interface CommonSvc
{
	public <T> List<T> getObjectBySelectObject(T t);
	public <T> List<T> getObjectByCondition(T t);
}
