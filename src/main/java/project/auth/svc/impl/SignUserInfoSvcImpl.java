package project.auth.svc.impl;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import common.model.SysUser;
import project.auth.svc.SignUserInfoSvc;
@Service
public class SignUserInfoSvcImpl implements SignUserInfoSvc
{
	@Transactional(readOnly=false)
	public void updateLoginTime(String id)
	{
		new SysUser().setUserId(id).setLastLogin(new Date()).updateById();
	}
}
